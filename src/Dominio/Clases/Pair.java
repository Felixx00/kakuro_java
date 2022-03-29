package Dominio.Clases;

/**
 * Clase para guardar pares de valores
 * @author Rubén Montagut
 */

public class Pair implements Comparable<Pair>{
	
	  /**
	   * String como clave primaria en el par.
	   */
	private String Key;
	
	  /**
	   * Número decimal utilizado como valor en el par.
	   */
	private Double Value;
	
	/**
	 * Consulta la clave del par.
	 * @return La clave String del par.
	 */
	public String getKey() {
		return this.Key;
	}
	/**
	 * Consulta el valor del par.
	 * @return El valor Double del par.
	 */
	public Double getValue() {
		return this.Value;
	}
	/**
	 * Asigna una clave al par.
	 * @param key Clave String para asignar como clave al par.
	 */
	public void setKey(String key) {
		this.Key = key;
	}
	/**
	 * Asigna un valor al par.
	 * @param value Valor Double para asignar como valor al par.
	 */
	public void setValue(Double value) {
		this.Value = value;
	}
	
	/**
	 * Compara el valor del par actual con otro par.
	 * @param o El par que se va a comparar.
	 * @return Valor que ha comparado del otro par.
	 */
	@Override
	public int compareTo(Pair o) {
		return Value.compareTo(o.Value);
	}
}
