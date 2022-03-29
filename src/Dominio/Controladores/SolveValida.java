package Dominio.Controladores;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Dominio.Clases.*;
import Persistencia.CtrlPersistencia;
/**
 * Clase para a las funciones destinadas a validar un kakuro
 * @author Victor Villarejo
 */


public class SolveValida {
	
	/**
	 * Número de filas
	 */
	private int n;
	
	/**
	 * Número de columnas
	 */
	private int m;
	
	/**
	 * Estructura donde se almacenan todos los posibles candidatos a numero
	 */
	private static HashMap<Point, Set<Integer>> LookupTable;
	
	/**
	 * Estructura que almacena el tablero del kakuro que se va a validar y solucionar
	 */
	private Cell[][]tablero;
	
	/**
	 * Número de soluciones
	 */
	private int numsolucions = 0;
	
	  /** 
	   * Consulta el numero de soluciones
	   * @return número de soluciones
	   */
	public int getnumsolucions() {
		return numsolucions;
	}
	  /** 
	   * Inicializa diversos atributos de las casillas blancas que son necesarias para el resto de la ejecución.
	   * @param el kakuro inicializado 
	   */
	public void initialize(kakuro k) {
		generateLookupTable();
		 n = k.getN();
		 m = k.getM();
		 tablero = k.getTablero();
		
		for(int i = 0; i < n;++i) {
			for(int j = 0; j < m; ++j) {
				int totalValueH = 0;
				int totalValueV = 0;
				int celdasH = 1;
				int celdasV = 1;
				if(tablero[i][j].getClass().getSimpleName().equals("Blanca")){
					
						//RECORRIDO HORIZONTAL
					   for (int l = j + 1; l <= m-1; l++) {
						   if (tablero[i][l].getClass().getSimpleName().equals("Jugable") || tablero[i][l].getClass().getSimpleName().equals("NoJugable")) break;
						   else if (tablero[i][l].getClass().getSimpleName().equals("Blanca")) ++celdasH;
					   	}
					   
					 for (int o = j - 1; o >= 0; o--) {
				            if (tablero[i][o].getClass().getSimpleName().equals("Jugable") || tablero[i][o].getClass().getSimpleName().equals("NoJugable")) {
				                if(tablero[i][o].getClass().getSimpleName().equals("Jugable")) {
				                	totalValueH = ((Jugable) tablero[i][o]).getnum_Fila();
				                	}
				                	break;
				            	}
				          
				            if (tablero[i][o].getClass().getSimpleName().equals("Blanca")) ++celdasH;
				            	}
					 								 
				            ((Blanca) tablero[i][j]).setNumero_fila(totalValueH);
				            ((Blanca) tablero[i][j]).setCeldas_fila(celdasH);
				            
				            //RECORRIDO VERTICAL
				            for (int l = i + 1; l <= n-1; l++) {
								   if (tablero[l][j].getClass().getSimpleName().equals("Jugable") || tablero[l][j].getClass().getSimpleName().equals("NoJugable")) break;
								   else if (tablero[l][j].getClass().getSimpleName().equals("Blanca")) ++celdasV;
							   	}
				            
							 for (int o = i - 1; o >= 0; o--) {
						            if (tablero[o][j].getClass().getSimpleName().equals("Jugable") || tablero[o][j].getClass().getSimpleName().equals("NoJugable")) {
						                if(tablero[o][j].getClass().getSimpleName().equals("Jugable")) {
						                	totalValueV = ((Jugable) tablero[o][j]).getnum_Columna();
						                		}
						                	break;
						            		}
						          
						            if (tablero[o][j].getClass().getSimpleName().equals("Blanca")) ++celdasV;
							 	}
						            ((Blanca) tablero[i][j]).setNumero_columna(totalValueV);
						            ((Blanca) tablero[i][j]).setCeldas_columna(celdasV);
					 	}
				}
		}
	}
	

	
	  /** 
	   * Soluciona el kakuro propuesto por parametro a partir de las dimensiones propuestas
	   * @param row Índice de Fila
	   * @param col Índice de Columna
	   * @param k Kakuro a solucionar
	   * @return True si el kakuro tenia solución. False si no tenia solución
	   */
	
	public boolean solve(int row, int col, kakuro k) {
		
	int n = k.getN();
	int m = k.getM();
	Cell[][]tablero = k.getTablero();

	
	Set<Integer> PosibleSumsH = new HashSet<Integer>();
	Set<Integer> PosibleSumsV = new HashSet<Integer>();
	
	  if (row == n) {
		  k.setTablero(tablero);
        return true;
    }
	  
	  if (col == m) {
        return solve (row + 1, 0,k);
    }
	  
	  if(tablero[row][col].getClass().getSimpleName().equals("Jugable") || tablero[row][col].getClass().getSimpleName().equals("NoJugable")) {
		  return solve (row, col + 1,k);
	  }
	  
	  if (tablero[row][col].getClass().getSimpleName().equals("Blanca")) {
	
		  	int totalValueH = ((Blanca) tablero[row][col]).getNumero_fila();
			int totalValueV = ((Blanca) tablero[row][col]).getNumero_columna();
			int celdasH = ((Blanca) tablero[row][col]).getCeldas_fila();
			int celdasV = ((Blanca) tablero[row][col]).getCeldas_columna();

    	   PosibleSumsH = getSums(celdasH, totalValueH);
    	   PosibleSumsV = getSums(celdasV, totalValueV);  
   
           if(PosibleSumsH.size() > 0 && PosibleSumsV.size() > 0) {
        	   Set<Integer> candidatos = new HashSet<Integer>();
        	   candidatos = intersection(PosibleSumsH, PosibleSumsV);
        	   Iterator <Integer> it = candidatos.iterator();
        			while(it.hasNext()) {			
        				int num = it.next();
        					 if(isValidRow(row,col,num,k) && isValidCol(row,col,num,k)) {	 
    	    					((Blanca) tablero[row][col]).setNumeroCorrecto(num);
    	    					if(solve(row, col + 1,k)) return true;  
        					  }
        				}
           			}

       
           else if(PosibleSumsH.size() == 0 && PosibleSumsV.size() != 0){
        	   Set<Integer> candidatos = PosibleSumsV;
    		   Iterator <Integer> it = candidatos.iterator();
    			  while(it.hasNext()) {
    				  int num = it.next();
    				 if(isValidRow(row,col,num,k) && isValidCol(row,col,num,k)) {	 
    					  ((Blanca) tablero[row][col]).setNumeroCorrecto(num);
    					  if(solve(row, col + 1,k)) return true;  
    				
    				}
    			  } 
    		    }

           else if(PosibleSumsH.size() != 0 && PosibleSumsV.size() == 0) {
        	   Set<Integer> candidatos = PosibleSumsH;
        	   Iterator <Integer> it = candidatos.iterator();
    			while(it.hasNext()) {
    				int num = it.next();
     				if(isValidRow(row,col,num,k) && isValidCol(row,col,num,k)) {	 
    				((Blanca) tablero[row][col]).setNumeroCorrecto(num);
    				if(solve(row, col + 1,k)) return true;  
     				}
     			} 
     		}

	  }
	  return false;
	}	
	  /** 
	   * Soluciona el kakuro propuesto por parámetro a partir de las dimensiones propuestas
	   * @param row Índice de Fila
	   * @param col Índice de Columna
	   * @param k Kakuro a validar
	   * @return True si el kakuro tenia una solución o varias. False si no tenia solución
	   */
	
 public boolean valida(int row, int col,kakuro k) {
		
	 int n = k.getN();
	 int m = k.getM();
	 Cell[][] tablero = k.getTablero();

	
	Set<Integer> PosibleSumsH = new HashSet<Integer>();
	Set<Integer> PosibleSumsV = new HashSet<Integer>();
	
	
	  if (row == n) {
		  ++numsolucions;
		  if(numsolucions == 2) return true;
		  else if(numsolucions == 1) {
			  k.setTablero(tablero);
			  return false;
		  }
    }
	  
	  if (col == m) {
        return valida (row + 1, 0,k);
    }
	  
	  if(tablero[row][col].getClass().getSimpleName().equals("Jugable") || tablero[row][col].getClass().getSimpleName().equals("NoJugable")) {
		  return valida (row, col + 1,k);
	  }
	  
	  	int totalValueH = ((Blanca) tablero[row][col]).getNumero_fila();
		int totalValueV = ((Blanca) tablero[row][col]).getNumero_columna();
		int celdasH = ((Blanca) tablero[row][col]).getCeldas_fila();
		int celdasV = ((Blanca) tablero[row][col]).getCeldas_columna();
		
    	   PosibleSumsH = getSums(celdasH, totalValueH);
    	   PosibleSumsV = getSums(celdasV, totalValueV);  
   
       if(PosibleSumsH.size() > 0 && PosibleSumsV.size() > 0) {
    	   Set<Integer> candidatos = new HashSet<Integer>();
    	   candidatos = intersection(PosibleSumsH, PosibleSumsV);
    	   Iterator <Integer> it = candidatos.iterator();
    			while(it.hasNext()) {			
    				int num = it.next();
    					 if(isValidRow(row,col,num,k) && isValidCol(row,col,num,k)) {	 
	    					((Blanca) tablero[row][col]).setNumeroCorrecto(num);
	    					if(valida(row, col + 1,k)) return true;  
    					  }
    				}
       			}

     
       else if(PosibleSumsH.size() == 0 && PosibleSumsV.size() != 0){
    	   Set<Integer> candidatos = PosibleSumsV;
		   Iterator <Integer> it = candidatos.iterator();
			  while(it.hasNext()) {
				  int num = it.next();
				 if(isValidRow(row,col,num,k) && isValidCol(row,col,num,k)) {	 
					  ((Blanca) tablero[row][col]).setNumeroCorrecto(num);
					  if(valida(row, col + 1,k)) return true;  
				
				}
			  } 
		    }

       

       else if(PosibleSumsH.size() != 0 && PosibleSumsV.size() == 0) {
    	   Set<Integer> candidatos = PosibleSumsH;
    	   Iterator <Integer> it = candidatos.iterator();
			while(it.hasNext()) {
				int num = it.next();
 				if(isValidRow(row,col,num,k) && isValidCol(row,col,num,k)) {	 
				((Blanca) tablero[row][col]).setNumeroCorrecto(num);
				if(valida(row, col + 1,k)) return true;  
 				}
 			} 
 		}
       
	  return false;
 }
 /** 
  * Comprueba si un numero cumple las restricciones del kakuro para una fila.
  	* @param row Índice de Fila.
	* @param col Índice de Columna.
	* @param k Kakuro a validar.
	* @param valor Candidato a número para ser validado en la fila.
	* @return True si el valor es válido. False si no es válido.
  */
  private boolean isValidRow(int row, int col, int valor,kakuro k) {
	        int sum = valor;
	        int totalValue = ((Blanca) tablero[row][col]).getNumero_fila();
			int m = k.getM();
			Cell[][] tablero = k.getTablero();
			
			if (totalValue == 0) return true;
			
	        for (int i = col - 1; i >= 0; i--) {
	            if (tablero[row][i].getClass().getSimpleName().equals("Jugable") || tablero[row][i].getClass().getSimpleName().equals("NoJugable")) break;
	    
	            if (((Blanca) tablero[row][i]).getNumeroCorrecto() == valor) {
	                return false;
	            }
	            sum +=  ((Blanca) tablero[row][i]).getNumeroCorrecto();
	        }

	        if (col == m-1) {
	            if (sum < totalValue) {
	                return false;
	            }
	            
	        } 
	        else if (tablero[row][col+1].getClass().getSimpleName().equals("Jugable") || tablero[row][col+1].getClass().getSimpleName().equals("NoJugable")) {
	            if (sum < totalValue) {
	                return false;
	            }
	        }

	       return true;
	    }
  /** 
   * Comprueba si un numero cumple las restricciones del kakuro para una columna.
   	* @param row Índice de Fila.
 	* @param col Índice de Columna.
 	* @param k Kakuro a validar.
 	* @param valor Candidato a número para ser validado en la columna.
 	* @return True si el valor es válido. False si no es válido.
   */
private boolean isValidCol(int row, int col, int valor,kakuro k) {
        int sum = valor;
        int totalValue = ((Blanca) tablero[row][col]).getNumero_columna();
        int n = k.getN();
		Cell[][] tablero = k.getTablero();
		if (totalValue == 0) return true;
		
        for (int i = row - 1; i >= 0; i--) {
            if (tablero[i][col].getClass().getSimpleName().equals("Jugable") || tablero[i][col].getClass().getSimpleName().equals("NoJugable")) break;
            
            if (((Blanca) tablero[i][col]).getNumeroCorrecto() == valor) {
                return false;
            	}
            sum += ((Blanca) tablero[i][col]).getNumeroCorrecto();
            }
        
        if (sum > totalValue) {
            return false;
        }
       
        if (row == n - 1) {
            if (sum < totalValue) {
                return false;
            }    
        }
        else if (tablero[row+1][col].getClass().getSimpleName().equals("Jugable") || tablero[row+1][col].getClass().getSimpleName().equals("NoJugable")) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }
	/**
	 * Busca la entrada de la lookup table que contiene los posibles candidatos a número de un número y cierto número de casillas
	 * @param sum numero que queremos conseguir
	 * @param numberOfCells numero de celdas que disponemos para conseguirlos
	 * @return La entrada de la lookup table que contiene los posibles candidatos a número
	 */
	private static Set<Integer> getSums(int sum, int numberOfCells){
		return LookupTable.get(new Point(sum, numberOfCells));
	}
	/**
	 * Comprueba si dados dos sets hay algun numero que esta en ambos
	 * @param one primer set 
	 * @param two segundo set
	 * @return Un set con los numeros que se encuentran en los sets dados
	 */
	private static Set<Integer> intersection(Set<Integer> one, Set<Integer> two) {
		Set<Integer> result = new HashSet<Integer>();
		
		for(Integer value : one) {
			if(two.contains(value))
				result.add(value);
		}
		
		return result;
	}
	/** 
	 * Hace una llamada al Controlador de Persistencia para que genere la LookUp Table y la asigne.
	 */
	private void generateLookupTable() {
			LookupTable = CtrlPersistencia.generarLookupTable();
	}
}