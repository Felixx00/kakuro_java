package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import javax.swing.JTextField;
import javax.swing.JComboBox;

/** \file Generar.java
 *  Clase destinada a mostrar un kakuro generado con los parñmetros indicados
 *
 */

public class Generar {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private JLabel fondo;
	private JLabel titulo;
	private BasicArrowButton retorno;
	private JPanel previewP;
	private JLabel previewT;
	private JLabel dimensiones;
	private JTextField x;
	private JLabel aux;
	private JTextField y;
	private JComboBox<String> dificultad;
	private JButton generar;
	private JButton guardar;
	private Preview p;

	
	public Generar(CtrlPresentacion CP, String usuario) {
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
		
		titulo = new JLabel("Generar kakuro");
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
		
		previewP = new JPanel();
		previewP.setBounds(243, 112, 504, 415);
		previewP.setBorder(BorderFactory.createLineBorder(Color.black));
		previewP.add(new Preview());
		panel.add(previewP); 
		previewP.removeAll();
    	previewP.revalidate();
    	previewP.repaint();
		previewP.setBackground(Color.WHITE);
		
		previewT = new JLabel("Preview:");
		previewT.setBounds(243, 91, 60, 14);
		previewT.setBackground(Color.WHITE);
		panel.add(previewT);
		
		dimensiones = new JLabel("Introduce dimensiones:");
		dimensiones.setFont(new Font("Segoe Print", Font.BOLD, 15));
		dimensiones.setBounds(36, 147, 173, 14);
		dimensiones.setBackground(Color.WHITE);
		panel.add(dimensiones);
		
		x = new JTextField();
		x.setBounds(36, 172, 60, 20);
		panel.add(x);
		x.setColumns(10);
		aux = new JLabel("X");
		aux.setFont(new Font("Segoe Print", Font.BOLD, 11));
		aux.setHorizontalAlignment(SwingConstants.CENTER);
		aux.setBounds(114, 175, 13, 14);
		panel.add(aux);
		
		y = new JTextField();
		y.setBounds(148, 172, 60, 20);
		panel.add(y);
		y.setColumns(10);
		
		dimensiones = new JLabel("Elige dificultad:");
		dimensiones.setFont(new Font("Segoe Print", Font.BOLD, 15));
		dimensiones.setBounds(36, 216, 173, 21);
		panel.add(dimensiones);
		
		dificultad = new JComboBox<String>();
		dificultad.setBounds(36, 248, 172, 33);
		panel.add(dificultad);
		dificultad.addItem("Fñcil");
		dificultad.addItem("Mediana");
		dificultad.addItem("Difñcil");
		
		generar = new JButton("Generar");
		generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generaractionPerformed(e);
			}
		});
		generar.setBounds(36, 338, 172, 44);
		panel.add(generar);
		
		guardar = new JButton("Guardar");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardaractionPerformed(e);
			}
		});
		guardar.setBounds(36, 430, 172, 44);
		panel.add(guardar);
		
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		Gestionarkakuro P = new Gestionarkakuro(CP, usuario);
        frame.dispose();
	}
	
	public void generaractionPerformed(ActionEvent e) {
		
		String aux = x.getText();
		String aux2 = y.getText();
		if (aux.equals("") || aux2.equals("")) JOptionPane.showMessageDialog(frame, "Rellena las casillas!",
                "Error", JOptionPane.WARNING_MESSAGE);
		else {
			int n = Integer.parseInt(aux);
			int m = Integer.parseInt(aux2);
			
	        if (n < 6 || m < 6 || n > 15 || m > 15) {
	        	JOptionPane.showMessageDialog(frame, "Dimensiones entre 6 y 15!",
	                    "Error", JOptionPane.WARNING_MESSAGE);
	        }
	        else {
	        	String x = String.valueOf(dificultad.getSelectedItem());
	        	previewP.removeAll();
	        	previewP.revalidate();
	        	previewP.repaint();
	        	p = (new Preview(CP,previewP.getBounds(), n, m, x));
	        	previewP.setBackground(Color.black);
	        	
	        	previewP.add(p);
	        	
	        }
		}
	}
	
	public void guardaractionPerformed(ActionEvent e) {
		if (p == null) JOptionPane.showMessageDialog(frame, "Genera un kakuro primero!",
                "Error", JOptionPane.WARNING_MESSAGE);
		else {
			List<List<String>> matriz = p.getkakuro();
			int fileCount = CP.tamañoDirectorio();
			++fileCount;
			CP.guardarkakuro(matriz, fileCount);
			JOptionPane.showMessageDialog(frame, "Kakuro " + fileCount + " guardado correctamente");
		}
	}
}

/** @class Generar
 *  Clase destinada a mostrar un kakuro generado con los parñmetros indicados
 *	@author Martñ Westermeyer
 */
