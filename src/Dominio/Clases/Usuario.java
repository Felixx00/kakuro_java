package Dominio.Clases;
/**
 * Clase para los Usuarios
 * @author Fñlix Forroll
 */
public class Usuario {
	/**
	 * Nombre del usuario, no se puede repetir
	 */
	private String usuario;
	/**
	 * Contraseña del usuario
	 */
	private String contraseña;
	/**
	 * Creadora por defecto de Usuario
	 */
    public Usuario() {
    	usuario = null;
    	contraseña = null;
    }
    /**
     * Creadora con valores del usuario
     * @param usuario Nombre del usuario
     * @param contraseña Contraseña del usuario
     */
    public Usuario(String usuario, String contraseña) {
    	this.usuario = usuario;
    	this.contraseña = contraseña;
    }
    /**
     * Consulta el nombre del Usuario
     * @return Nombre del Usuario
     */
    public String getUsuario() {
    	return usuario;
    }
    /**
     * Consulta la contraseña del Usuario
     * @return Contraseña del Usuario
     */
    public String getContraseña() {
    	return contraseña;
    }
	/**
	 * Modifica el nombre del Usuario
	 * @param usuario Nombre del Usuario
	 */
    public void setUsuario(String usuario) {
    	this.usuario =  usuario;
    }
    
    /**
     * Modifica la contraseña del Usuario
     * @param contraseña Contraseña del Usuario
     */
    public void setContraseña(String contraseña) {
    	this.contraseña =  contraseña;
    }
    /**
     * Comprueba si la contraseña es correcta
     * @param contraseña Contraseña del Usuario
     * @return True si es correcta, en caso contrario false
     */
    public boolean contraseñaCorrecta(String contraseña) {
        return usuario != null && this.contraseña.equals(contraseña);
    }
}
