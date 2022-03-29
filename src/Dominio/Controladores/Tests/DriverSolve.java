package Dominio.Controladores.Tests;

import java.lang.Exception;

import java.util.Scanner;

import Dominio.Controladores.SolveValida;
import Dominio.Clases.kakuro;
import Dominio.Controladores.LeerEscribir;

/**
 * Driver del SolverValida
 * @author Víctor Villarejo
 */

public class DriverSolve {
	
	/**
	 * Valida y soluciona un kakuro
	 * @param k Kakuro
	 * @return Devuelve el numero de soluciones que tiene el kakuro.
	 */
	public static int testValidar(kakuro k) throws Exception {
		SolveValida s = new SolveValida();
    	s.initialize(k);
		s.valida(0, 0, k);
		int num = s.getnumsolucions();
		s.solve(0, 0, k);
		return num;
	}

	
    
    public static void main (String [] args) throws Exception{
        

    	Scanner s = new Scanner(System.in);
        
    	System.out.println("1.Elegir kakuro");
    	System.out.println("-1.Salir");
    	
        LeerEscribir l = new LeerEscribir();
        int op = s.nextInt();
        kakuro k;
        SolveValida so = new SolveValida();
        
        while(op != -1) {
        	if(op == 1) {
	        	k = l.leerkakurotxt();
	        	
	        	int op2;
	        	System.out.println("1.Validar este kakuro");
	        	System.out.println("-1.Atrás");
	        	op2 = s.nextInt();

		        while(op2 != -1) {
		        	
		        	//Validar
		        	if(op2 == 1) {
		        		int sols;
					    sols = testValidar(k);		  
					    if(sols == 0){
					    	System.out.println("Este Kakuro no tiene solución!");
					    	l.escribirkakuro(k, 0);
					    	}
					    if(sols == 1) {
					    	System.out.println("Este Kakuro tiene una solución única. ");
						    l.escribirkakuro(k, 1);
					    	}
					    if(sols==2){
					    	System.out.println("Este Kakuro tiene más de una solución");
					    	 l.escribirkakuro(k, 1);
					    	}
					    }
			            System.out.println("1.Validar este kakuro");
			            System.out.println("-1.Atrás");
				        op2 = s.nextInt();
					    
					    }

		        		}
		        	
		        }
    }
}