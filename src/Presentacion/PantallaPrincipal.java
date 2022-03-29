package Presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** \file PantallaPrincipal.java
 *  Clase destinada a mostrar la pantalla principal al usuario, puediendo acceder a perfil, jugar, gestionar, rankings así como salir de su cuenta
 *
 */

public class PantallaPrincipal {

	private JFrame frame;
	private CtrlPresentacion CP;
	private JLabel logo;
	private JButton logOut;
	private JButton gestionarkakuros;
	private JButton jugar;
	private JButton ranking;
	private JLabel titulo;
	private String usuario;
	private JButton perfil;
	private JLabel fondo;


	
	public PantallaPrincipal(CtrlPresentacion CP, String usuario) {
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
	    frame.setSize(new java.awt.Dimension(800, 600));	
	}

	
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
		
		titulo = new JLabel("Bienvenido " + usuario + "!");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe Print", Font.PLAIN, 36));
		titulo.setBounds(0, 26, 784, 54);
		panel.add(titulo);
		
		logOut = new JButton("Log out");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOutActionPerformed(e);
			}
		});
		logOut.setBounds(690, 499, 80, 40);
		panel.add(logOut);
		
		gestionarkakuros = new JButton("Gestionar kakuros");
		gestionarkakuros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionarkakurosPerformed(e);
			}
		});
		gestionarkakuros.setBounds(100, 150, 594, 54);
		panel.add(gestionarkakuros);
		
		jugar = new JButton("Jugar");
		jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugarActionPerformed(e);
			}
		});
		jugar.setBounds(100, 250, 594, 54);
		panel.add(jugar);
		
		ranking = new JButton("Rankings");
		ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankingActionPerformed(e);
			}
		});
		ranking.setBounds(100, 350, 594, 54);
		panel.add(ranking);
		
		Image buttonIcon = Toolkit.getDefaultToolkit().getImage("Presentacion/img/perfil.png");
		perfil = new JButton(new ImageIcon(buttonIcon));
		perfil.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				perfil.setFocusable(true);
			}
		});
		perfil.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		perfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perfilActionPerformed(e);
			}
		});
		perfil.setBorder(BorderFactory.createEmptyBorder());
		perfil.setContentAreaFilled(false);
		perfil.setBounds(28, 21, 100, 100);
		panel.add(perfil);
		
		perfil.setFocusable(false);
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
	public void gestionarkakurosPerformed(ActionEvent e) {
		  Gestionarkakuro GK = new Gestionarkakuro(CP, usuario);
          frame.dispose();
	}
	
	public void logOutActionPerformed(ActionEvent e) {
		  SignIn SI = new SignIn(CP);
		  frame.dispose();
	}
	
	public void jugarActionPerformed(ActionEvent e) {
		  Jugar J = new Jugar(CP, usuario);
		  frame.dispose();
	}
	
	public void rankingActionPerformed(ActionEvent e) {
		  Ranking R = new Ranking(CP, usuario);
		  frame.dispose();
	}
	
	public void perfilActionPerformed(ActionEvent e) {
		  Perfil P = new Perfil(CP, usuario);
		  frame.dispose();
	}
}

/** @class PantallaPrincipal
 *  Clase destinada a mostrar la pantalla principal al usuario, puediendo acceder a perfil, jugar, gestionar, rankings así como salir de su cuenta
 *	@author Martí Westermeyer
 */

