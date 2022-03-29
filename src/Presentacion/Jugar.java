package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/** \file Jugar.java
 *  Clase destinada a elegir un kakuro del repositorio o cargar una partida guardada i mostrarla por pantalla
 *
 */

public class Jugar {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private JLabel fondo;
	private BasicArrowButton retorno;
	private JLabel titulo;
	private JButton jugar;
	private JButton cargarAnterior;
	private JComboBox<String> desplegable;
	private JPanel previewP;
	private JLabel previewT;
	private int id;

	
	public Jugar(CtrlPresentacion CP, String usuario) {
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
		
		titulo = new JLabel("Jugar");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe Print", Font.PLAIN, 36));
		titulo.setBounds(0, 26, 784, 54);
		panel.add(titulo);
		
		retorno = new BasicArrowButton(BasicArrowButton.WEST);
		retorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retornoActionPerformed(e);
			}
		});
		retorno.setBounds(36, 30, 50, 25);
		panel.add(retorno);
		
		jugar = new JButton("Jugar");
		jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugarButtonActionPerformed(e);
			}
		});
		jugar.setBounds(57, 255, 150, 61);
		panel.add(jugar);
		
		cargarAnterior = new JButton("<html><style>p{text-align: center;}</style><p>Cargar partida<br />guardada</p></html>");
		cargarAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarAnteriorButtonActionPerformed(e);
			}
		});
		cargarAnterior.setBounds(57, 367, 150, 61);
		panel.add(cargarAnterior);
		
		previewP = new JPanel();
		previewP.setBounds(243, 112, 504, 415);
		previewP.setBorder(BorderFactory.createLineBorder(Color.black));
		previewP.add(new Preview());
		panel.add(previewP);
		
		desplegable = new JComboBox<String>();
		desplegable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desplegableActionPerformed(e);
			}
		});
		desplegable.setBounds(57, 160, 150, 32);
		panel.add(desplegable);
		int tam = CP.tamañoDirectorio();
		desplegable.addItem("KAKUROS");
		for (int i = 0; i < tam; ++i) desplegable.addItem("Kakuro " + String.valueOf(i+1));
		
		previewT = new JLabel("Preview:");
		previewT.setBounds(243, 91, 60, 14);
		panel.add(previewT);
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
		
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
        frame.dispose();
	}
	
	public void desplegableActionPerformed(ActionEvent e) {
    	String x = String.valueOf(desplegable.getSelectedItem());
    	if (x != "KAKUROS") {
        	String x2 = x.replace("Kakuro ","");
        	id = Integer.parseInt(x2);
        	previewP.removeAll();
        	previewP.revalidate();
        	previewP.repaint();
        	previewP.add(new Preview(CP, id, previewP.getBounds()));
        	previewP.setBackground(Color.BLACK);
    	}
    	else {
    		id = -1;
        	previewP.removeAll();
        	previewP.revalidate();
        	previewP.repaint();
        	previewP.setBackground(Color.WHITE);
    	}
	}
	
	public void jugarButtonActionPerformed(ActionEvent e) {
		if (id == -1)  JOptionPane.showMessageDialog(frame, "Elige un kakuro primero!",
                "Error", JOptionPane.WARNING_MESSAGE);
		else {
			Partida P = new Partida(CP, usuario, id);
	        frame.dispose();
		}
    }
	
	public void cargarAnteriorButtonActionPerformed(ActionEvent e) {
		if (CP.hayGuardada()) {
	    	previewP.removeAll();
	    	previewP.revalidate();
	    	previewP.repaint();
	    	previewP.add(new Preview(CP, 0, previewP.getBounds()));
	    	previewP.setBackground(Color.BLACK);
	    	id = 0;
		}
		else {
			 JOptionPane.showMessageDialog(frame, "No hay ninguna partida guardada!",
		                "Error", JOptionPane.WARNING_MESSAGE);
		}
    }
}

/** @class Jugar
 *  Clase destinada a elegir un kakuro del repositorio o cargar una partida guardada i mostrarla por pantalla
 *	@author Martñ Westermeyer
 */