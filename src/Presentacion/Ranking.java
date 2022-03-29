package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import Dominio.Clases.Pair;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/** \file Ranking.java
 *  Clase destinada a mostrar los rankings de un kakuro y los records
 *
 */

public class Ranking {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private BasicArrowButton retorno;
	private JLabel titulo;
	private JComboBox<String> desplegable;
	private JLabel fondo;
	private JScrollPane scrollPane;
	private JPanel rank;
	private JPanel panel;
	private JLabel oro;
	private JLabel plata;
	private JLabel bronce;
	private JLabel primero;
	private JLabel segundo;
	private JLabel tercero;
	private JLabel records;
	private JList list;
	private String s[];
	/**
	 * Create the application.
	 */
	public Ranking(CtrlPresentacion CP, String usuario) {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("KAKURO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();	
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		logo = new JLabel();
		logo.setIcon(new javax.swing.ImageIcon("Presentacion/img/logo.png"));
		logo.setBounds(682, 26, 44, 53);
		panel.add(logo);
		Image icon = Toolkit.getDefaultToolkit().getImage("Presentacion/img/icon.png");
		frame.setIconImage(icon);
		
		titulo = new JLabel("Rankings");
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

		rank = new JPanel();
		rank.setBounds(245, 119, 493, 398);
		rank.setVisible(true);
		rank.setBackground(Color.white);
		rank.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(rank);
		
		records = new JLabel("Rñcords");
		records.setFont(new Font("Segoe Print", Font.BOLD, 20));
		records.setBounds(81, 330, 82, 24);
		panel.add(records);
		
		records = new JLabel("________");
		records.setFont(new Font("Arial", Font.PLAIN, 18));
		records.setBounds(81, 335, 82, 29);
		panel.add(records);
		
		primero = new JLabel("New label");
		primero.setFont(new Font("Segoe Print", Font.BOLD, 16));
		primero.setBounds(91, 370, 144, 25);
		panel.add(primero);
		primero.setVisible(false);
		
		segundo = new JLabel("New label");
		segundo.setFont(new Font("Segoe Print", Font.BOLD, 16));
		segundo.setBounds(91, 420, 144, 25);
		panel.add(segundo);
		segundo.setVisible(false);

		tercero = new JLabel("New label");
		tercero.setFont(new Font("Segoe Print", Font.BOLD, 16));
		tercero.setBounds(91, 470, 144, 25);
		panel.add(tercero);
		tercero.setVisible(false);

		oro = new JLabel();
		oro.setIcon(new javax.swing.ImageIcon("Presentacion/img/oro.png"));
		oro.setBounds(50, 370, 32, 32);
		panel.add(oro);
		
		plata = new JLabel();
		plata.setIcon(new javax.swing.ImageIcon("Presentacion/img/plata.png"));
		plata.setBounds(50, 420, 32, 32);
		panel.add(plata);
		
		bronce = new JLabel();
		bronce.setIcon(new javax.swing.ImageIcon("Presentacion/img/bronce.png"));
		bronce.setBounds(50, 470, 32, 32);
		panel.add(bronce);
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
        frame.dispose();
	}

	@SuppressWarnings("unchecked")
	public void desplegableActionPerformed(ActionEvent e) {
    	String x = String.valueOf(desplegable.getSelectedItem());
    	
    	if (x != "KAKUROS") {
    		rank.setBorder(BorderFactory.createLineBorder(Color.white));
    		rank.setBackground(Color.white);
    	
    		String x2 = x.replace("Kakuro ","");
        	int id = Integer.parseInt(x2);
        	List<Pair> ranking = CP.verRanking(id);
        	int tam = ranking.size();
    		int i = 0;
    		s = new String[tam];
    		
        	List<Pair> records = CP.verRecords(id);
        	if (!(records.isEmpty())) {
        		primero.setVisible(true);
        		primero.setText(records.get(0).getKey() + "  " + records.get(0).getValue());
        		if (records.size() >= 2) {
            		segundo.setVisible(true);
            		segundo.setText(records.get(1).getKey() + "  " + records.get(1).getValue());

        		}
        		if (records.size() >= 3) {
            		tercero.setVisible(true);
            		tercero.setText(records.get(2).getKey() + "  " + records.get(2).getValue());
        		}
        	}
        	else {
    			primero.setVisible(false);
    			segundo.setVisible(false);
    			tercero.setVisible(false);
        	}
    		
        	for (Pair fila : ranking) {
        		

            	
        		
        		String nombre = fila.getKey();
        		
        		double temps = fila.getValue();
        		String tiempo = String.valueOf(temps);
        		
        		
        		s[i] = ((i+1) + "." + "                              " + nombre);
        		int letras = nombre.length();
        		StringBuilder sb = new StringBuilder();
        		int whitespaces = 50;
        		if(letras > 1) {
        			whitespaces -= (letras - 1);
        		}else{
        			whitespaces = 50;
        		}
        		
        		for(int p = 0; p < whitespaces;++p) {
        			sb.append(" ");
        		}
        		sb.append(tiempo);
        		//System.out.println(sb);
        		s[i] += sb;
        		//System.out.println(s[i]);
        		++i;
        		
        	}
        	for(int a = 0; a < s.length; ++a) {
        		System.out.print(s[a]);
        		System.out.println(" ");
        	}
        	rank.removeAll();
    		rank.revalidate();
    		rank.repaint();
        	
        	list = new JList<String>(s);
    		list.setFixedCellHeight(40);
    		list.setBorder(new EmptyBorder(10,10, 10, 10));
    		scrollPane = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    		scrollPane.setBounds(245, 119, 493, 398);
    		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
    		renderer.setHorizontalAlignment(SwingConstants.LEFT);
    		
    		rank.setLayout(new BorderLayout());
    		rank.add(scrollPane,BorderLayout.CENTER);
    		
    		frame.setVisible(true);
    		
	}else {
		if(rank != null) {
			primero.setVisible(false);
			segundo.setVisible(false);
			tercero.setVisible(false);

			rank.setBorder(BorderFactory.createLineBorder(Color.black));
			rank.removeAll();
			rank.revalidate();
			rank.repaint();
		}
		
	}
	}
}

/** @class Ranking
 *  Clase destinada a mostrar los rankings de un kakuro y los records
 *	@author Fñlix Forroll
 */

