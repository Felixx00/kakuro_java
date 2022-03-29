package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;


/** \file Gestionarkakuro.java
 *  Clase destinada a mostrar las diferentes funcionalidades relativas a la gestiñn de kakuros (generar, crear y eliminar)
 *
 */

public class Gestionarkakuro {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private JLabel fondo;
	private JButton generar;
	private JButton crear;
	private JButton eliminar;
	private JLabel titulo;
	private BasicArrowButton retorno;

	
	public Gestionarkakuro(CtrlPresentacion CP, String usuario) {
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
		
		retorno = new BasicArrowButton(BasicArrowButton.WEST);
		retorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retornoActionPerformed(e);
			}
		});
		retorno.setBounds(36, 30, 50, 25);
		panel.add(retorno);
		
		titulo = new JLabel("Gestionar kakuros");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe Print", Font.PLAIN, 36));
		titulo.setBounds(0, 26, 784, 54);
		panel.add(titulo);
		
		generar = new JButton("Generar kakuro");
		generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarkakurosPerformed(e);
			}
		});
		generar.setBounds(100, 150, 594, 54);
		panel.add(generar);
		
		crear = new JButton("Crear kakuro");
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearActionPerformed(e);
			}
		});
		crear.setBounds(100, 250, 594, 54);
		panel.add(crear);
		
		eliminar = new JButton("Eliminar kakuro");
		eliminar.setForeground(Color.RED);
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarActionPerformed(e);
			}
		});
		eliminar.setBounds(100, 350, 594, 54);
		panel.add(eliminar);
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
        frame.dispose();
	}
	
	public void generarkakurosPerformed(ActionEvent e) {
		  Generar G = new Generar(CP, usuario);
		  frame.dispose();
	}
	
	public void crearActionPerformed(ActionEvent e) {
		  Crear C = new Crear(CP, usuario);
		  frame.dispose();
	}
	
	public void eliminarActionPerformed(ActionEvent e) {
		int tam = CP.tamañoDirectorio();
		JComboBox<String> kakuros = new JComboBox<String>();
		for (int i = 0; i < tam; ++i) kakuros.addItem("Kakuro " + String.valueOf(i+1));
		int aux = JOptionPane.showConfirmDialog(
	            null,
	            kakuros,
	            "Selecciona kakuro a eliminar",
	            JOptionPane.YES_NO_OPTION);

	        if(aux == JOptionPane.YES_OPTION){
	        	String x = String.valueOf(kakuros.getSelectedItem());
	        	String x2 = x.replace("Kakuro ","");
	        	int id = Integer.parseInt(x2);
	        	CP.eliminarkakuro(id);
	        	JOptionPane.showMessageDialog(frame, "Kakuro eliminado correctamente");
	        }
	}
}


/** @class Gestionarkakuro
 *  Clase destinada a mostrar las diferentes funcionalidades relativas a la gestiñn de kakuros (generar, crear y eliminar)
 *	@author Martñ Westermeyer
 */