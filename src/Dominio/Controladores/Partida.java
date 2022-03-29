/**
 * Clase Partida
 */
package Dominio.Controladores;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import Dominio.Clases.Cell;
import Dominio.Clases.kakuro;
import Dominio.Clases.*;

import java.time.*;
/**
 * Clase encargada de las funciones relativas al caso de uso Jugar
 * @author Marti Westermeyer
 */


public class Partida {
	/**
	 * Matriz de tipo Cell para la partida actual
	 */
	private Cell[][] tableroActivo;
	/**
	 * tiempo de resoucion
	 */
	long tiempo;
	/**
	 * Creadora de Partida vacia
	 */
	public Partida () {
		
	}
	/**
	 * Creadora de Partida con tiempo
	 * @param tiempo tiempo de resolucion
	 */
	public Partida (long tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * Inicia una partida con un determinado kakuro
	 * @param k Kakuro a jugar
	 * @param x fila de la casilla a cambiar
	 * @param y columna de la casilla a cambiar
	 * @param cambio numero a cambiar
	 * @return Si el cambio propuesto es correcto
	 */
	public boolean partida(kakuro k, int x, int y, int cambio) {
		
		tableroActivo = k.getTablero();
		int n = k.getN();
		int m = k.getM();

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (i != x || j != y) {
					if(tableroActivo[i][j].getClass().getSimpleName().equals("Blanca") && ((Blanca)tableroActivo[i][j]).getNumero() != 0) {
				        int jugableF = consultarJugableF(i,j);
				        int jugableC = consultarJugableC(i,j);
				        ((Jugable)tableroActivo[i][jugableF]).add_setfila(((Blanca)tableroActivo[i][j]).getNumero());
				        ((Jugable)tableroActivo[jugableC][j]).add_setcol(((Blanca)tableroActivo[i][j]).getNumero());
		            }
				}
			}
		}

		if (!(comprovarNum(cambio,x ,y))) {
			return false;
		}
		return true;
	}
	
/**
 * Comprueba que el número introducido cumple con las restricciones
 * @param cambio Numero nuevo a escribir
 * @param x Índice de fila
 * @param y Índice de columna
 * @return True si el número se puede escribir en esa posicion, en caso contrario False
 */
	private Boolean comprovarNum(int cambio, int x, int y) {
        int jugableF = consultarJugableF(x,y);
        int jugableC = consultarJugableC(x,y);

        //fila
        if (!(((Jugable)tableroActivo[x][jugableF]).add_setfila(cambio))) {
        	return false;
        }

        //columna
        if (!(((Jugable)tableroActivo[jugableC][y]).add_setcol(cambio))) {
            return false;
        }
        
        if (!(((Jugable)tableroActivo[x][jugableF]).sumafila(false)) || 
                !(((Jugable)tableroActivo[jugableC][y]).sumacol(false))) {
            return false;
        }
        return true;
    }	
	
	
	/**
	 * Consulta la casilla Jugable de su respectiva fila
	 * @param x Índice de Fila
	 * @param y Índice de Columna
	 * @return Posición de la Jugable, o -1 si no hay
	 */
	private int consultarJugableF (int x, int y) {
		for(int i = y; i >= 0; --i) {
			if (tableroActivo[x][i].getClass().getSimpleName().equals("Jugable")) return i;
		}
		return -1;
	}
	/**
	 * Consulta la casilla Jugable de su respectiva columna
	 * @param x Índice de Fila
	 * @param y Índice de Columna
	 * @return Posición de la Jugable, o -1 si no hay
	 */
	private int consultarJugableC (int x, int y) {
		for(int j = x; j >= 0; --j) {
			if (tableroActivo[j][y].getClass().getSimpleName().equals("Jugable")) return j;
		}
		return -1;
	}
	
	/**
	 * Comprueba que el kakuro está completo y correcto
	 * @param k Kakuro a jugar
	 * @return True si el kakuro está resuelto
	 */
	public Boolean resuelto(kakuro k) {
		tableroActivo = k.getTablero();
		int n = k.getN();
		int m = k.getM();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
					if(tableroActivo[i][j].getClass().getSimpleName().equals("Blanca") && ((Blanca)tableroActivo[i][j]).getNumero() != 0) {
				        int jugableF = consultarJugableF(i,j);
				        int jugableC = consultarJugableC(i,j);
				        ((Jugable)tableroActivo[i][jugableF]).add_setfila(((Blanca)tableroActivo[i][j]).getNumero());
				        ((Jugable)tableroActivo[jugableC][j]).add_setcol(((Blanca)tableroActivo[i][j]).getNumero());
		            }
				
			}
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if(tableroActivo[i][j].getClass().getSimpleName().equals("Jugable") &&
						!((Jugable)tableroActivo[i][j]).comprSumas()) return false;
			}
		}
		return true;
	}
}