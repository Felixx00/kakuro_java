package Dominio.Clases;

/**
 * Clase para las casillas Blancas, donde se colocan los números
 * @author Víctor Villarejo
 */

public class Blanca extends Cell{
	/**
	 * El número que contiene la casilla Blanca.
	 * Por defecto será el 0.
	 */
  private int numero;

  /**
   * Número usado para la comprobación de soluciones.
   */
  private int numero_correcto;
  
  /**
   * Número usado para el numero de la fila al que se juega.
   */
  private int numero_fila;
  /**
   * Número usado para el numero de la columna al que se juega.
   */
  private int numero_columna;
  /**
   * Número usado para el numero de celdas que componen la fila jugable.
   */
  
  private int celdas_fila;
  
  /**
   * Número usado para el numero de celdas que componen la columna jugable.
   */
  
  private int celdas_columna;
  
  
  public Blanca(){
	  
  }
	/**
	 * Creadora de Blanca con parámetros.
	 * @param numero Número introducizo.
	 * @param numero_correcto Número correcto de la solución.
	 */
  public Blanca(int numero, int numero_correcto) {
      this.numero = numero;
      this.numero_correcto = numero_correcto;
  }
  
  
	/**
	 * Consulta el numero escrito en la casilla.
	 * @return Número de casilla.
	 */
  public int getNumero(){
    return numero;
  }
  
	/**
	 * Consulta el número correcto de una casilla de un kakuro.
	 * @return Número correcto de casilla.
	 */
  public int getNumeroCorrecto(){
    return this.numero_correcto;
  }
  
  /**
   * Modifica el número de la casilla.
   * @param x Número a introducir en la casilla.
   */
  public void setNumero(int x){
	  if(x < 10 && x >= 0) {
		  numero = x;
	  }
    
  }
  
/**
 * Asigna el número correcto de la casilla para un kakuro.
 * @param y Número a añadir.
 */
  public void setNumeroCorrecto(int y){
	  if(y < 10 && y >= 0) {
		  this.numero_correcto = y;
	  }
	  
  }
	/**
	 * Consulta el numero de fila
	 * @return Número de fila
	 */ 
  	public int getNumero_fila() {
		return numero_fila;
	}
  	/**
  	 * Asigna el numero pasado como parametro a numero fila.
  	 * @param numero_fila Número a asignar a numero fila.
  	 */
  	
	public void setNumero_fila(int numero_fila) {
		this.numero_fila = numero_fila;
	}
	/**
	 * Consulta el numero de columna
	 * @return Número de columna
	 */ 
	public int getNumero_columna() {
		return numero_columna;
	}
	/**
  	 * Asigna el numero pasado como parametro a numero columna.
  	 * @param numero_columna Número a asignar a numero columna.
  	 */
	public void setNumero_columna(int numero_columna) {
		this.numero_columna = numero_columna;
	}
	
	/**
	 * Consulta el numero de celdas jugables de la fila
	 * @return Número de celdas fila
	 */
	public int getCeldas_fila() {
		return celdas_fila;
	}
	/**
  	 * Asigna el numero pasado como parametro a celdas fila.
  	 * @param celdas_fila Número a asignar a celdas fila.
  	 */
	public void setCeldas_fila(int celdas_fila) {
		this.celdas_fila = celdas_fila;
	}
	/**
	 * Consulta el numero de celdas jugables de la columna
	 * @return Número de celdas columna
	 */
	public int getCeldas_columna() {
		return celdas_columna;
	}
	/**
 	 * Asigna el numero pasado como parametro a celdas columna.
  	 * @param celdas_columna Número a asignar a celdas columna.
  	 */
	public void setCeldas_columna(int celdas_columna) {
		this.celdas_columna = celdas_columna;
	}
}
