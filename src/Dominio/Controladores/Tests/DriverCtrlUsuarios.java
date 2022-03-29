package Dominio.Controladores.Tests;

import java.util.HashSet;

import java.util.Iterator;
import java.util.Scanner;

import Dominio.Clases.Pair;
import Dominio.Clases.Usuario;
import Dominio.Controladores.CtrlRankings;
import Dominio.Controladores.CtrlUsuario;

/**
 * Driver del Controlador De Usuarios
 * @author Rubñn Montagut
 */

public class DriverCtrlUsuarios {

	
	/**
   	 * Carga los todos los usuarios que haya
   	 * @param CU CtrlUsuario
   	 */
    public static void testCargarUsuarios(CtrlUsuario CU) throws Exception{
        CU.cargar_usuarios();
    };
    
    
    /**
   	 * Imprime por pantalla el numero de usuarios que hay en CU
   	 * @param CU CtrlUsuario
   	 */
    public static void testGetNumUsuarios(CtrlUsuario CU) throws Exception{
        System.out.println("Cantidad de Usuarios: " + CU.getNumUsuarios());
    };
	
    
    /**
   	 * Imprime por pantalla todos los nombres de los usuarios
   	 * @param CU CtrlUsuario
   	 */
    public static void testGetUsuarios(CtrlUsuario CU) throws Exception{
        HashSet<Usuario> aux = CU.getUsuarios();
        Iterator<Usuario> it = aux.iterator();
        
        while(it.hasNext()) {
        	System.out.println(it.next().getUsuario());
        }
    };
    
    
    /**
   	 * Imprime por pantalla el nombre y la contraseña del usuario especificado
   	 * @param CU CtrlUsuario, nombre String
   	 */
    public static void testGetUsuario(CtrlUsuario CU, String nombre) throws Exception{
        Usuario aux = CU.getUsuario(nombre);
        System.out.println(aux.getUsuario() + " " + aux.getContraseña());
    };
    
    /**
   	 * Elimina el usuario especificado con el nombreusuario = nombre
   	 * @param CU CtrlUsuario, nombre String
   	 */
    public static void testEliminarUsuario(CtrlUsuario CU, String nombre) throws Exception{
    	CU.eliminarUsuario(nombre);
    };
    
    
    /**
   	 * Añade un usuario con nombreusuario nombre y contraseña contraseña
   	 * @param CU CtrlUsuario, nombre String, contraseña String
   	 */
    public static void testAñadirUsuario(CtrlUsuario CU, String nombre, String contraseña) throws Exception{
    	CU.añadirUsuario(nombre, contraseña);
    };
    
    
    /**
   	 * Comprueba si la contraseña especificada corresponde al usuario con el nombre especificado
   	 * @param CU CtrlUsuario, nombre String, contraseña String
   	 */
    public static void testComprobarContraseña(CtrlUsuario CU, String nombre, String contraseña) throws Exception{
    	boolean aux = CU.comprobarContraseña(nombre, contraseña);
    	if (aux) System.out.println("Contraseña correcta");
    	else System.out.println("Contraseña incorrecta");
    };
    
    
    /**
   	 * Modifica el usuaro con nombreusuario nombre y le asigna la nueva contraseña novacontraseña
   	 * @param CU CtrlUsuario, nombre String, novacontraseña String
   	 */
    public static void testModificarUsuarioContraseña(CtrlUsuario CU, String nombre, String novacontraseña) throws Exception{
    	CU.modificarUsuarioContraseña(nombre, novacontraseña);
    };
    
    
    
public static void main (String [] args) throws Exception{
    	
    	Scanner s = new Scanner(System.in);
    	
    	CtrlUsuario CU = new CtrlUsuario();
    	System.out.println("Introduce el nñmero correspondiente a la funciñn que se quiera testear:");
        System.out.println("1- Test CargarUsuarios()");
        System.out.println("2- Test GetNumUsuarios()");
        System.out.println("3- Test GetUsuarios()");
        System.out.println("4- Test GetUsuario()");
        System.out.println("5- Test EliminarUsuario()");
        System.out.println("6- Test AñadirUsuario()");
        System.out.println("7- Test ComprobarContraseña()");
        System.out.println("8- Test ModificarUsuarioContraseña()");
        System.out.println("-1- Salir");
  

        int op = s.nextInt();
        while(op != -1){
            switch (op) {
                case 1: testCargarUsuarios(CU);
                        break;
                case 2: testGetNumUsuarios(CU);
                        break;
                case 3: testGetUsuarios(CU);
                        break;
                case 4: System.out.println("Introduce el nombre del usuario que quieres obtener");
                		String b = s.next();	
                		testGetUsuario(CU,b);
                        break;
                case 5: System.out.println("Introduce el nombre del usuario que quieres eliminar");
        				String c = s.next();
                		testEliminarUsuario(CU, c);
                        break;
                case 6: System.out.println("Introduce el nombre y la contraseña del usuario que quieres añadir");
						String d = s.next();
						String e = s.next();
		        		testAñadirUsuario(CU, d, e);
		                break;
                case 7: System.out.println("Introduce el nombre del usuario y la contraseña para comprobar si es la correcta");
						String f = s.next();
						String g = s.next();
		        		testComprobarContraseña(CU, f, g);
		                break;
                case 8: System.out.println("Introduce el nombre del usuario que quieres cambiar la contraseña y la nueva contraseña");
						String h = s.next();
						String i = s.next();
		        		testModificarUsuarioContraseña(CU, h, i);
		                break;
		                
              }
	            System.out.println("Introduce el nñmero correspondiente a la funciñn que se quiera testear:");
	            System.out.println("1- Test CargarUsuarios()");
	            System.out.println("2- Test GetNumUsuarios()");
	            System.out.println("3- Test GetUsuarios()");
	            System.out.println("4- Test GetUsuario()");
	            System.out.println("5- Test EliminarUsuario()");
	            System.out.println("6- Test AñadirUsuario()");
	            System.out.println("7- Test ComprobarContraseña()");
	            System.out.println("8- Test ModificarUsuarioContraseña()");
	            System.out.println("-1- Salir");
	            op = s.nextInt();
        }
    	
    
    }
    
}
