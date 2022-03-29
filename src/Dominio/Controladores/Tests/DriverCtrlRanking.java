package Dominio.Controladores.Tests;

import java.util.List;

import java.util.ListIterator;
import java.util.Scanner;

import Dominio.Clases.Pair;
import Dominio.Clases.Ranking;
import Dominio.Controladores.CtrlRankings;

/**
 * Driver del Controlador De Rankings
 * @author Rubén Montagut
 */

public class DriverCtrlRanking {

	
	/**
   	 * Carga los todos los rankings que haya
   	 * @param CR CtrlRankings
   	 */
    public static void testCargarRankings(CtrlRankings CR) throws Exception{
        CR.cargar_ranking();
    };
    
    
    /**
   	 * Imprime por pantalla el numero de rankings que hay en CR
   	 * @param CR CtrlRankings
   	 */
    public static void testGetNumRankings(CtrlRankings CR) throws Exception{
        System.out.println("Cantidad de Rankings: " + CR.getNumRankings());
    };
	
	
    /**
   	 * Guarda en el ranking idKakuro un nuevo tiempo con su jugador
   	 * @param CR CtrlRankings
   	 * @param idKakuro Identificador del kakuro
   	 * @param nomJ Nombre de usuario
   	 * @param temps Tiempo de la partida
   	 */
    public static void testGuardarRanking(CtrlRankings CR, int idKakuro, String nomJ, Double temps) throws Exception{
    	CR.guardarRanking(idKakuro, nomJ, temps);
    };
    
    
    /**
   	 * Imprime por pantalla todos los tiempos con su respectivo nombre de jugador del ranking de idKakuro
   	 * @param CR CtrlRankings
   	 * @param idKakuro Identificador del kakuro
   	 */
    public static void testVerRanking(CtrlRankings CR, int idKakuro) throws Exception{
        List<Pair> aux = CR.verRanking(idKakuro);
        ListIterator<Pair> it = aux.listIterator();
        while(it.hasNext()) {
        	Pair aux2 = it.next();
        	System.out.println(aux2.getKey() + " " + aux2.getValue());
        }
    };
    
    
    /**
   	 * Imprime por pantalla los valores de les 3 primeros puestos del ranking
   	 * @param CR CtrlRankings
   	 * @param idKakuro Identificador del kakuro
   	 */
    public static void testVerRecords(CtrlRankings CR, int idKakuro) throws Exception{
        List<Pair> aux = CR.verRecords(idKakuro);
        ListIterator<Pair> it = aux.listIterator();
        while(it.hasNext()) {
        	Pair aux2 = it.next();
        	System.out.println(aux2.getKey() + " " + aux2.getValue());
        }
    };
    
	
	
    public static void main (String [] args) throws Exception{
    	
    	Scanner s = new Scanner(System.in);
    	
    	CtrlRankings CR = new CtrlRankings();
    	System.out.println("Introduce el número correspondiente a la función que se quiera testear:");
        System.out.println("1- Test CargarRankings()");
        System.out.println("2- Test GetNumRankings()");
        System.out.println("3- Test GuardarRanking()");
        System.out.println("4- Test VerRanking()");
        System.out.println("5- Test VerRecords()");
        System.out.println("-1- Salir");
  

        int op = s.nextInt();
        while(op != -1){
            switch (op) {
                case 1: testCargarRankings(CR);
                        break;
                case 2: testGetNumRankings(CR);
                        break;
                case 3: System.out.println("Introduce el kakuro al que quieras guardar, nuevo nombre y tiempo en s con coma");
                		int a = s.nextInt();
                		String n = s.next();
                		Double d = s.nextDouble();
                		testGuardarRanking(CR,a,n,d);
                        break;
                case 4: System.out.println("Introduce el kakuro del cual quieres obtener el ranking");
                		int b = s.nextInt();	
                		testVerRanking(CR,b);
                		System.out.println("Ranking y Records actualizados");
                        break;
                case 5: System.out.println("Introduce el kakuro del cual quieres obtener los records");
        				int c = s.nextInt();
                		testVerRecords(CR, c);
                        break;
              }
	            System.out.println("Introduce el número correspondiente a la función que se quiera testear:");
	            System.out.println("1- Test CargarRankings()");
	            System.out.println("2- Test GetNumRankings()");
	            System.out.println("3- Test GuardarRanking()");
	            System.out.println("4- Test VerRanking()");
	            System.out.println("5- Test VerRecords()");
	            System.out.println("-1- Salir");
	            op = s.nextInt();
        }
    	
    
    }
}
