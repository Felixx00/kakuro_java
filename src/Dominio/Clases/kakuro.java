/**
 * Clase kakuro
 */
package Dominio.Clases;

/**
 * Clase encargada de las funciones leer, escribir, guardar kakuro en fichero y crear el tablero
 * @author Fèlix Forroll
 */

public class kakuro  {
  /**
   * Matriz de tipo Cell que representa el tablero del kakuro
   */
  private Cell[][] tablero;
  /**
   * Número de filas
   */
  private int n;
  /**
   * Número de columnas
   */
  private int m;
  /** 
   * Número de blancas que dispone el tablero
   */
  private int numeroBlancas;
  /** 
  * Número de identificador del tablero
  */
 private int identificador;
 
  /**
   * Creadora de kakuro
   */
  public kakuro() {
	 
  }
  /**
   * Creadora de kakuro con parámetros
   * @param tablero Matriz de tipo Cell
   * @param n Número de filas
   * @param m Número de columnas
   * @param numeroBlancas Número de blancas que dispone el tablero
   */
  public kakuro(Cell[][] tablero, int n, int m, int numeroBlancas, int id) {
	  this.tablero = tablero;
	  this.n = n;
	  this.m = m;
	  this.numeroBlancas = numeroBlancas;
	  this.identificador= id;
  }
  /**
   * Consulta el tablero
   * @return Tablero
   */
  public Cell[][] getTablero(){
	  return tablero;
  }
  /**
   * Consulta el número de filas
   * @return Número de filas
   */
  public int getN(){
	  return n;
  }
 
  /**
   * Consulta el número de columnas
   * @return Número de columnas
   */
  public int getM(){
	  return m;
  }
  /**
   * Consulta el número de blancas que dispone el tablero
   * @return Número de blancas
   */
  public int getnumeroBlancas(){
	  return numeroBlancas;
  }
  
  /**
   * Consulta el identificador del tablero
   * @return identificador
   */
  public int getid(){
	  return identificador;
  }
  
  /**
   * Modifica el tablero
   * @param tablero Matriz de tipo Cell
   */
  public void setTablero(Cell[][]tablero){
	  this.tablero = tablero;
  }
/**
   * Modifica el número de filas
   * @param Número de filas
   */
  public void setN(int n){
	  this.n = n;
  }
 
  /**
   * Modifica el número de columnas
   * @param Número de columnas
   */
  public void setM(int m){
	  this.m = m;
  }
  /**
   * Modifica el número de blancas que dispone el tablero
   * @param Número de blancas
   */
  public void setnumeroBlancas(int numeroBlancas){
	  this.numeroBlancas = numeroBlancas;
  }
  
  /**
   * Modifica el identificador del tablero
   * @param identificador
   */
  public void setid(int id){
	  this.identificador = id;
  }
  
  
}