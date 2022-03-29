package Presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.Color;

/** \file Perfil.java
 *  Clase destinada a mostrar la cuenta para cambiar la contraseña o eliminar el perfil
 *
 */

public class Perfil {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private JLabel fondo;
	private BasicArrowButton retorno;
	private JLabel titulo;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JLabel passwordLabel2;
	private JPasswordField passwordText2;
	private JButton cambiarContraseña;
	private JButton eliminarperfil;


	
	public Perfil(CtrlPresentacion CP, String usuario) {
		this.CP = CP;
		this.usuario = usuario;
		initialize();
		frame.setVisible(true);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = pantalla.height;
	    int width = pantalla.width;
	    frame.setSize(width/2, height/2);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setSize(new java.awt.Dimension(800, 600));		}

	
	private void initialize() {
		frame = new JFrame("KAKURO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();	
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		logo = new JLabel();
		logo.setIcon(new javax.swing.ImageIcon("Presentacion/img/logo.png"));
		logo.setBounds(682, 26, 44, 53);
		panel.add(logo);
		Image icon = Toolkit.getDefaultToolkit().getImage("Presentacion/img/icon.png");
		frame.setIconImage(icon);
		
		retorno = new BasicArrowButton(BasicArrowButton.WEST);
		retorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retornoActionPerformed(e);
			}
		});
		retorno.setBounds(36, 30, 50, 25);
		panel.add(retorno);
		
		titulo = new JLabel(usuario);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe Print", Font.PLAIN, 36));
		titulo.setBounds(0, 26, 784, 54);
		panel.add(titulo);
		
		passwordLabel = new JLabel("Nueva contraseña");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 18));
		passwordLabel.setBounds(0, 196, 392, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(331, 196, 392, 25); 
		panel.add(passwordText);
		
		passwordLabel2 = new JLabel("Confirmar contraseña");
		passwordLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel2.setFont(new Font("Segoe Print", Font.PLAIN, 18));
		passwordLabel2.setBounds(0, 262, 392, 25);
		panel.add(passwordLabel2);

		passwordText2 = new JPasswordField(20);
		passwordText2.setBounds(331, 265, 392, 25);
		panel.add(passwordText2);
		
		cambiarContraseña = new JButton("Cambiar contraseña");
		cambiarContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarContraseñaButtonActionPerformed(e);
			}
		});
		cambiarContraseña.setBounds(148, 325, 150, 50);
		panel.add(cambiarContraseña);
		
		eliminarperfil = new JButton("Eliminar Perfil");
		eliminarperfil.setForeground(Color.RED);
		eliminarperfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarperfilButtonActionPerformed(e);
			}
		});
		eliminarperfil.setBounds(460, 325, 150, 50);
		panel.add(eliminarperfil);
		
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
        frame.dispose();
	}
	
	public void cambiarContraseñaButtonActionPerformed(ActionEvent e) {
		String np = String.valueOf(passwordText.getPassword());
		String np2 = String.valueOf(passwordText2.getPassword());
        if (np.equals("")) JOptionPane.showMessageDialog(frame, "Rellena las casillas!",
                "Error", JOptionPane.WARNING_MESSAGE);
        else {
            if (!(np.equals(np2))) JOptionPane.showMessageDialog(frame, "No coinciden las Contraseñas!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            else {
            	CP.modificarUsuarioContraseña(usuario, np);
            	JOptionPane.showMessageDialog(frame, "Contraseña modificada correctamente");
            	PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
                frame.dispose();
            }
        }
	}

	public void eliminarperfilButtonActionPerformed(ActionEvent e) {
		int aux = JOptionPane.showConfirmDialog(
            null,
            "Seguro que desea eliminar el Perfil?",
            "",
            JOptionPane.YES_NO_OPTION);

        if(aux == JOptionPane.YES_OPTION){
        	CP.eliminarUsuario(usuario);
        	JOptionPane.showMessageDialog(frame, "Perfil eliminado correctamente");
        	SignIn SI = new SignIn(CP);
            frame.dispose();
        }
	}
}


/** @class Perfil
 * Clase destinada a mostrar la cuenta para cambiar la contraseña o eliminar el perfil
 *	@author Fñlix Forroll
 */
 