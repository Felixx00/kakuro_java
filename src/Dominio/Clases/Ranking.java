package Dominio.Clases;
import java.util.*;

/**
 * Clase para los Rankings
 * @author Rubén Montagut
 */

public class Ranking {
	
	/**
	 * Lista de pares de usuario y tiempo de resolver
	 */
	private List<Pair> tempsPerUsuari = new ArrayList<Pair>();
	
	/**
	 * Lista de pares de los records de un kakuro
	 */
	private List<Pair> records = new ArrayList<Pair>(3);
	
	
	
	/**
	 * Consulta los rankings de un kakuro
	 * @return Devuelve la lista de usuarios y tiempo de un ranking
	 */
	public List<Pair> getRanking() {
		Collections.sort(tempsPerUsuari);
		return this.tempsPerUsuari;
	}
	
	/**
	 * Añade un usuario y un tiempo al ranking de un kakuro
	 * @param p Par con un usuario y un tiempo
	 */
	public void afegirTemps(Pair p) {
		this.tempsPerUsuari.add(p);
		Collections.sort(tempsPerUsuari);
		
	}
	
	/**
	 * Modifica la lista de los tres mejores tiempos de un ranking
	 */
	public void setRecords() {
		this.records.clear();
		
		ListIterator<Pair> it = tempsPerUsuari.listIterator();
		if (it.hasNext()) this.records.add(it.next());
		if (it.hasNext()) this.records.add(it.next());
		if (it.hasNext()) this.records.add(it.next()); 
	}
	
	/**
	 * Guarda en el ranking de un kakuro un nuevo valor
	 * @param nomJ Nombre de usuario
	 * @param temps Tiempo de resolver el kakuro
	 */
	public void guardarRanking(String nomJ, Double temps) {
		Pair p = new Pair();
		p.setKey(nomJ);
		p.setValue(temps);
		this.tempsPerUsuari.add(p);
		Collections.sort(tempsPerUsuari);
		setRecords();
	}
	
	
	/**
	 * Consulta los records de un kakuro
	 * @return Devuelve la lista de pares de los mejores 3 valores
	 */
	public List<Pair> getRecords() {
		setRecords();
		return this.records;
	}
}