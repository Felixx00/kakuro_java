package Dominio.Controladores.Tests;

import java.util.*;


import Dominio.Clases.Pair;
import Dominio.Clases.Ranking;

/**
 * Driver del Driver De Ranking
 * @author Rubén Montagut
 */

public class DriverRanking {
	/**
	 * Imprime por pantalla elos valores del Ranking
	 * @param ranking Ranking
	 */
	public static void testGetRanking(Ranking ranking) throws Exception{
        List<Pair> aux = ranking.getRanking();
        ListIterator<Pair> it = aux.listIterator();
        if(it.hasNext()) {
        	while (it.hasNext()) {
            	Pair aux2 = it.next();
            	System.out.println(aux2.getKey() + " " + aux2.getValue());
            }
        }
        else System.out.println("Aun no hay nada en el ranking");
    };
    
    
    /**
	 * Añade al ranking un nombre mas el tiempo que haya hecho
	 * @param ranking Ranking
	 * @param pair Pair<String,Double>
	 */
    public static void testAfegirTemps(Ranking ranking, Pair pair) throws Exception{
    	ranking.afegirTemps(pair);
    };
    
    
    /**
   	 * Configura los records del ranking
   	 * @param ranking Ranking
   	 */
    public static void testSetRecords(Ranking ranking) throws Exception{
        ranking.setRecords();   
    };
    
    
    /**
	 * Imprime por pantalla el valor del tiempo casilla Blanca
	 * @param ranking Ranking
	 * @param nom nombre a añadir al ranking
	 * @param temps tiempo a añadir al ranking
	 */
    public static void testGuardarIOrdenarRanking(Ranking ranking, String nom, Double temps) throws Exception{
    	ranking.guardarRanking(nom, temps);
    };
    
    
    /**
   	 * Imprime por pantalla los valores de les 3 primeros puestos del ranking
   	 * @param ranking Ranking
   	 */
    public static void testGetRecords(Ranking ranking) throws Exception{
        List<Pair> aux = ranking.getRecords();
        ListIterator<Pair> it = aux.listIterator();
        if(it.hasNext()) {
        	while (it.hasNext()) {
            	Pair aux2 = it.next();
            	System.out.println(aux2.getKey() + " " + aux2.getValue());
            }
        }
        else System.out.println("Aun no hay nada en records");
        
    };
    
    
    
    
    public static void main (String [] args) throws Exception{
    	
    	Scanner s = new Scanner(System.in);
    	
    	Ranking ranking = new Ranking();
    	System.out.println("Introduce el número correspondiente a la función que se quiera testear:");
        System.out.println("1- Test GetRanking()");
        System.out.println("2- Test GetRecords()");
        System.out.println("3- Test AfegirTemps()");
        System.out.println("4- Test GuardarIOrdenarRanking()");
        System.out.println("5- Test SetRecords()");
        System.out.println("-1- Salir");
  

        int op = s.nextInt();
        while(op != -1){
            switch (op) {
                case 1: testGetRanking(ranking);
                        break;
                case 2: testGetRecords(ranking);
                        break;
                case 3: System.out.println("Introduce nuevo nombre y tiempo en s con coma");
                		String n = s.next();
                		Double d = s.nextDouble();
                		Pair aux = new Pair();
                		aux.setKey(n);
                		aux.setValue(d);
                		testAfegirTemps(ranking,aux);
                        break;
                case 4: System.out.println("Introduce nuevo nombre y tiempo en s con coma");
                		String m = s.next();
        				Double e = s.nextDouble();
                		testGuardarIOrdenarRanking(ranking,m,e);
                		System.out.println("Ranking y Records actualizados");
                        break;
                case 5: testSetRecords(ranking);
                        break;
              }
	            System.out.println("Introduce el número correspondiente a la función que se quiera testear:");
	            System.out.println("1- Test GetRanking()");
	            System.out.println("2- Test GetRecords()");
	            System.out.println("3- Test AfegirTemps()");
	            System.out.println("4- Test GuardarIOrdenarRanking()");
	            System.out.println("5- Test SetRecords()");
	            System.out.println("-1- Salir");
	            op = s.nextInt();
        }
    	
    
    }
}
