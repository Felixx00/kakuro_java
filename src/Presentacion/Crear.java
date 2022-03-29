package Presentacion;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JButton;






/** \file Crear.java
 *  Clase destinada a proponer un kakuro, validarlo y guardarlo si es correcto.
 *
 */




public class Crear {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private BasicArrowButton retorno;
	private JLabel titulo;
	private JPanel aux;
	private JPanel tablero;
	private JTextField dimX;
	private JTextField dimY;
	private JTextField sumafil;
	private JTextField sumacol;
	private JButton blanca;
	private JButton jugable;
	private JButton negra;
	private JButton solucionar;
	private JButton guardar;
	private JLabel casillaaux;
	private JLabel fondo;


	private List<JLabel> aa = new ArrayList<JLabel>();
	private List<List<String>> kakuro;
	private List<List<String>> x;
	private int n;
	private int m;
	private int id;
    private int filaSize;
    private int columSize;
    private int soluciones;


	
	public Crear(CtrlPresentacion CP, String usuario) {
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
		initialize1();
		initdim();
		soluciones = -1;
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
	    
		titulo = new JLabel("Crear kakuro");
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
		
		Image buttonIcon = Toolkit.getDefaultToolkit().getImage("Presentacion/img/blanca.png");
		blanca = new JButton(new ImageIcon(buttonIcon));
		blanca.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				blanca.setFocusable(true);
			}
		});
		blanca.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		blanca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blancaActionPerformed(e);
			}
		});
		blanca.setBorder(BorderFactory.createEmptyBorder());
		blanca.setContentAreaFilled(false);
		blanca.setBounds(50, 500, 50, 50);
		panel.add(blanca);
		blanca.setFocusable(false);
		
		Image buttonIcon1 = Toolkit.getDefaultToolkit().getImage("Presentacion/img/negra.png");
		negra = new JButton(new ImageIcon(buttonIcon1));
		negra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				negra.setFocusable(true);
			}
		});
		negra.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		negra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				negraActionPerformed(e);
			}
		});
		negra.setBorder(BorderFactory.createEmptyBorder());
		negra.setContentAreaFilled(false);
		negra.setBounds(150, 500, 50, 50);
		panel.add(negra);
		negra.setFocusable(false);
		
		Image buttonIcon2 = Toolkit.getDefaultToolkit().getImage("Presentacion/img/jugable.png");
		jugable = new JButton(new ImageIcon(buttonIcon2));
		jugable.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				jugable.setFocusable(true);
			}
		});
		jugable.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		jugable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugableActionPerformed(e);
			}
		});
		jugable.setBorder(BorderFactory.createEmptyBorder());
		jugable.setContentAreaFilled(false);
		jugable.setBounds(250, 500, 50, 50);
		panel.add(jugable);
		jugable.setFocusable(false);
		
		Image buttonIcon3 = Toolkit.getDefaultToolkit().getImage("Presentacion/img/solucionar.png");
		solucionar = new JButton(new ImageIcon(buttonIcon3));
		solucionar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				solucionar.setFocusable(true);
			}
		});
		solucionar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		solucionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solucionarActionPerformed(e);
			}
		});
		solucionar.setBorder(BorderFactory.createEmptyBorder());
		solucionar.setContentAreaFilled(false);
		solucionar.setBounds(330, 495, 200, 50);
		panel.add(solucionar);
		solucionar.setFocusable(false);
		
		Image buttonIcon4 = Toolkit.getDefaultToolkit().getImage("Presentacion/img/guardar2.png");
		guardar = new JButton(new ImageIcon(buttonIcon4));
		guardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				guardar.setFocusable(true);
			}
		});
		guardar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarActionPerformed(e);
			}
		});
		guardar.setBorder(BorderFactory.createEmptyBorder());
		guardar.setContentAreaFilled(false);
		guardar.setBounds(570, 505, 200, 50);
		panel.add(guardar);
		guardar.setFocusable(false);
		
		tablero = new JPanel();
		tablero.setBounds(36, 84, 711, 405);
		tablero.setBackground(Color.WHITE);
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		
		aux = new JPanel();
		aux.setBounds(36, 84, 711, 405);
		aux.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(aux);
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
	
	public void blancaActionPerformed(ActionEvent e) {
		if (!(aa.isEmpty())) {
			int i = casillaaux.getY()/columSize;
			int j = casillaaux.getX()/filaSize+1;
			x.get(j).set(i, "?");
			kakuro.get(i+1).set(j-1, "?");
			casillaaux = null;
			aa.clear();
			pintar();
		}
	}
	
	public void negraActionPerformed(ActionEvent e) {
		if (!(aa.isEmpty())) {
			int i = casillaaux.getY()/columSize;
			int j = casillaaux.getX()/filaSize+1;
			x.get(j).set(i, "*");
			kakuro.get(i+1).set(j-1, "*");
			casillaaux = null;
			aa.clear();
			pintar();
		}
	}
	
	
	public void jugableActionPerformed(ActionEvent e) {
		if (!(aa.isEmpty())) {
			boolean aux = jugable();
			if (aux) {
				int i = casillaaux.getY()/columSize;
				int j = casillaaux.getX()/filaSize+1;
				if (sumafil.getText().equals("")) {
					x.get(j).set(i, "C" + sumacol.getText());
					kakuro.get(i+1).set(j-1, "C" + sumacol.getText());
				}
				else if (sumacol.getText().equals("")) {
					x.get(j).set(i, "F" + sumafil.getText());
					kakuro.get(i+1).set(j-1, "F" + sumafil.getText());
				}
				else {
					x.get(j).set(i, "C" + sumacol.getText() + "F" + sumafil.getText());
					kakuro.get(i+1).set(j-1, "C" + sumacol.getText() + "F" + sumafil.getText());
				}
				casillaaux = null;
				aa.clear();
				pintar();
			}
		}
	}
	
	public boolean jugable() {
	    sumafil = new JTextField(5);
	    sumacol = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Suma de las filas:"));
	    myPanel.add(sumafil);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Suma de las columnas:"));
	    myPanel.add(sumacol);
	    int result = JOptionPane.showConfirmDialog(null, myPanel,
	            "Introduce las dimensiones del kakuro:", JOptionPane.OK_CANCEL_OPTION);
    	String aux = sumafil.getText();
		String aux2 = sumacol.getText();
	    if (result == JOptionPane.OK_OPTION) {
	    	if (aux.equals("") && aux2.equals("")) {
	    		JOptionPane.showMessageDialog(frame, "Rellena las casillas!","Error", JOptionPane.WARNING_MESSAGE);
	    		jugable();
	    	}
	    	else {
	    		if (aux.equals("")) {
					int sumacolnum = Integer.parseInt(aux2);
			        if (sumacolnum < 2 || sumacolnum > 45) {
			        	JOptionPane.showMessageDialog(frame, "Sumas entre 2 y 45!",
			                    "Error", JOptionPane.WARNING_MESSAGE);
			        	jugable();
			        }

	    		}
	    		else if (aux2.equals("")) {
		    		int sumafilnum = Integer.parseInt(aux);
			        if (sumafilnum < 2 ||sumafilnum > 45) {
			        	JOptionPane.showMessageDialog(frame, "Sumas entre 2 y 45!",
			                    "Error", JOptionPane.WARNING_MESSAGE);
			        	jugable();
			        }
	    		}
	    		else {
		    		int sumafilnum = Integer.parseInt(aux);
					int sumacolnum = Integer.parseInt(aux2);
			        if (sumafilnum < 2 || sumacolnum < 2 || sumafilnum > 45 || sumacolnum > 45) {
			        	JOptionPane.showMessageDialog(frame, "Sumas entre 2 y 45!",
			                    "Error", JOptionPane.WARNING_MESSAGE);
			        	jugable();
			        }
	    		}
	    	}
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public void solucionarActionPerformed(ActionEvent e) {
		try {
			long startTime = System.currentTimeMillis();
			soluciones = CP.validar(kakuro, n, m, id);
			long stopTime = System.currentTimeMillis();
			long tiempo =  stopTime - startTime;
			if (soluciones == 0) JOptionPane.showMessageDialog(frame, "Kakuro sin soluci�n.\r\nSolucionado en " + ((double)(tiempo))/1000  + " segundos.");
			if (soluciones == 1) JOptionPane.showMessageDialog(frame, "Kakuro con soluci�n.\r\nSolucionado en " + ((double)(tiempo))/1000  + " segundos.");
			if (soluciones == 2) JOptionPane.showMessageDialog(frame, "Kakuro con m�ltiples soluciones.\r\nSolucionado en " + ((double)(tiempo))/1000  + " segundos.");
		}
		catch(Exception ee) {
			soluciones = 0;
			JOptionPane.showMessageDialog(frame, "El formato del kakuro es incorrecto!",
                    "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void guardarActionPerformed(ActionEvent e) {
		if (soluciones == -1) JOptionPane.showMessageDialog(frame, "Soluciona el kakuro primero para poder guardarlo!",
                "Error", JOptionPane.WARNING_MESSAGE);
		else if (soluciones == 0) JOptionPane.showMessageDialog(frame, "El kakuro debe tener soluci�n para poder guardarlo!",
                "Error", JOptionPane.WARNING_MESSAGE);
		else {
			id = CP.tamañoDirectorio();
			++id;
			CP.guardarkakuro(kakuro, id);
			JOptionPane.showMessageDialog(frame, "Kakuro " + id + " guardado correctamente");
			PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
	        frame.dispose();
		}
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		Gestionarkakuro GK = new Gestionarkakuro(CP, usuario);
        frame.dispose();
	}
	
	private void initdim() {
	    dimX = new JTextField(5);
	    dimY = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Filas:"));
	    myPanel.add(dimX);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Columnas:"));
	    myPanel.add(dimY);
	    int result = JOptionPane.showConfirmDialog(null, myPanel,
	            "Introduce las dimensiones del kakuro:", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {
	    	String aux = dimX.getText();
			String aux2 = dimY.getText();
	    	if (aux.equals("") || aux2.equals("")) {
	    		JOptionPane.showMessageDialog(frame, "Rellena las casillas!","Error", JOptionPane.WARNING_MESSAGE);
	    		initdim();
	    	}
	                
	    	else {
	    		n = Integer.parseInt(aux);
				m = Integer.parseInt(aux2);
		        if (n < 6 || m < 6 || n > 15 || m > 15) {
		        	JOptionPane.showMessageDialog(frame, "Dimensiones entre 6 y 15!",
		                    "Error", JOptionPane.WARNING_MESSAGE);
		        	initdim();
		        }
		        else {
		        	initialize2();
		        }
	    	}
	   }
	    else {
	    	Gestionarkakuro GK = new Gestionarkakuro(CP, usuario);
	    	frame.dispose();
	    }
	}
	
	private void initialize1() {

		aux.setMaximumSize(new java.awt.Dimension(711, 405));
		aux.setMinimumSize(new java.awt.Dimension(711, 405));
		aux.setPreferredSize(new java.awt.Dimension(711, 405));
		aux.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout TaulerLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(TaulerLayout);
        TaulerLayout.setHorizontalGroup(
            TaulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );
        TaulerLayout.setVerticalGroup(
            TaulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );

        aux.add(tablero); 
        tablero.setLocation(100,100);
	}
	
	private void initialize2() {
		tablero.setBackground(Color.BLACK);
	    
        filaSize = 711/m;
        columSize = 405/n;

	    for (int i=1; i<=m; i++) {
	        for (int j=0; j<n; j++) {
	        	
	            JLabel b = new JLabel();
	            
	            tablero.add(b);
	            b.setBounds((i-1)*filaSize, j*columSize, filaSize, columSize);
	            b.setOpaque(true);                
	            b.setBackground(Color.white);
	            b.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
	            b.setVisible(true);
	            
	            JLabel lab = new JLabel ("?");
	            b.setHorizontalAlignment(SwingConstants.CENTER);
	            b.setVerticalAlignment(SwingConstants.CENTER);

	            lab.setHorizontalAlignment(SwingConstants.CENTER);
	            lab.setVerticalAlignment(SwingConstants.CENTER); 
	            String aux = lab.getText();
	            
	            if (aux.charAt(0) == '?') {
	        		lab.setVisible(false);
	            }

	            b.add(lab);
	            
	    		b.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent e) {
	    				casillamouseClicked(e, aux, b, lab);
	    			}
	    		});

	            lab.setBounds(filaSize/4, columSize/4, filaSize/2, columSize/2);

	            b.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
	        }
	    }
	    
	    kakuro = new ArrayList<List<String>>();
	    List<String> auxl = new ArrayList<String>();
	    auxl.add(String.valueOf(n));
	    auxl.add(String.valueOf(m));
	    kakuro.add(auxl);
	    for (int i=1; i<=n; i++) {
	    	auxl = new ArrayList<String>();
	    	for (int j=0; j<m; j++) {
	    		auxl.add("?");
	    	}
	    	 kakuro.add(auxl);
	    }
	    x = transKakuro2(kakuro);
	}
	
	private void pintar() {
	    for (int i=1; i<=m; i++) {
	        for (int j=0; j<n; j++) {

	            JLabel b = new JLabel();

	            tablero.add(b);
	            b.setBounds((i-1)*filaSize, j*columSize, filaSize, columSize);
	            b.setOpaque(true);                
	            b.setBackground(Color.white);
	            b.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
	            b.setVisible(true);
	            
	            JLabel lab = new JLabel (x.get(i).get(j));
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
	            
	            
	    		b.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent e) {
	    				casillamouseClicked(e, aux, b, lab);
	    			}
	    		});
	            
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
        	String auxi = s.replaceFirst("F","");
        	s = "";
        	s += "\\";
          s += auxi;
          return s;
        }
        else{
          s = s.replaceFirst("C","");
          s += "\\";
          return s;
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
	
	public void casillamouseClicked(MouseEvent e, String aux, JLabel b, JLabel lab) {
		if (!aa.isEmpty()) {
			(aa.get(0)).setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
			aa.clear();		
		}
		casillaaux = b;
		aa.add(b);
	
		b.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.BLUE));
		int i = casillaaux.getY()/columSize;
		int j = casillaaux.getX()/filaSize+1;
		
		lab.setText(x.get(j).get(i));
		
		if((x.get(j).get(i)).equals("?") || (x.get(j).get(i)).equals("*")) {
			lab.setVisible(false);	
		}else {
			lab.setVisible(true);
		}
		if ((x.get(j).get(i)).equals("*")) {
			b.setBackground(Color.black);
		}
		if ((x.get(j).get(i)).charAt(0) == 'C' || (x.get(j).get(i)).charAt(0) == 'F') {
			b.setBackground(Color.gray);
		}
		if ((x.get(j).get(i)).equals("?")) {
            b.setBackground(Color.white);
        }
		String a = x.get(j).get(i);
		if (a.charAt(0) == 'F' || a.charAt(0) == 'C' || a.charAt(0) == '1' || a.charAt(0) == '2' || a.charAt(0) == '3' || a.charAt(0) == '4' || a.charAt(0) == '5' || a.charAt(0) == '6' || a.charAt(0) == '7' || a.charAt(0) == '8' || a.charAt(0) == '9') {
			lab.setText(leerCasillaJugable(x.get(j).get(i)));
			lab.setForeground(Color.white);
		}else {
			lab.setText(x.get(j).get(i));
		}
	}
}

/** @class Crear
 *  Clase destinada a proponer un kakuro, validarlo y guardarlo si es correcto.
 *  @author Mart� Westermeyer
 */
