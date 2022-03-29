package Dominio.Controladores;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import Dominio.Clases.Usuario;
import Persistencia.CtrlPersistencia;
/**
 * Clase para las operaciones relacionadas con Usuarios
 * @author Mart� Westermeyer
 */
public class CtrlUsuario {
	/**
	 * Booleano para saber si se ha modificado la tabla
	 */
	private boolean dirty;
	/**
	 * Set de Usuarios
	 */
	HashSet<Usuario> usuarios = new HashSet<Usuario>();
	/**
	 * Guarda en el set los uusarios del txt
	 */
	public void cargar_usuarios() {
		
        try {
        	List<List<String>> aux = CtrlPersistencia.cargarTablaUsuarios();

            for (List<String> fila : aux) {
                usuarios.add(new Usuario(fila.get(0), fila.get(1)));
            }
        }
        catch (Exception e) {
		    System.out.println(e);
        } 
        /*
        Iterator<Usuario> iter = usuarios.iterator();
        while (iter.hasNext()) System.out.println(iter.next().getUsuario());
        */
	}
	
	/**
	 * Devuelve el numero de usuarios en usuarios
	 * @return el numero de usuarios
	 */
	public int getNumUsuarios() {
		return this.usuarios.size();
	}
	
	/**
	 * Consulta los usuarios
	 * @return Set de usuarios
	 */
	public HashSet<Usuario> getUsuarios() {
		return usuarios;
	}
	/**
	 * Consulta un �nico Usuario
	 * @param nombre Nombre del Usuario
	 * @return Usuario seleccionado
	 */
	public Usuario getUsuario(String nombre) {
      for (Usuario aux : usuarios) {
        if (Objects.equals(aux.getUsuario(), nombre)) 
          return aux;
      }
      return null; 
	}
	/**
	 * Borra el Usuario pasado por par�metro
	 * @param nombre Nombre del Usuario
	 */
	public void eliminarUsuario(String nombre) {
	  Usuario aux = getUsuario(nombre);
      if (aux != null) {
              dirty = usuarios.remove(aux);
      }
      else System.out.println("No existe el usuario");
      end();
      dirty = false;
	}
	/**
	 * A�ade el Usuario pasado por par�metro
	 * @param nombre Nombre del Usuario
	 * @param contrase�a Contrase�a del Usuario
	 * @return Si el usuario se ha a�adido correctamente
	 */
	public boolean añadirUsuario(String nombre, String contraseña) {
		boolean ret = false;
		  Usuario aux = getUsuario(nombre);
	      if (aux == null) {
	    	  Usuario nuevo = new Usuario(nombre, contraseña);
	    	  ret = usuarios.add(nuevo);
	    	  dirty = ret;
	      }
	      else System.out.print("Ya existe ese Usuario");
	      end();
	      dirty = false;
	      return ret;
	}
	/**
	 * Comprueba contrase�a correcta
	 * @param nombre Nombre del Usuario
	 * @param contrase�a Contrase�a del Usuario
	 * @return true si la contrase�a es correcta, en caso contrario false
	 */
    public boolean comprobarContraseña(String nombre, String contraseña) {
      boolean ret = false;
  	  Usuario aux = getUsuario(nombre);
      if (aux != null) {
          ret = aux.contraseñaCorrecta(contraseña);
      }
      else System.out.print("No existe el usuario");
      return ret;
    }
    /**
     * Modifica la contrase�a del Usuario pasado por par�metro
     * @param nombre Nombre del Usuario
     * @param nuevaContrase�a Nueva Contrase�a del Usuario
     */
	public void modificarUsuarioContraseña(String nombre, String nuevaContraseña) {
		  Usuario aux = getUsuario(nombre);
	      if (aux != null) {
	    	  aux.setContraseña(nuevaContraseña);
	          dirty = true;
	      }
	      else System.out.println(dirty);
	      end();
	      dirty = false;
	}
	/**
	 * Funci�n para modificar el fichero si ha habido cambios
	 */
	private void end() {
		if (dirty) {
	        try {
	            CtrlPersistencia.guardarTablaUsuarios(usuarios);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
}
