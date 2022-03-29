/**
 * Clase Generar
 */
package Dominio.Controladores;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Dominio.Clases.*;
import Persistencia.CtrlPersistencia;
/**
 * Clase encargada de las funciones relativas al caso de uno Generar
 * @author Martñ Westermeyer y Fñlix Forroll
 */
public class Generar {
	/**
	 * Matriz de tipo Cell para la partida actual
	 */
	private Cell[][] tablero;
	  /**
	   * Nñmero de filas
	   */
	  private int n;
	  /**
	   * Nñmero de columnas
	   */
	  private int m;
	
	  /** 
	   * Genera un kakuro con ciertas limitaciones
	   * @return kakuro inicialzado
	   */
	 public kakuro generar(int n, int m, String dificultad) {
	  	        
	    	int numeroBlancas = 0;
	    	this.n = n;
	    	this.m = m;
	        int porcentaje_dificultad = 0;
	        
 	        if(dificultad.equals("Fàcil")) {
 	        	porcentaje_dificultad = 20;
 	        }else if(dificultad.equals("Mediana")) {
 	        	porcentaje_dificultad = 40;
 	        }else if(dificultad.equals("Difñcil")) {
 	        	porcentaje_dificultad = 60;
 	        }
	        
	        
	        tablero = new Cell[n][m];
	        boolean conexo = false;
	        boolean distcorrecta = false;
	        String tipoCelda; 
	        while (!conexo || !distcorrecta) {
	        	numeroBlancas = 0;

	        	for (int c = 0; c < m; ++c) {
	        		tablero[0][c] = new Negra();
	        	}
	        	for (int f = 0; f < n; ++f) {
	        		tablero[f][0] = new Negra();
	        	}
	        	int proB;
	            if (n%2 == 0) { //par
	            	
	            	for (int f = 1; f < n/2; ++f) {
	            		for (int c = 1; c < m; ++c) {
	            			proB = comprobarProbabilidad(f,c);
	        				if (proB < porcentaje_dificultad) tablero[f][c] = new Blanca();
	        				else tablero[f][c] = new Negra();
	            		}
	            	}
	            	int aux = 2;
	            	for (int f = n/2+1; f < n; ++f) {
	            		for (int c = 1; c < m; ++c) {
	            			tipoCelda = tablero[f-aux][m-c].getClass().getSimpleName();
	            			if (tipoCelda.equals("Blanca")) tablero[f][c] = new Blanca();
	            			else tablero[f][c] = new Negra();
	            		}
	            		aux+=2;
	            	}
	            	//fila medio
	            	int aux2;
	    			if (m%2 == 0) {
	    				aux = m/2+1;
	    				aux2 = m/2;

	    			}
	    			else {
	    				aux = (m+1)/2;
	    				aux2 = m/2+1;
	    			}
	            	for (int c = 1; c < aux2; ++c) {
	        			proB = comprobarProbabilidad(n/2,c);
	    				if (proB < porcentaje_dificultad) tablero[n/2][c] = new Blanca();
	    				else tablero[n/2][c] = new Negra();
	    				if (tablero[n/2-1][c].getClass().getSimpleName().equals("Negra") && tablero[n/2-1][m-c].getClass().getSimpleName().equals("Negra"))
	    					tablero[n/2][c] = new Negra();
	            	}
	            	if (m%2 == 0) {
	     				if (tablero[n/2][m/2-1].getClass().getSimpleName().equals("Blanca") && tablero[n/2][m/2-2].getClass().getSimpleName().equals("Negra") ||
	     					tablero[n/2-1][m/2].getClass().getSimpleName().equals("Blanca") && tablero[n/2-2][m/2].getClass().getSimpleName().equals("Negra"))
	     					tablero[n/2][m/2] = new Blanca();
	     				else  tablero[n/2][m/2] = new Negra();
	            	}
	            	for (int c = aux; c < m; ++c) {            		
	        			tipoCelda = tablero[n/2][m-c].getClass().getSimpleName();
	        			if (tipoCelda.equals("Blanca")) tablero[n/2][c] = new Blanca();
	        			else tablero[n/2][c] = new Negra();
	            	}
	            }
	            
	            else { //impar
	            	
	            	for (int f = 1; f <= (n-1)/2; ++f) {
	            		for (int c = 1; c < m; ++c) {
	            			proB = comprobarProbabilidad(f,c);
	        				if (proB < porcentaje_dificultad) tablero[f][c] = new Blanca();
	        				else tablero[f][c] = new Negra();
	            		}
	            	}
	            	for (int c = 1; c < m; ++c) {
	        				if (tablero[(n-1)/2-1][c].getClass().getSimpleName().equals("Negra") && tablero[(n-1)/2][m-c].getClass().getSimpleName().equals("Negra"))
	        					tablero[(n-1)/2][c] = new Negra();
	            	}

	            	int aux = 1;
	            	for (int f = (n-1)/2+1; f < n; ++f) {
	            		for (int c = 1; c < m; ++c) {
	            			tipoCelda = tablero[f-aux][m-c].getClass().getSimpleName();
	            			if (tipoCelda.equals("Blanca")) tablero[f][c] = new Blanca();
	            			else tablero[f][c] = new Negra();
	            		}
	            		aux+=2;
	            	}
	            }
	            
	            int x = 0,y = 0;
	            boolean trobat = false;
	            
	            for (int i = 0; i < n; ++i ) {
	                for (int j = 0; j < m; ++j ) {
	                	if (tablero[i][j].getClass().getSimpleName().equals("Blanca")) {
	                		++numeroBlancas;
	                		if (!trobat) {
	                			trobat = true;
	                			x = i;
	                			y = j;
	                		}
	                	}
	                }
	            }

	            boolean[][] visitado = new boolean[n][m];
	            ArrayList<Cell> recorrido = new ArrayList<Cell>();
	            conexo = rec(visitado,recorrido,x,y,numeroBlancas);
	            distcorrecta = distCorrecta();
	        }
	        for (int i = 0; i < n; ++i ) {
	            for (int j = 0; j < m; ++j ) {
	            	if (tablero[i][j].getClass().getSimpleName().equals("Negra")) {
	            		if (i == n-1 && j == m-1) tablero[i][j] = new NoJugable();
	            		else {
	                    	if (i == n-1) {
	                    		if (tablero[i][j+1].getClass().getSimpleName().equals("Blanca")) tablero[i][j] = new Jugable();
	                    		else tablero[i][j] = new NoJugable();
	                    	}
	                    	else if (j == m-1) {
	                    		if (tablero[i+1][j].getClass().getSimpleName().equals("Blanca")) tablero[i][j] = new Jugable();
	                    		else tablero[i][j] = new NoJugable();
	                    	}                    
	                    	else if(tablero[i+1][j].getClass().getSimpleName().equals("Blanca") || tablero[i][j+1].getClass().getSimpleName().equals("Blanca")) tablero[i][j] = new Jugable();
	                		else tablero[i][j] = new NoJugable();
	            		}
	            	}
	            }
	        }
	        definirNumeros();
	        //System.out.println(validar_kakuro());
	        int fileCount = CtrlPersistencia.tamañoDirectorio();
	        kakuro k = new kakuro(tablero,n,m,numeroBlancas,fileCount);
	        return k;
	  }
	  
	 /**
	  * obtiene la probabilidad de la celda dependiendo de ciertas limitaciones
	  * @param f ñndice de fila
	  * @param c ñndice de columna
	  * @return La probabilidad averiguada
	  */
	  private int comprobarProbabilidad(int f, int c) {
		  int proB;
		  if (c == m-1 && tablero[f][c-1].getClass().getSimpleName().equals("Negra")) return 100; 

		  if (f >= 2 && tablero[f-1][c].getClass().getSimpleName().equals("Blanca") && tablero[f-2][c].getClass().getSimpleName().equals("Negra"))
			  return 0;
		  if (c >= 2 && tablero[f][c-1].getClass().getSimpleName().equals("Blanca") && tablero[f][c-2].getClass().getSimpleName().equals("Negra"))
			  return 0;
		  else {
			  Random rr = new Random();
			  proB = rr.nextInt(100) + 1;
			  return proB;
		  }
	  }
	  /**
	   * DFS para comprobar que el kakuro es conexo
	   * @param visitado Matriz que marca las celdas ya visitadas
	   * @param recorrido Arraylist que contiene el contenido del recorrido realizado
	   * @param x ñndice de fila
	   * @param y ñndice de columna
	   * @param numeroBlancas Nñmero de blancas que dispone el tablero
	   * @return True si el kakuro es conexo, en caso contrario False
	   */
	  private boolean rec(boolean[][] visitado, ArrayList<Cell> recorrido, int x, int y, int numeroBlancas) {
		  if (x >= 0 && x < n && y >= 0 && y < m) {
			  if (!visitado[x][y]) {
				  visitado[x][y] = true;
				  if (tablero[x][y].getClass().getSimpleName().equals("Blanca")) {
					  recorrido.add(tablero[x][y]);
					  if (recorrido.size() == numeroBlancas) return true;
				  }
				  if (tablero[x][y].getClass().getSimpleName().equals("Negra")) return false;
				  return (rec(visitado, recorrido, x, y+1,numeroBlancas) || rec(visitado, recorrido, x, y-1,numeroBlancas) || rec(visitado, recorrido, x+1, y,numeroBlancas) || rec(visitado, recorrido, x-1, y,numeroBlancas));
			  }
		  }
		  return false;
	  }
	  /**
	   * Comprueba que no haya filas o columnas de blancas seguidas mayor que 9
	   * @return True si cumple esta distribuciñn, en caso contrario false
	   */
	  private boolean distCorrecta() {
		  int contadorF = 0;
		  int contadorC = 0;

		  for (int i = 0; i < n; i++) {
			  for (int j = 0; j < m; j++) {
				  if (tablero[i][j].getClass().getSimpleName().equals("Blanca")) ++contadorF;
				  else if (tablero[i][j].getClass().getSimpleName().equals("Negra")) contadorF = 0;
				  if (contadorF > 9) return false;
			  }
			  contadorF = 0;
		  }
		  for (int i = 0; i < m; i++) {
			  for (int j = 0; j < n; j++) {
				  if (tablero[j][i].getClass().getSimpleName().equals("Blanca")) ++contadorC;
				  else if (tablero[j][i].getClass().getSimpleName().equals("Negra")) contadorC = 0;
				  if (contadorC > 9) return false;
			  }
			  contadorC = 0;
		  }

		  return true;
	  }
	  /**
	   * Asigna valor a las celdas Blancas comprobando que cumpla las normas de juego
	   */
	  private void definirNumeros() {
		  Random rr = new Random();
		  int num;
		  int x= 0;

		  num = rr.nextInt(9) + 1;
		  for (int i = 1; i < n; i++) {
			  for (int j = 1; j < m; j++) {
				  if (tablero[i][j].getClass().getSimpleName().equals("Blanca")) {
					  while (!numeroCorrecto(num,i,j)) {
						  num = rr.nextInt(9) + 1;
					  }
					  ((Blanca)tablero[i][j]).setNumeroCorrecto(num);
				  }
			  }
		  }
		  boolean correcto;
		  for (int i = 0; i < n; i++) {
			  for (int j = 0; j < m; j++) {
				  if (tablero[i][j].getClass().getSimpleName().equals("Jugable")) {
					  correcto = ((Jugable)tablero[i][j]).sumafila(true);
					  correcto = ((Jugable)tablero[i][j]).sumacol(true);
				  }
			  }
		  }
	  }
	  
	  /**
	   * Comprueba que el nñmero introducido cumple las normas de juego
	   * @param num Nuevo nñmero propuesto
	   * @param i ñndice de fila
	   * @param j ñndice de columna
	   * @return True si el nñmero se puede escribir en esa posicion, en caso contrario False
	   */
	  private boolean numeroCorrecto(int num, int i, int j) {
		  int jugableF, jugableC;
		  jugableF = consultarJugableF(i,j);
		  jugableC = consultarJugableC(i,j);

		  //fila
	   	  if (!((Jugable)tablero[i][jugableF]).add_setfila(num)) return false;

		  //columna
		  if (!((Jugable)tablero[jugableC][j]).add_setcol(num)) {
			  ((Jugable)tablero[i][jugableF]).del_setfila(num);
			  return false;
		  }
		  return true;
	  }
	  
		/**
		 * Consulta la casilla Jugable de su respectiva fila
		 * @param x ñndice de Fila
		 * @param y ñndice de Columna
		 * @return Posiciñn de la Jugable, o -1 si no hay
		 */
	  private int consultarJugableF (int x, int y) {
		  for(int i = y; i >= 0; --i) {
			  if (tablero[x][i].getClass().getSimpleName().equals("Jugable")) return i;
		  }
		  return -1;
	  }
	  
		/**
		 * Consulta la casilla Jugable de su respectiva columna
		 * @param x ñndice de Fila
		 * @param y ñndice de Columna
		 * @return Posiciñn de la Jugable, o -1 si no hay
		 */
	  private int consultarJugableC (int x, int y) {
		  for(int j = x; j >= 0; --j) {
			  if (tablero[j][y].getClass().getSimpleName().equals("Jugable")) return j;
		  }
		  return -1;
	  }
	  
	   
	   }

