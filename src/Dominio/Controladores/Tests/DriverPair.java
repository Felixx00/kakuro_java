package Dominio.Controladores.Tests;

import java.util.Scanner;


import Dominio.Clases.*;

/**
 * Driver de Pair
 * @author Rubén Montagut
 */
public class DriverPair {

	
	/**
   	 * Retorna la key de pair
   	 * @param pair Pair
   	 */
    public static void testGetKey(Pair pair) throws Exception{
        System.out.println(pair.getKey());   
    };
    
    
    /**
   	 * Retorna el value de pair
   	 * @param pair Pair
   	 */
    public static void testGetValue(Pair pair) throws Exception{
        System.out.println(pair.getValue());   
    };
    
    
    /**
   	 * Configura la key del Pair
   	 * @param pair Pair
   	 * @param key String
   	 */
    public static void testSetKey(Pair pair, String key) throws Exception{
        pair.setKey(key);   
    };
    
    
    /**
   	 * Configura el value del Pair
   	 * @param pair Pair
   	 * @param value Double
   	 */
    public static void testSetValue(Pair pair, Double value) throws Exception{
        pair.setValue(value);   
    };
    
    
    
public static void main (String [] args) throws Exception{
    	
    	Scanner s = new Scanner(System.in);
    	
    	Pair pair = new Pair();
    	System.out.println("Introduce el número correspondiente a la función que se quiera testear:");
        System.out.println("1- Test GetKey()");
        System.out.println("2- Test GetValue()");
        System.out.println("3- Test SetKey()");
        System.out.println("4- Test SetValue()");
        System.out.println("-1- Salir");
  

        int op = s.nextInt();
        while(op != -1){
            switch (op) {
                case 1: testGetKey(pair);
                        break;
                case 2: testGetValue(pair);
                        break;
                case 3: System.out.println("Introduce la key que quiere");
                		String n = s.next();
                		testSetKey(pair,n);
                        break;
                case 4: System.out.println("Introduce el value que quiere");
                		Double m = s.nextDouble();
                		testSetValue(pair,m);
                        break;
              }
	            System.out.println("Introduce el número correspondiente a la función que se quiera testear:");
	            System.out.println("1- Test GetKey()");
	            System.out.println("2- Test GetValue()");
	            System.out.println("3- Test SetKey()");
	            System.out.println("4- Test SetValue()");
	            System.out.println("-1- Salir");
	            op = s.nextInt();
        }
    	
    
    }
}
