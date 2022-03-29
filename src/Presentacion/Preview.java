package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/** \file Preview.java
 *  Clase destinada a mostrar una previsualizacion de un kakuro
 *
 */
public class Preview extends javax.swing.JPanel{

    private JPanel tablero;
    private CtrlPresentacion CP;
    private int id;
    private List<List<String>> kakuro;
    private String dificultad;
    private int n;
    private int m;


	
	public Preview() {
		initialize();
	}
	
	public Preview (CtrlPresentacion CP, int id, Rectangle r) {
		this.CP = CP;
		this.id = id;
		initialize();
		this.setBounds(r);
        tablero.setBounds(r);

		initialize2();
		setVisible(true);
	}
	
	public Preview (CtrlPresentacion CP, Rectangle r, int n, int m, String dificultad) {
		this.CP = CP;
		this.m = m;
		this.n = n;
		this.dificultad = dificultad;
		initialize();
		this.setBounds(r);
        tablero.setBounds(r);

		initialize3();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tablero = new JPanel();
        setMaximumSize(new java.awt.Dimension(502, 408));
        setMinimumSize(new java.awt.Dimension(502, 408));
        setPreferredSize(new java.awt.Dimension(502, 408));
        setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout TaulerLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(TaulerLayout);
        TaulerLayout.setHorizontalGroup(
            TaulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );
        TaulerLayout.setVerticalGroup(
            TaulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        add(tablero); 
        this.setLocation(100,100);
	}
	
	//llegeix un de txt
	private void initialize2() {
		tablero.setBackground(Color.BLACK);

        kakuro = CP.leerkakurotxt(id);
        
        
        
	    n = Integer.parseInt(kakuro.get(0).get(0));
	    m = Integer.parseInt(kakuro.get(0).get(1));
	    
	    
	    
        //int alt = getHeight();
        int filaSize = 504/m;
        int columSize = 415/n;
        
       List<List<String>> x = transKakuro2(kakuro);    
        //transKakuro(kakuro);
        
        
       
        
        
        
        for (int i=1; i<=m; i++) {
            for (int j=0; j<n; j++) {

                JLabel b = new JLabel();
                tablero.add(b);
                b.setBounds((i-1)*filaSize, j*columSize, filaSize, columSize);
                b.setOpaque(true);                
                b.setBackground(Color.white);
                b.setVisible(true);
                

                JLabel lab = new JLabel (x.get(i).get(j));
                if (m > 12) lab.setFont(new Font("Tahoma", Font.PLAIN, 10));
                b.setHorizontalAlignment(SwingConstants.CENTER);
                b.setVerticalAlignment(SwingConstants.CENTER);

                lab.setHorizontalAlignment(SwingConstants.CENTER);
                lab.setVerticalAlignment(SwingConstants.CENTER); 
                String aux = lab.getText();
                if (aux.charAt(0) == '*') {
            		lab.setVisible(false);
                	b.setBackground(Color.black);
                }
                if (aux.charAt(0) == 'F' || aux.charAt(0) == 'C') {
                	String s = leerCasillaJugable(lab.getText());
	            	lab.setText(s);
                	b.setBackground(Color.gray);
                	lab.setForeground(Color.WHITE);
                }
                if (aux.charAt(0) == '?') {
            		lab.setVisible(false);
                }
                b.add(lab);
                
                lab.setBounds(filaSize/4-6, columSize/4, filaSize/2+10, columSize/2);

                b.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            }
        }
	}
	
	//generar un nou
	private void initialize3() {
		//this.setBackground(Color.white);
		tablero.setBackground(Color.BLACK);

        kakuro = CP.generarkakuro(n, m, dificultad);
        int filaSize = 504/m;
        int columSize = 415/n;
        List<List<String>> x = transKakuro2(kakuro);    
        
        for (int i=1; i<=m; i++) {
            for (int j=0; j<n; j++) {
                JLabel b = new JLabel();
                tablero.add(b);
                b.setBounds((i-1)*filaSize, j*columSize, filaSize, columSize);
                b.setOpaque(true);                
                b.setBackground(Color.white);
                b.setVisible(true);
                
                JLabel lab = new JLabel (x.get(i).get(j));
                if (m > 12) lab.setFont(new Font("Tahoma", Font.PLAIN, 10));
                b.setHorizontalAlignment(SwingConstants.CENTER);
                b.setVerticalAlignment(SwingConstants.CENTER);

                lab.setHorizontalAlignment(SwingConstants.CENTER);
                lab.setVerticalAlignment(SwingConstants.CENTER); 
                String aux = lab.getText();
                if (aux.charAt(0) == '*') {
            		lab.setVisible(false);
                	b.setBackground(Color.black);
                }
                if (aux.charAt(0) == 'F' || aux.charAt(0) == 'C') {
                	String s = leerCasillaJugable(lab.getText());
	            	lab.setText(s);
                	b.setBackground(Color.gray);
                	lab.setForeground(Color.WHITE);
                }
                if (aux.charAt(0) == '?') {
            		lab.setVisible(false);
                }
                b.add(lab);
                lab.setBounds(filaSize/4-6, columSize/4, filaSize/2+10, columSize/2);

                b.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            }
        }
	}
	
	
	private String leerCasillaJugable(String s) {
		
        if(s.charAt(0) == 'C' && s.contains("F")){
          s = s.replaceFirst("C","");
          String[] valores = s.split("F");
          
          s = valores[0];
          s += "\\";
          s += valores[1];
          return s;
      
        }
        else if(s.charAt(0) == 'F'){
          s = s.replaceFirst("F","");
          return s;
        }
        else{
          s = s.replaceFirst("C","");
          return s;
        }		
	}
	
	
	private void transKakuro (List<List<String>> k) {
		String[][] mat = new String[n][m];
		
		for (int i=1; i<=n; i++) {
            for (int j=0; j<m; j++) {
               String aux2 =  k.get(i).get(j);
               mat[i-1][j] = aux2;
            }
        }
        
        String[][] mat2 = new String[m][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                mat2[j][i] = mat[i][j];
            }
        }
        
		for (int i=1; i<=n; i++) {
            for (int j=0; j<m; j++) {
               String aux2 =  mat2[i-1][j];
               System.out.print(aux2);
               kakuro.get(i).set(j, aux2);
            }
            System.out.println("");
        }
    }
	
	private List<List<String>> transKakuro2 (List<List<String>> k) {
		String[][] mat = new String[n][m];
		
		for (int i=1; i<=n; i++) {
            for (int j=0; j<m; j++) {
               String aux2 =  k.get(i).get(j);
               mat[i-1][j] = aux2;
            }
        }
		
        String[][] mat2 = new String[m][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                mat2[j][i] = mat[i][j];
            }
        }
        
		List<List<String>> nuevo = new ArrayList<List<String>>();
        List<String> aux  = new ArrayList<String>();
        aux.add(String.valueOf(n));
        aux.add(String.valueOf(m));
        nuevo.add(aux);
        aux  = new ArrayList<String>();

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
               String aux2 =  mat2[i][j];
               aux.add(aux2);
            }
            nuevo.add(aux);
            aux  = new ArrayList<String>();
            
        }

        return nuevo;
    }
	
	public List<List<String>> getkakuro() {
		return kakuro;
	}
}


/** @class Preview
 *  Clase destinada a mostrar una previsualizacion de un kakuro
 *	@author Fèlix Forroll
 */