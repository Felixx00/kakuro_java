package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Dominio.Clases.Cell;
import Dominio.Clases.Pair;
import Dominio.Clases.Ranking;
import Dominio.Clases.kakuro;
import Dominio.Controladores.CtrlRankings;
import Dominio.Controladores.CtrlUsuario;
import Dominio.Controladores.Generar;
import Dominio.Controladores.LeerEscribir;
import Dominio.Controladores.Partida;
import Dominio.Controladores.SolveValida;
import Persistencia.CtrlPersistencia;

/**
 * Clase para el Controlador de Dominio y las funciones principales de la capa
 * @author Fñlix Forroll
 */

public class CtrlDominio {
	
	/**
	 * Controlador de Persistñncia
	 */
    private CtrlPersistencia CP;
    
    /**
     * Controlador d'usuari
     */
    private CtrlUsuario CU;
    
    /**
     * Controlador de Rankings
     */
    private CtrlRankings CR;
    
    /**
     * Generar
     */
    private Generar G;
    
    /**
     * LeerEscribir
     */
    private LeerEscribir LE;
    
    /**
     * Partida
     */
    private Partida P;
	
    /**
     * Creadora de la CtrlDominio
     * Crea instancias del CtrlPersistencia, de usuarios y rankings
     */
    public CtrlDominio() {
        CP = new CtrlPersistencia();
        System.out.println("Iniciando controlador de persistencia...");
        CU = new CtrlUsuario();
        CU.cargar_usuarios();
        CR = new CtrlRankings();
        CR.cargar_ranking();
    }
    
    /**
     * Comprueba que el usuario existe y tiene contraseña
     * @param u Usuario a comprobar
     * @param p Contraseña del usuario
     * @return Informa si existe el usuario indicado con la contraseña indicada
     */
    public boolean comprobarUsuario(String u, String p) {
        return CU.comprobarContraseña(u,p);
    }
    
    /**
     * Añade un usario al fichero usuarios.txt con su respectiva contraseña
     * @param u Nombre de usuario a añadir
     * @param p Contraseña para el usuario
     * @return Indica si se ha podido añadir el usuario correctamente o si ha fallado
     */
    public boolean añadirUsuario(String u, String p) {
    	return CU.añadirUsuario(u,p);
    }
    
    /**
     * Elimina un usuario del fichero usuarios.txt
     * @param u Nombre de usuario a eliminar
     */
    public void eliminarUsuario(String u) {
    	CU.eliminarUsuario(u);
    }
    
    /**
     * Modifica la constraseña del usuario indicado
     * @param u Nombre de usuario al que cambiar la constraseña
     * @param np Nueva constraseña
     */
    public void modificarUsuarioContraseña(String u, String np) {
    	CU.modificarUsuarioContraseña(u, np);
    }
    
    /**
     * Informa del tamaño del directorio Kakuros
     * @return Retorna el numero de elementos que hay
     */
    public int tamañoDirectorio() {
    	return CtrlPersistencia.tamañoDirectorio();
    }
    
    
    /**
     * Elimina un kakuro del repositorio
     * @param id Identificador del kakuro a eliminar
     */
    public void eliminarkakuro(int id) {
    	CtrlPersistencia.eliminar_kakuro(id);
    }
    
    /**
     * Lee un kakuro de un fichero
     * @param id Identificador del kakuro a leer
     * @return Retorna el kakuro en una matriz
     */
    public List<List<String>> leerkakurotxt(int id) {
    	return CtrlPersistencia.leerkakurotxt(id);
    }
    
    /**
     * Genera un kakuro con las dimensiones y dificultad indicadas
     * @param n Tamaño de filas del kakuro
     * @param m Tamaño de columnas del kakuro
     * @param dificultad Dificultad para el kakuro
     * @return Retorna una matriz con un kakuro generado con los parñmetros indicados
     */
    public List<List<String>> generarkakuro(int n, int m, String dificultad) {
    	G = new Generar();
    	ArrayList<kakuro> ak = new ArrayList<kakuro>();
		boolean x = false;
		while (!x) {
			x = true;
           ExecutorService executorService = Executors.newSingleThreadExecutor();
           executorService.execute(() ->ak.add(G.generar(n,m,dificultad)));
           executorService.shutdown();
           try {
                    if (!executorService.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
                    	x = false;
                    	System.out.println("ERROR");
                     }
            }catch(InterruptedException e){
            	
            }
		}
	    kakuro k = ak.get(0);
        ak.clear();
    	LE = new LeerEscribir();
    	return LE.convertirKakuroMatriz(k, 0);
    }
    
    /**
     * Guarda un kakuro en un fichero
     * @param matriz_fichero Matriz de un kakuro
     * @param fileCount Identificador para el nuevo kakuro
     */
    public void guardarkakuro(List<List<String>> matriz_fichero, int fileCount) {
    	CtrlPersistencia.guardarkakuro(matriz_fichero, fileCount);
    }
    
    /**
     * Muestra el ranking de un kakuro
     * @param id Identificador del kakuro
     * @return Retorna una lista de pares indicando nombres de usuario que han resuelto un kakuro i el tiempo que han tardado
     */
    public List<Pair> verRanking(int id) {
        return CR.verRanking(id);
    }
    
    
    /**
     * Comprueba si un cambio en un kakuro es correcto
     * @param matriz Matriz con el kakuro
     * @param n Numero de filas
     * @param m Numero de columnas
     * @param id Identificador del kakuro
     * @param i Numero de fila a actualizar
     * @param j Numero de columna a actualizar
     * @param cambio Valor del numero que introduce
     * @return Retorna si ha podido introducir el numero correctamente o era invñlido
     */
    public boolean partida(List<List<String>> matriz, int n, int m, int id, int i, int j, int cambio) {
      	LE = new LeerEscribir();
    	kakuro k = LE.crearMatriz(matriz,n,m,id);
      	P = new Partida();
    	return P.partida(k, i,j,cambio);
    }
    
    /**
     * Guarda el estado de una partida empezada
     * @param matriz Matriz del kakuro actual
     * @param n Numero de filas
     * @param m Numero de columnas
     * @param id Identificador del kakuro
     * @param tiempo Tiempo que lleva jugando el kakuro
     */
    public void guardarPartida (List<List<String>> matriz, int n, int m, int id, long tiempo) {
    	LE = new LeerEscribir();
    	kakuro k = LE.crearMatriz(matriz,n,m,id);
    	LE.guardar_partida(k, tiempo);
    }
    
    /**
     * Comprueba si un kakuro esta acabado y correcto
     * @param matriz Matriz del kakuro actual
     * @param n Numero de filas
     * @param m Numero de columnas
     * @param id Identificador del kakuro
     * @return Informa si el kakuro esta correctamente completado
     */
    public boolean kakuroAcabado (List<List<String>> matriz, int n, int m, int id) {
      	LE = new LeerEscribir();
    	kakuro k = LE.crearMatriz(matriz,n,m,id);
      	P = new Partida();
    	return P.resuelto(k);
    }
    
    /**
     * Guarda un ranking de un kakuro
     * @param idKakuro Identificador del kakuro
     * @param nomJ Nombre del usuario que ha resuelto el kakuro
     * @param temps Tiempo que ha tardado en resolver el kakuro
     */
    public void guardarRanking(int idKakuro, String nomJ, Double temps) {
    	CR.guardarRanking(idKakuro, nomJ, temps);
    }
    
    /**
     * Muestra los records de un kakuro
     * @param idKakuro Identificador de un kakuro
     * @return Retorna los valores de los tres primeros tiempos del ranking
     */
	public List<Pair> verRecords(int idKakuro) {
		return CR.verRecords(idKakuro);
	}
	
	/**
	 * Valida un kakuro
	 * @param matriz Matriz de un kakuro
	 * @param n Numero de filas
	 * @param m Numero de columnas
	 * @param id Identificador del kakuro
	 * @return Retorna el numero de soluciones que tiene el kakuro
	 */
	public int validar(List<List<String>> matriz, int n, int m, int id) {
      	LE = new LeerEscribir();
    	kakuro k = LE.crearMatriz(matriz,n,m,id);
		
		SolveValida v = new SolveValida();
		v.initialize(k);
		boolean aux = v.valida(0,0,k);
		return v.getnumsolucions();
	}
	
	/**
	 * Comprueba si se puede pedir pista para el kakuro 
	 * @param matriz Matriz del kakuro
	 * @param n Numero de filas
	 * @param m Numero de columnas
	 * @param id Identificador del kakuro
	 * @return Retorna la matriz del kakuro
	 */
	public List<List<String>> getvalidartablero (List<List<String>> matriz, int n, int m, int id) {
      	LE = new LeerEscribir();
    	kakuro k = LE.crearMatriz(matriz,n,m,id);
    	
		SolveValida v = new SolveValida();
		v.initialize(k);
		v.valida(0,0,k);
		int sol = v.getnumsolucions();
		if (sol == 1) {
			v.solve(0,0,k);
			List<List<String>> auxmat = LE.convertirKakuroMatriz(k, 1);
			return auxmat;
		}
		else {
			List<List<String>> auxmat = new ArrayList<List<String>>();
			return auxmat;
		}
	}
	/**
	 * Comprueba si hay una partida guardada
	 * @return retorna cierto si la hay, false en caso contrario
	 */
	public boolean hayGuardada() {
		return CtrlPersistencia.hayGuardada();
	}
	
	
	
	/**
	   * Lee el tiempo de la partida guardada
	   * @return Tiempo de partida
	   */
	  public long cargar_tiempo() {

		    List<List<String>> matriz_auxiliar = new ArrayList<>();
					    
		    int num_kakuro = 0;
		    
		    matriz_auxiliar = CtrlPersistencia.leerkakurotxt(num_kakuro);

		    //Pasa a int las dimensiones de la matriz
		    int n = Integer.parseInt(matriz_auxiliar.get(0).get(0));
		    			
		    long x = Long.parseLong(matriz_auxiliar.get(n+1).get(0));
		  
		    return x;    
	  }
	
}