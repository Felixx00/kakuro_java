package Dominio.Controladores;
import java.io.IOException;
import java.util.*;
import Dominio.Clases.*;
import Persistencia.CtrlPersistencia;

/**
 * Controlador de Rankings
 * @author Rubñn Montagut
 */

public class CtrlRankings {

	
	/**
	 * Lista de Rankings
	 */
	private ArrayList<Ranking> rankings = new ArrayList<Ranking>();

	
	/**
	 * Booleano para saber si se ha modificado la tabla
	 */
	private boolean dirty;
	
	
	
	/**
	 * Devuelve el numero de rankings en rankings
	 * @return Numero de rankings
	 */
	public int getNumRankings() {
		return this.rankings.size();
	}
	
	/**
	 * Guarda en la lista los rankings del txt
	 */
	public void cargar_ranking() {
		
		int tam = CtrlPersistencia.tamañoDirectorio();
		for(int idKakuro = 1; idKakuro <= tam; ++idKakuro) {
			try {
	        	List<List<String>> aux = CtrlPersistencia.cargarRanking(idKakuro);
	        	Ranking r = new Ranking();
	            for (List<String> fila : aux) {
	            	Pair p = new Pair();
	            	p.setKey(fila.get(0));
	            	p.setValue(Double.valueOf(fila.get(1)));
	            	r.afegirTemps(p);
	            }
	            this.rankings.add(r);
	            rankings.get((idKakuro-1)).setRecords();
	            
	        }
	        catch (Exception e) {
			    System.out.println(e);
	        } 
		}
	}
	
	
	/**
	 * Añade el nombre y tiempo pasado por parñmetro al ranking del kakuro correspondiente
	 * @param idKakuro id del kakuro
	 * @param nomJ Nombre del Usuario
	 * @param temps Tiempo de la partida
	 */
	public void guardarRanking(int idKakuro, String nomJ, Double temps) {
		idKakuro-=1;
		this.rankings.get(idKakuro).guardarRanking(nomJ, temps);
		this.dirty = true;
		idKakuro+=1;
		end(idKakuro);
	}
	
	
	/**
	 * Consulta un ñnico Ranking
	 * @param idKakuro
	 * @return Ranking del kakuro seleccionado
	 */
	public List<Pair> verRanking(int idKakuro) {
		idKakuro-=1;
		Ranking r = this.rankings.get(idKakuro);
		List<Pair> aux = r.getRanking();
		return aux;
	}
	
	
	/**
	 * Consulta los tres primeros Ranking
	 * @param idKakuro
	 * @return Records del kakuro seleccionado
	 */
	public List<Pair> verRecords(int idKakuro) {
		idKakuro-=1;
		Ranking r = this.rankings.get(idKakuro);
		List<Pair> aux = r.getRecords();
		return aux;
	}
	
	
	/**
	 * Funciñn para modificar el fichero si ha habido cambios
	 */
	private void end(int idKakuro) {
		if (dirty) {
	        try {
	            CtrlPersistencia.guardarRanking(this.rankings.get((idKakuro-1)).getRanking(), idKakuro);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
}