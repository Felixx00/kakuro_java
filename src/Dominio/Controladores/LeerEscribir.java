package Dominio.Controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Dominio.Clases.*;
import Persistencia.CtrlPersistencia;
/**
 * Clase encargada de las funciones relativas a las lecturas y escrituras
 * @author Fñlix Forroll
 */

public class LeerEscribir {
	
	
	/** 
	 * Inicializa el tablero con los parñmetros correctos de la matriz auxiliar
	 * @param matriz_auxiliar  Matriz de String de formato leñdo
	 * @param n Nñmero filas
	 * @param m Nñmero columnas
	 * @param id Id del kakuro
	 * @return Kakuro inicializado
	 */
	  public kakuro crearMatriz(List<List<String>> matriz_auxiliar, int n, int m, int id){
		    Cell[][] tablero = new Cell[n][m];
		    int nb = 0;
		    for (int i = 1; i < n+1; i++) {
		      for (int j = 0; j < m; j++) {
		    
		        String aux = matriz_auxiliar.get(i).get(j);

		        if(aux.charAt(0) == '*'){
		          tablero[i-1][j] = new NoJugable(); //Negra(No jugable)
		        }
		        else if(aux.charAt(0) == '?'){   
		          tablero[i-1][j] = new Blanca(); //Blanca a llenar


		        }
		        else if(aux.charAt(0) == 'C' || aux.charAt(0) == 'F'){

		        	tablero[i-1][j] = new Jugable();
		          if(aux.charAt(0) == 'C' && aux.contains("F")){
		            aux = aux.replaceFirst("C","");
		            String[] valores = aux.split("F");
		            ((Jugable)tablero[i-1][j]).setnum_Fila(Integer.parseInt(valores[1]));
		            ((Jugable)tablero[i-1][j]).setnum_Columna(Integer.parseInt(valores[0]));

		          }
		          else if(aux.charAt(0) == 'F'){
		            aux = aux.replaceFirst("F","");
		            ((Jugable)tablero[i-1][j]).setnum_Fila(Integer.parseInt(aux));

		          }
		          else{
		            aux = aux.replaceFirst("C","");
		            ((Jugable)tablero[i-1][j]).setnum_Columna(Integer.parseInt(aux));
		          }


		        }
		        else{
		          tablero[i-1][j] = new Blanca();
		          ++nb;
		          ((Blanca)tablero[i-1][j]).setNumero(Integer.parseInt(matriz_auxiliar.get(i).get(j)));
		        }
		      }
		    }
		    //System.out.println(n + " " + m + " " + nb + " " + id);
		    kakuro k = new kakuro(tablero, n, m, nb, id);
		    return k;
		  }

		  /**
		   * Lee un formato de kakuro desde un fichero
		   * @return Kakuro inicializado
		   */
		  public kakuro leerkakurotxt() {

			    List<List<String>> matriz_auxiliar = new ArrayList<>();
			    
			    int fileCount = CtrlPersistencia.tamañoDirectorio();
			    if (fileCount == 0) return new kakuro();
			    int num_kakuro = 0;
			    Scanner s = new Scanner(System.in);
			    
			    while(num_kakuro > fileCount || num_kakuro <= 0) {
			    	System.out.println("Elige numero de kakuro (1 - " + fileCount + "):");
			        num_kakuro = s.nextInt();
			    }
			    matriz_auxiliar = CtrlPersistencia.leerkakurotxt(num_kakuro);

			    //Pasa a int las dimensiones de la matriz
			    int n = Integer.parseInt(matriz_auxiliar.get(0).get(0));
			    int m = Integer.parseInt(matriz_auxiliar.get(0).get(1));
			  
			    return crearMatriz(matriz_auxiliar, n, m, num_kakuro);    
		  }
		  
		  
		  /**
		   * Lee el fichero de la partida guardada
		   * @return La partida guardada
		   */
		  public kakuro cargar_partida() {

			    List<List<String>> matriz_auxiliar = new ArrayList<>();
			    		
			    if (!(CtrlPersistencia.hayGuardada())) return new kakuro();
			    
			    int num_kakuro = 0;
			    
			    matriz_auxiliar = CtrlPersistencia.leerkakurotxt(num_kakuro);

			    //Pasa a int las dimensiones de la matriz
			    int n = Integer.parseInt(matriz_auxiliar.get(0).get(0));
			    int m = Integer.parseInt(matriz_auxiliar.get(0).get(1));
			    
			    //System.out.println(matriz_auxiliar.get(n+1).get(0));
			    //x = Long.parseLong(matriz_auxiliar.get(n+1).get(0));
			  
			    return crearMatriz(matriz_auxiliar, n, m, num_kakuro);    
		  }
		  
		  /**
		   * Lee el tiempo de la partida guardada
		   * @return Tiempo de partida
		   */
		  public long cargar_tiempo() {

			    List<List<String>> matriz_auxiliar = new ArrayList<>();
	    					    
			    int num_kakuro = 0;
			    
			    matriz_auxiliar = CtrlPersistencia.leerkakurotxt(num_kakuro);

			    //Pasa a int las dimensiones de la matriz
			    int n = Integer.parseInt(matriz_auxiliar.get(0).get(0));
			    			
			    long x = Long.parseLong(matriz_auxiliar.get(n+1).get(0));
			  
			    return x;    
		  }
		  
		  
		  /**
		   * Lee un formato de kakuro introducido por la consola	
		   * @return Kakuro inicializado
		   */
		  public kakuro leerkakurocmd() {

		    List<List<String>> matriz_auxiliar = new ArrayList<>();
		    String line = "";
			Scanner s = new Scanner(System.in);
			System.out.print("Introduce dimensiones: ");
			int n = s.nextInt();
			int m = s.nextInt();
			 
			 String dimensions = Integer.toString(n) + ',' +  Integer.toString(m);
			 //System.out.println(dimensions);
			 
			 matriz_auxiliar.add(Arrays.asList(dimensions));	 
			System.out.print("Introduce kakuro: ");
			
			 for (int i = 0; i < n ; ++i) {
				line = s.next();
			    String[] values = line.split(",");
			    matriz_auxiliar.add(Arrays.asList(values));
			   
			 }
			 int fileCount = CtrlPersistencia.tamañoDirectorio();
			 return crearMatriz(matriz_auxiliar, n, m, fileCount+1);
		  }
		  /**
		   * Escribe un kakuro con el formato correcto por consola
		   * @param k Kakuro ya inicializado
		   * @param solucion Equivale a 0 si lo queremos escribir sin resolver, 1-4 para escribir con soluciñn
		   */
		  //0 = no resuelto, 1 = primera solucion, 2 = segunda solucion (si la hay), 3 = tercera solucion (si hay), 4 = cuarta solucion(si hay)
		  public void escribirkakuro(kakuro k, int solucion){

		    String tipo;
		    int n = k.getN();
		    int m = k.getM();
		    Cell[][] tablero = k.getTablero();
		    
		    for(int i = 0; i < n; i++){
		      for(int j = 0; j < m; j++){
		    	  //System.out.print(i + " "  + j);
		        tipo = tablero[i][j].getClass().getSimpleName();
		        if(tipo.equals("Jugable")){
		          if(((Jugable)tablero[i][j]).getnum_Columna() == 0){
		            //fila
		            System.out.print("F" + ((Jugable)tablero[i][j]).getnum_Fila());
		          }
		          else if(((Jugable)tablero[i][j]).getnum_Fila() == 0){
		            //columna
		            System.out.print("C" + ((Jugable)tablero[i][j]).getnum_Columna());
		          }
		          else{
		            //las dos
		            System.out.print("C" + ((Jugable)tablero[i][j]).getnum_Columna() + "F" + ((Jugable)tablero[i][j]).getnum_Fila());
		          }
		          
		        }
		        else if(tipo.equals("NoJugable")){
		          System.out.print("*");
		        }
		        else if(tipo.equals("Blanca")){
		        	
		          if(solucion == 0) {
			          int x = ((Blanca)tablero[i][j]).getNumero();
			          if(x == 0){
			            System.out.print("?");
			          }
			          else{
			            System.out.print(x);
			          	}
			          }
		          else if(solucion == 1) {
		              int x = ((Blanca)tablero[i][j]).getNumeroCorrecto();
		              if(x == 0){
		                System.out.print("?");
		              }
		              else{
		                System.out.print(x);
		              	}
		          }          
		        }
		        System.out.print(" ");
		      }
		      System.out.println(" ");
		    }
		    System.out.println(" ");
		  }
		 

		  /**
		   * Almacena un kakuro en un nuevo fichero de Data/Kakuros/
		   * @param k Kakuro ya inicializado
		   */
		  public void guardar_kakuro(kakuro k) {
			  List<List<String>> matriz_fichero = new ArrayList<>();
			  String tipo;
		      int n = k.getN();
		      int m = k.getM();
		      Cell[][] tablero = k.getTablero();

			  String[] array_tamaño = {Integer.toString(n), Integer.toString(m)};
			  matriz_fichero.add(Arrays.asList(array_tamaño));

			  for(int i = 0; i < n; i++){
				  String[] info_casilla = new String[m];
			      for(int j = 0; j < m; j++){
			    	  
			    	  //System.out.print(i + " "  + j);
			        tipo = tablero[i][j].getClass().getSimpleName();
			        if(tipo.equals("Jugable")){
			          if(((Jugable)tablero[i][j]).getnum_Columna() == 0){
			            //fila
			            info_casilla[j] = (("F" + ((Jugable)tablero[i][j]).getnum_Fila()));
			          }
			          else if(((Jugable)tablero[i][j]).getnum_Fila() == 0){
			            //columna
			        	  info_casilla[j] = ("C" + ((Jugable)tablero[i][j]).getnum_Columna());
			          }
			          else{
			            //las dos
			        	  info_casilla[j] = ("C" + ((Jugable)tablero[i][j]).getnum_Columna() + "F" + ((Jugable)tablero[i][j]).getnum_Fila());
			          }
			        }
			        else if(tipo.equals("NoJugable")){
			        	info_casilla[j] = "*";
			        }
			        else if(tipo.equals("Blanca")){	   
			        	info_casilla[j] = "?"; 
			        }
			        
			      }
			      matriz_fichero.add(Arrays.asList(info_casilla));
			    }

			  System.out.println(" ");
			  
			  int fileCount = CtrlPersistencia.tamañoDirectorio();
			  fileCount++;
			  
			  k.setid(fileCount);
			  CtrlPersistencia.guardarkakuro(matriz_fichero, fileCount);
		  }
		  /**
		   * convierte un kakuro de tipo kakuro a matris de lista
		   * @param k Kakuro ya inicializado
		   * @param solucion 0 si desea convertirlo con las blancas vacias, 1 con la soluciñn del kakuro en las blancas
		   * @return el kakuro convertido a matriz de listas
		   */
		  public List<List<String>> convertirKakuroMatriz(kakuro k, int solucion) {
			  
			  List<List<String>> matriz_fichero = new ArrayList<>();
			  String tipo;
		      int n = k.getN();
		      int m = k.getM();
		      Cell[][] tablero = k.getTablero();

			  String[] array_tamaño = {Integer.toString(n), Integer.toString(m)};
			  matriz_fichero.add(Arrays.asList(array_tamaño));

			  for(int i = 0; i < n; i++){
				  String[] info_casilla = new String[m];
			      for(int j = 0; j < m; j++){
			    	  
			    	  //System.out.print(i + " "  + j);
			        tipo = tablero[i][j].getClass().getSimpleName();
			        if(tipo.equals("Jugable")){
			          if(((Jugable)tablero[i][j]).getnum_Columna() == 0){
			            //fila
			            info_casilla[j] = (("F" + ((Jugable)tablero[i][j]).getnum_Fila()));
			          }
			          else if(((Jugable)tablero[i][j]).getnum_Fila() == 0){
			            //columna
			        	  info_casilla[j] = ("C" + ((Jugable)tablero[i][j]).getnum_Columna());
			          }
			          else{
			            //las dos
			        	  info_casilla[j] = ("C" + ((Jugable)tablero[i][j]).getnum_Columna() + "F" + ((Jugable)tablero[i][j]).getnum_Fila());
			          }
			        }
			        else if(tipo.equals("NoJugable")){
			        	info_casilla[j] = "*";
			        }
			        else if(tipo.equals("Blanca")){
			        	
				          if(solucion == 0) {
					          int x = ((Blanca)tablero[i][j]).getNumero();
					          if(x == 0){
					        	  info_casilla[j] = "?"; 
					          }
					          else{
					        	  info_casilla[j] = String.valueOf(x);
					          	}
					          }
				          else if(solucion == 1) {
				              int x = ((Blanca)tablero[i][j]).getNumeroCorrecto();
				              
				              if(x == 0){
				            	  
				            	  info_casilla[j] = "?";
				              }
				              else{
				            	  info_casilla[j] = String.valueOf(x);
				              	}
				          }          
				        }
			      }
			      matriz_fichero.add(Arrays.asList(info_casilla));
			    }
			  return matriz_fichero;
		  }

		  /**
		   * Guarda en fichero la partida
		   * @param tablero Tablero de la partida a guardar
		   * @param tiempo Tiempo de la partida a guardar
		   */
		  public void guardar_partida(kakuro k, long tiempo) {
		      Cell[][] tablero = k.getTablero();
              int n = k.getN();
              int m = k.getM();
			  List<List<String>> matriz_fichero = new ArrayList<>();
			  String tipo;
			  
			  String[] array_tamaño = {Integer.toString(n), Integer.toString(m)};
			  matriz_fichero.add(Arrays.asList(array_tamaño));
			  
			  for(int i = 0; i < n; i++){
				  String[] info_casilla = new String[m];
			      for(int j = 0; j < m; j++){
			    	  
			    	  //System.out.print(i + " "  + j);
			        tipo = tablero[i][j].getClass().getSimpleName();
			        if(tipo.equals("Jugable")){
			          if(((Jugable)tablero[i][j]).getnum_Columna() == 0){
			            //fila
			            info_casilla[j] = (("F" + ((Jugable)tablero[i][j]).getnum_Fila()));
			          }
			          else if(((Jugable)tablero[i][j]).getnum_Fila() == 0){
			            //columna
			        	  info_casilla[j] = ("C" + ((Jugable)tablero[i][j]).getnum_Columna());
			          }
			          else{
			            //las dos
			        	  info_casilla[j] = ("C" + ((Jugable)tablero[i][j]).getnum_Columna() + "F" + ((Jugable)tablero[i][j]).getnum_Fila());
			          }
			        }
			        else if(tipo.equals("NoJugable")){
			        	info_casilla[j] = "*";
			        }
			        else if(tipo.equals("Blanca")){	  
			        	if(((Blanca)tablero[i][j]).getNumero() == 0) {
			        		info_casilla[j] = "?"; 
			        	}else {
			        		info_casilla[j] = (Integer.toString(((Blanca)tablero[i][j]).getNumero()));
			        	}
			        }
			      }
			      matriz_fichero.add(Arrays.asList(info_casilla));
			    }
			  
			  String[] segundos = {Long.toString((tiempo))};
			  matriz_fichero.add(Arrays.asList(segundos));
			  
			  System.out.println(" ");
			  
			  CtrlPersistencia.guardarpartida(matriz_fichero);
		  }
		  
}
