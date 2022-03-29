package Persistencia;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Dominio.Clases.Pair;
import Dominio.Clases.Usuario;
import Dominio.Clases.kakuro;
/**
 * Controladora de Persistencia
 * @author Mart� Westermeyer
 */
public class CtrlPersistencia {
	/**
	 * Lee el fichero de Usuarios 
	 * @return Matriz con los Usuarios
	 */
    public static List<List<String>> cargarTablaUsuarios() {
        
	    List<List<String>> matriz = new ArrayList<>();
        
	    try (BufferedReader br = new BufferedReader(new FileReader("../Data/usuarios.txt"))) {
		      String line;
		      while ((line = br.readLine()) != null) {
		        String[] values = line.split(" ");
		        matriz.add(Arrays.asList(values));
		      }
		    } 
		    catch (Exception e) {
		      System.out.println(e);
		    }
	    
        return matriz;
    }
    /**
     * Modifica el fichero de Usuarios si ha habido cambios
     * @param taula Set de Usuarios
     */
    public static void guardarTablaUsuarios(HashSet<Usuario> taula) throws IOException {
                
        FileWriter writer = new FileWriter("../Data/usuarios.txt");
        
        for (Usuario fila : taula) {
            writer.write(fila.getUsuario());
            writer.write(" ");
            writer.write(fila.getContraseña());
		    writer.write("\r\n");
	    }
        writer.close();
    }
    
    
    /**
	 * Lee el fichero de kakX.txt (X corrsponde a idKakuro)
	 * @param id del kakuro a cargar sus rankings
	 * @return Matriz con el ranking del kakuro correspondiente a idKakuro
	 */
    public static List<List<String>> cargarRanking(int idKakuro) {
	    List<List<String>> matriz = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader("../Data/Rankings/kak" + idKakuro + ".txt"))) {
			     String line;
			     while ((line = br.readLine()) != null) {
			       String[] values = line.split(" ");
			       matriz.add(Arrays.asList(values));
			     }
			   } 
			   catch (Exception e) {
			     System.out.println(e);
			   }
	    	    
        return matriz;
    }
    
    
    /**
     * Modifica el fichero de kakX.txt (X corrsponde a idKakuro) si ha habido cambios
     * @param list Lista de Pairs de nombreUsuario y tiempo, 
     * @param idKakuro Integer correspondiente al kakuro
     */
    public static void guardarRanking(List <Pair> list, Integer idKakuro) throws IOException {
        FileWriter writer = new FileWriter("../Data/Rankings/kak" + idKakuro + ".txt");
        for (Pair fila : list) {
            writer.write(fila.getKey());
            writer.write(" ");
            writer.write(String.valueOf(fila.getValue()));
		    writer.write("\r\n");
	    }
        writer.close();
    }
    /**
     * Lee un archivo .txt almacenado en la carpeta de persistencia.
     * @param num_kakuro N�mero identificador del kakuro a leer.
     * @return El kakuro le�do de Data formateado.
     */
    public static List<List<String>> leerkakurotxt(int num_kakuro) {
    	List<List<String>> matriz_auxiliar = new ArrayList<>();
    	if (num_kakuro == 0)  {
    	   	try (BufferedReader br = new BufferedReader(new FileReader("../Data/guardada.txt"))) {
  		      String line;
  		      while ((line = br.readLine()) != null) {
  		        String[] values = line.split(",");
  		        matriz_auxiliar.add(Arrays.asList(values));
  		      }
  		    } 
  		    catch (Exception e) {
  		      System.out.println(e);
  		    }
    	}
    	else {
        	try (BufferedReader br = new BufferedReader(new FileReader("../Data/Kakuros/kak" + num_kakuro + ".txt"))) {
  		      String line;
  		      while ((line = br.readLine()) != null) {
  		        String[] values = line.split(",");
  		        matriz_auxiliar.add(Arrays.asList(values));
  		      }
  		    } 
  		    catch (Exception e) {
  		      System.out.println(e);
  		    }
    	}
	    return matriz_auxiliar;
    }
    /**
     * Guarda un kakuro en la carpeta de persistencia.
     * @param matriz_fichero Matriz del kakuro.
     * @param fileCount Id del kakuro a introducir.
     */
    public static void guardarkakuro(List<List<String>> matriz_fichero, int fileCount) {
	      int n = Integer.parseInt(matriz_fichero.get(0).get(0));
	      int m = Integer.parseInt(matriz_fichero.get(0).get(1));
		  File file = new File("../Data/Kakuros/kak" + fileCount + ".txt"); //initialize File object and passing path as argument  
		  File file2 = new File("../Data/Rankings/kak" + fileCount + ".txt");
		  try   
		  	{  
			  file.createNewFile();  //creates a new file  	 
			  file2.createNewFile();
		  	}   
		  catch (IOException e)   
		  	{  
			  e.printStackTrace();    //prints exception if any  
		 	}
		
		  try {
		      FileWriter myWriter = new FileWriter("../Data/Kakuros/kak" + fileCount + ".txt");
		      
		      myWriter.write(matriz_fichero.get(0).get(0) + ',' + matriz_fichero.get(0).get(1));
		      myWriter.write("\n");
		      
		      for(int i = 1; i <= n; i++) {
		    	  for(int j = 0; j < m; j++) {
		    		  if(j == m-1) {
		    			  myWriter.write(matriz_fichero.get(i).get(j));
		    			  myWriter.write("\n");
		    		  }else {
		    			  myWriter.write(matriz_fichero.get(i).get(j) + ',');
		    		  }
		    		  
		    	  }
		    	  
		      }

		      myWriter.close();
		      System.out.println("Generado el fichero kak" + fileCount + ".txt correctamente.");
		      System.out.println(" ");
		    } catch (IOException e) {
		      System.out.println("Ha ocurrido un error");
		      e.printStackTrace();
		    }
    }
    
    /**
     * Guarda la situaci�n de la partida.
     * @param matriz_fichero Matriz del kakuro.
     */
    
    public static void guardarpartida(List<List<String>> matriz_fichero) {
	      int n = Integer.parseInt(matriz_fichero.get(0).get(0));
	      int m = Integer.parseInt(matriz_fichero.get(0).get(1));
		  File file = new File("../Data/guardada.txt"); //initialize File object and passing path as argument  
		  try   
		  	{  
			  file.createNewFile();  //creates a new file  	   
		  	}   
		  catch (IOException e)   
		  	{  
			  e.printStackTrace();    //prints exception if any  
		 	}
		
		  try {
		      FileWriter myWriter = new FileWriter("../Data/guardada.txt");
		      
		      myWriter.write(matriz_fichero.get(0).get(0) + ',' + matriz_fichero.get(0).get(1));
		      myWriter.write("\n");
		      
		      for(int i = 1; i <= n; i++) {
		    	  for(int j = 0; j < m; j++) {
		    		  if(j == m-1) {
		    			  myWriter.write(matriz_fichero.get(i).get(j));
		    			  myWriter.write("\n");
		    		  }else {
		    			  myWriter.write(matriz_fichero.get(i).get(j) + ',');
		    		  }
		    		  
		    	  }
		    	  
		      }
		      
		      myWriter.write(matriz_fichero.get(n+1).get(0));

		      myWriter.close();
		      System.out.println("Partida guardada correctamente");
		      System.out.println(" ");
		    } catch (IOException e) {
		      System.out.println("Ha ocurrido un error al guardar partida");
		      e.printStackTrace();
		    }
    }
    
	  /**
	   * Elimina un kakuro del repositorio.
	   * @param id del Kakuro a eliminar.
	   */
	  public static void eliminar_kakuro(int id) {
	  
		  File directory = new File("../Data/Kakuros/");
		  int fileCount = directory.list().length;
		  //int id = k.getid();
		  File actual, aux;
		  int numAux;
		  File file = new File("../Data/Kakuros/kak" + id + ".txt"); //initialize File object and passing path as argument  
		  File file2 = new File("../Data/Rankings/kak" + id + ".txt"); 
		  if (file.delete()) System.out.println("El fichero ha sido borrado satisfactoriamente de la carpeta Kakuros");
		  else System.out.println("El fichero no pudo ser borrado");
		  if (file2.delete()) System.out.println("El fichero ha sido borrado satisfactoriamente de la carpeta Rankings");
	      else System.out.println("El fichero no pudo ser borrado");

		  if (fileCount != id) {
			  for (int i = fileCount; i > id; --i) {
				  numAux = i-1;
				  actual = new File("../Data/Kakuros/kak" + i + ".txt");
				  aux = new File("../Data/Kakuros/kak" + numAux + ".txt");
				  if (!(actual.renameTo(aux))) System.out.println("El fichero no pudo ser renombrado");
			  }
			  for (int i = fileCount; i > id; --i) {
				  numAux = i-1;
				  actual = new File("../Data/Rankings/kak" + i + ".txt");
				  aux = new File("../Data/Rankings/kak" + numAux + ".txt");
				  if (!(actual.renameTo(aux))) System.out.println("El fichero no pudo ser renombrado");
			  }
		  }
		  
	  }
    /**
     * Consulta el numero de kakuros almacenados
     * @return numero de kakuros almacenados
     */
    public static int tamañoDirectorio() {
	    File directory = new File("../Data/Kakuros/");
		int fileCount = directory.list().length;
	    return fileCount;
    }
    /**
     * Consulta si hay una partida guardada
     * @return true si la hay, en caso contrario false
     */
    public static boolean hayGuardada() {
	    File file = new File("../Data/guardada.txt");
	    if(file.length() == 0) {
	    	return false;
	    }
	    else return true;
    }
    /**
     * Consulta el archivo ListaPosibilidades para cada combinacion posible
     * @return todas las posibilidades
     */
    public static HashMap<Point, Set<Integer>> generarLookupTable(){
    	HashMap<Point, Set<Integer>> LookupTable = new HashMap<Point, Set<Integer>>();

		  try {
	            BufferedReader in = new BufferedReader(new FileReader("../Data/ListaPosibilidades.txt"));
	            String str;
	      
	            while ((str = in.readLine()) != null) {
	            	String[] result = str.split(",");
	            	//System.out.println("Numero de celdas:"+Integer.parseInt(result[0]) + "Numero:"+Integer.parseInt(result[1]));
	            	int nums = result.length;
	            	int c = Integer.parseInt(result[0]);
	            	int n = Integer.parseInt(result[1]);
	            	Set<Integer> s1 = new HashSet<Integer>();
	            	for(int i = 2; i < nums;++i) {
	            	String[] resultnumbers = result[i].split("\\+");
			       // System.out.print("Combinaciones: ");
			        for(String a : resultnumbers){
			            	int num;
			            	num = Integer.parseInt(a);
			            	//System.out.print(num);
			            	s1.add(num);       
			        	}
			      //  System.out.println("");
			       // System.out.println(l1);
			            	}
	            	LookupTable.put(new Point(c,n), s1);
	            	//System.out.println(LookupTable);
	            	}
	
	            in.close();
	        } 
		  catch (Exception e) {
		      System.out.println(e);
	        } 
		return LookupTable; 
    	
    }
}
