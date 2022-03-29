package Presentacion;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JSplitPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

/** \file SignIn.java
 *  Clase destinada a entrar e una cuenta existente
 *
 */


public class SignIn {

	private JFrame frame;
	private CtrlPresentacion CP;
	
	private JLabel titulo;
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton registerButton;
	private JLabel logo;
	private JLabel fondo;

	/**
	 * Create the application.
	 */
	public SignIn(CtrlPresentacion CP) {
		this.CP = CP;
		initialize();
		frame.setVisible(true);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = pantalla.height;
	    int width = pantalla.width;
	    frame.setSize(width/2, height/2);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setSize(new java.awt.Dimension(800, 600));
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

		
		titulo = new JLabel("Iniciar Sesiñn");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe Print", Font.PLAIN, 36));
		titulo.setBounds(0, 26, 784, 54);
		panel.add(titulo);
		
		userLabel = new JLabel("Usuario");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(new Font("Segoe Print", Font.PLAIN, 18));
		userLabel.setBounds(0, 125, 392, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(331, 125, 392, 25);
		panel.add(userText);

		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Segoe Print", Font.PLAIN, 18));
		passwordLabel.setBounds(0, 226, 392, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(331, 226, 392, 25);
		panel.add(passwordText);

		loginButton = new JButton("Iniciar sesiñn");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginButtonActionPerformed(e);
			}
		});
		loginButton.setBounds(148, 325, 150, 50);
		panel.add(loginButton);
		
		registerButton = new JButton("Registrarse");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerButtonActionPerformed(e);
			}
		});
		registerButton.setBounds(460, 325, 150, 50);
		panel.add(registerButton);
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
    private void loginButtonActionPerformed(ActionEvent e) {
        String nombre = userText.getText();
        String contraseña = String.valueOf(passwordText.getPassword());
        if (CP.comprobarUsuario(nombre,contraseña)) {
            System.out.println("Identificaciñ correcte");
            PantallaPrincipal P = new PantallaPrincipal(CP, nombre);
            frame.dispose();
        }
        else {
        	JOptionPane.showMessageDialog(frame, "Usuario Incorrecto!",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void registerButtonActionPerformed(ActionEvent e) {
        Register R = new Register(CP);
        frame.dispose();
    }
}



/** @class SignIn
 *  Clase destinada a entrar e una cuenta existente
 *	@author Fñlix Forroll
 */

