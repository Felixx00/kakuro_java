package Presentacion;

import java.util.List;


import Dominio.CtrlDominio;
import Dominio.Clases.Cell;
import Dominio.Clases.Pair;
import Dominio.Clases.kakuro;
import Dominio.Controladores.Generar;
import Dominio.Controladores.LeerEscribir;
import Persistencia.CtrlPersistencia;


/** \file CtrlPresentacion.java
 *  Clase destinada a comunicarse con la capa de dominio
 *
 */


public class CtrlPresentacion {

    private CtrlDominio CD;
    
    public CtrlPresentacion() {
        iniciarCtrls();
        System.out.println("Cargando interfñcie...");
        SignIn SI = new SignIn(this);
    }
    
    private void iniciarCtrls() {
        System.out.println("Iniciando controlador de dominio...");
        CD = new CtrlDominio();
    }   
    
    public boolean comprobarUsuario(String u, String p) {
    	return CD.comprobarUsuario(u,p);
    }
    
    public boolean añadirUsuario(String u, String p) {
    	return CD.añadirUsuario(u,p);
    }
    
    public void eliminarUsuario(String u) {
    	CD.eliminarUsuario(u);
    }
    
    public void modificarUsuarioContraseña(String u, String np) {
    	CD.modificarUsuarioContraseña(u, np);
    }
    
    public int tamañoDirectorio() {
    	return CD.tamañoDirectorio();
    }
    
    public void eliminarkakuro(int id) {
    	CD.eliminarkakuro(id);
    }
    
    public List<List<String>> leerkakurotxt(int id) {
    	return CD.leerkakurotxt(id);
    }
    
    public List<List<String>> generarkakuro(int n, int m, String dificultad) {
    	return CD.generarkakuro(n,m,dificultad);
    }
    
    public void guardarkakuro(List<List<String>> matriz_fichero, int fileCount) {
    	CD.guardarkakuro(matriz_fichero, fileCount);
    }
    
    public List<Pair> verRanking(int id) {
        return CD.verRanking(id);
    }
    
    public boolean partida(List<List<String>> matriz, int n, int m, int id, int i, int j, int cambio) {
    	return CD.partida(matriz, n, m, id, i,j,cambio);
    }
    
    public void guardarPartida(List<List<String>> matriz, int n, int m, int id, long tiempo) {
    	CD.guardarPartida(matriz, n, m, id, tiempo);
    }
    
    public boolean kakuroAcabado (List<List<String>> matriz, int n, int m, int id) {
    	return CD.kakuroAcabado(matriz, n, m, id);
    }
    
    public void guardarRanking(int idKakuro, String nomJ, Double temps) {
    	CD.guardarRanking(idKakuro, nomJ, temps);
    }
    
	public List<Pair> verRecords(int idKakuro) {
		return CD.verRecords(idKakuro);
	}
	
	public int validar(List<List<String>> matriz, int n, int m, int id) {
		return CD.validar(matriz, n, m, id);
	}
	public List<List<String>> getvalidartablero(List<List<String>> matriz, int n, int m, int id) {
		return CD.getvalidartablero(matriz, n, m, id);
	}
	public boolean hayGuardada() {
		return CD.hayGuardada();
	}
	
	public long cargar_tiempo() {
		return CD.cargar_tiempo();
	}
}

/** @class CtrlPresentacion
 *  Clase destinada a comunicarse con la capa de dominio
 *	@author Martñ Westermeyer
 */
