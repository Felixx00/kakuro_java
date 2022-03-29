import Presentacion.CtrlPresentacion;

/**
 * Método principal para Kakuro
 * @author Martí Westermeyer
 */
public class Main {
    private static CtrlPresentacion CP;
    
    public static void main (String[] args) throws Exception {
    	javax.swing.SwingUtilities.invokeLater (
    			new Runnable() {
    				public void run () {
    	    	    	System.out.println("Iniciando controlador de presentación...");
    	    	    	CP = new CtrlPresentacion();
    				}
    			}
    	);
    }
}