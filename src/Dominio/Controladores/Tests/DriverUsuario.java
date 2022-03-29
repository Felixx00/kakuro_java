package Dominio.Controladores.Tests;

import java.util.Scanner;


import Dominio.Clases.Pair;
import Dominio.Clases.Ranking;
import Dominio.Clases.Usuario;

/**
 * Driver del Usuario
 * @author Vñctor Villarejo
 */

public class DriverUsuario {
	
	/**
   	 * Retorna el nombre del usuario
   	 * @param usuario Usuario
   	 */
    public static void testGetUsuario(Usuario usuario) throws Exception{
        System.out.println(usuario.getUsuario());   
    };
    
    
    /**
   	 * Retorna la contraseña del usuario
   	 * @param usuario Usuario
   	 */
    public static void testGetContraseña(Usuario usuario) throws Exception{
        System.out.println(usuario.getContraseña());   
    };
    
    
    /**
   	 * Configura el nombre usuario de usuario
   	 * @param usuario nombre usuario
   	 * @param nomU String
   	 */
    public static void testSetUsuario(Usuario usuario, String nomU) throws Exception{
        usuario.setUsuario(nomU);   
    };
    
    
    /**
   	 * Configura la contraseña de usuario
   	 * @param usuario Usuario
   	 * @param contr String
   	 */
    public static void testSetContraseña(Usuario usuario, String contr) throws Exception{
        usuario.setContraseña(contr);   
    };
    
    
    /**
   	 * Comprueba si es la contraseña es correcta
   	 * @param usuario Usuario
   	 * @param contr String
   	 */
    public static void testContraseñaCorrecta(Usuario usuario, String contr) throws Exception{
        boolean aux = usuario.contraseñaCorrecta(contr);   
        if (aux) System.out.println("Contraseña correcta");
        else System.out.println("Contraseña incorrecta");
    };
    
    
    
public static void main (String [] args) throws Exception{
    	
    	Scanner s = new Scanner(System.in);
    	
    	Usuario usuario = new Usuario();
    	System.out.println("Introduce el nñmero correspondiente a la funciñn que se quiera testear:");
        System.out.println("1- Test GetUsuario()");
        System.out.println("2- Test GetContraseña()");
        System.out.println("3- Test SetUsuario()");
        System.out.println("4- Test SetContraseña()");
        System.out.println("5- Test ContraseñaCorrecta()");
        System.out.println("-1- Salir");
  

        int op = s.nextInt();
        while(op != -1){
            switch (op) {
                case 1: testGetUsuario(usuario);
                        break;
                case 2: testGetContraseña(usuario);
                        break;
                case 3: System.out.println("Introduce el nombre de usuario");
                		String n = s.next();
                		testSetUsuario(usuario,n);
                        break;
                case 4: System.out.println("Introduce la contraseña del usuario");
                		String m = s.next();
                		testSetContraseña(usuario,m);
                        break;
                case 5: System.out.println("Introduce el nombre de usuariola contraseña para comprobar si es la correcta");
        				String l = s.next();
                		testContraseñaCorrecta(usuario,l);
                        break;
              }
	            System.out.println("Introduce el nñmero correspondiente a la funciñn que se quiera testear:");
	            System.out.println("1- Test GetUsuario()");
	            System.out.println("2- Test GetContraseña()");
	            System.out.println("3- Test SetUsuario()");
	            System.out.println("4- Test SetContraseña()");
	            System.out.println("5- Test ContraseñaCorrecta()");
	            System.out.println("-1- Salir");
	            op = s.nextInt();
        }
    	
    
    }
}
