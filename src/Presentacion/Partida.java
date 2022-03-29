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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import Dominio.Clases.Jugable;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/** \file Partida.java
 *  Clase destinada a mostrar y jugar un kakuro, pedir una pista o guardar partida
 *
 */
public class Partida {

	private JFrame frame;
	private CtrlPresentacion CP;
	private String usuario;
	private JLabel logo;
	private BasicArrowButton retorno;
	private JLabel titulo;
	private JLabel fondo;
	private JButton uno;
	private JButton dos;
	private JButton tres;
	private JButton cuatro;
	private JButton cinco;
	private JButton seis;
	private JButton siete;
	private JButton ocho;
	private JButton nueve;
	private JButton pista;
	private JButton guardar;
	private JPanel tablero;
	private JPanel aux;
	private JLabel casillaaux;
	private Timer timer;
	private JLabel clock;
	private JPanel panel_tiempo;
	
	private List<JLabel> aa = new ArrayList<JLabel>();
	

	private List<List<String>> kakuro;
	private List<List<String>> x;
	private int id;
    private int n;
    private int m;
    private int filaSize;
    private int columSize;
    private Boolean rojo = false;
    private int coordi;
    private int coordj;
    private long startTime;
    private int numeropistas;


	
	public Partida (CtrlPresentacion CP, String usuario, int id) {
		this.CP = CP;
		this.usuario = usuario;
		this.id = id;
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
		initialize2();
	}

	private void initialize() {
		frame = new JFrame("KAKURO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();	
		panel.setBounds(0, 0, 784, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		titulo = new JLabel("Partida");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe print", Font.PLAIN, 36));
		titulo.setBounds(0, 26, 784, 54);
		panel.add(titulo);
		
		panel_tiempo = new JPanel();
		if(id == 0) {
			long x = CP.cargar_tiempo();
			startTime = x;
		}else {
			startTime = 0;
		}
		
	    timer = new Timer(500, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	          tickTock();
	        }
	      });
	      timer.setRepeats(true);
	      timer.setCoalesce(true);
	      timer.setInitialDelay(0);
	      timer.start();
		panel.add(panel_tiempo);
		panel_tiempo.setBounds(500, 30, 134, 41);
		panel_tiempo.setBackground(Color.white);
		panel_tiempo.setLayout(null);
		panel_tiempo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		clock = new JLabel();
		clock.setHorizontalAlignment(SwingConstants.RIGHT);
		clock.setBounds(new Rectangle(10, 12, 114, 20));
		clock.setText("esp");
		panel_tiempo.add(clock);
		//clock.revalidate();
		//clock.repaint();
		clock.setVisible(true);
		panel_tiempo.setVisible(true);
		
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
		
		uno = new JButton("1");
		uno.setFont(new Font("Tahoma", Font.BOLD, 18));
		uno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 1);
			}
		});
		uno.setBounds(98, 500, 50, 50);
		panel.add(uno);
		
		dos = new JButton("2");
		dos.setFont(new Font("Tahoma", Font.BOLD, 18));
		dos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 2);
			}
		});
		dos.setBounds(158, 500, 50, 50);
		panel.add(dos);
		
		tres = new JButton("3");
		tres.setFont(new Font("Tahoma", Font.BOLD, 18));
		tres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 3);
			}
		});
		tres.setBounds(218, 500, 50, 50);
		panel.add(tres);
		
		cuatro= new JButton("4");
		cuatro.setFont(new Font("Tahoma", Font.BOLD, 18));
		cuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 4);
			}
		});
		cuatro.setBounds(278, 500, 50, 50);
		panel.add(cuatro);
		
		cinco = new JButton("5");
		cinco.setFont(new Font("Tahoma", Font.BOLD, 18));
		cinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 5);
			}
		});
		cinco.setBounds(338, 500, 50, 50);
		panel.add(cinco);
		
		seis = new JButton("6");
		seis.setFont(new Font("Tahoma", Font.BOLD, 18));
		seis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 6);
			}
		});
		seis.setBounds(398, 500, 50, 50);
		panel.add(seis);
		
		siete = new JButton("7");
		siete.setFont(new Font("Tahoma", Font.BOLD, 18));
		siete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 7);
			}
		});
		siete.setBounds(458, 500, 50, 50);
		panel.add(siete);
		
		ocho = new JButton("8");
		ocho.setFont(new Font("Tahoma", Font.BOLD, 18));
		ocho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 8);
			}
		});
		
		ocho.setBounds(518, 500, 50, 50);
		panel.add(ocho);
		
		nueve = new JButton("9");
		nueve.setFont(new Font("Tahoma", Font.BOLD, 18));
		nueve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numButtonActionPerformed(e, 9);
			}
		});
		nueve.setBounds(578, 500, 50, 50);
		panel.add(nueve);
		
		Image buttonIcon = Toolkit.getDefaultToolkit().getImage("Presentacion/img/pista.png");
		pista = new JButton(new ImageIcon(buttonIcon));
		pista.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				pista.setFocusable(true);
			}
		});
		pista.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		pista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pistaActionPerformed(e);
			}
		});
		pista.setBorder(BorderFactory.createEmptyBorder());
		pista.setContentAreaFilled(false);
		pista.setBounds(638, 510, 96, 32);
		panel.add(pista);
		pista.setFocusable(false);
		
		Image buttonIcon2 = Toolkit.getDefaultToolkit().getImage("Presentacion/img/guardar.png");
		guardar = new JButton(new ImageIcon(buttonIcon2));
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
		guardar.setBounds(46, 510, 32, 32);
		panel.add(guardar);
		guardar.setFocusable(false);
		
		tablero = new JPanel();
		tablero.setBounds(36, 84, 711, 405);
		tablero.setBackground(Color.BLACK);
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		//panel.add(tablero);
		
		aux = new JPanel();
		aux.setBounds(36, 84, 711, 405);
		aux.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(aux);
		
		
		fondo = new JLabel();
		fondo.setIcon(new javax.swing.ImageIcon("Presentacion/img/fondo.png"));
		fondo.setBounds(0, 0, 784, 561);
		panel.add(fondo);
	}
    public void tickTock() {
	    //clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
    	startTime += 500;
    	clock.setText(Long.toString(startTime/1000));
	}
	
	public void retornoActionPerformed(ActionEvent e) {
		PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
        frame.dispose();
	}

	public void numButtonActionPerformed(ActionEvent e, int num) {
		if (!(aa.isEmpty())) {
			int i = casillaaux.getY()/columSize;
			int j = casillaaux.getX()/filaSize+1;
			coordi = i;
			coordj = j-1;
			x.get(j).set(i, String.valueOf(num));
			kakuro.get(i+1).set(j-1, String.valueOf(num));
			casillaaux = null;
			boolean correcto = CP.partida(kakuro, n, m, id, i,j-1,num);
			if (correcto) {
				rojo = false;
			}else {
				rojo = true;
			}
			aa.clear();
			pintar(false);
			boolean acabado = CP.kakuroAcabado(kakuro, n, m, id);
			if (acabado) {
				timer.stop();
				if (id != 0)CP.guardarRanking(id, usuario, (double)startTime/1000);
				JOptionPane.showMessageDialog(frame, "Kakuro Resuelto!");
				PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
		        frame.dispose();
			} 
		}
    }

	public void pistaActionPerformed(ActionEvent e) {
		List<List<String>> mat = CP.getvalidartablero(kakuro, n, m, id);
		if (mat.isEmpty()) JOptionPane.showMessageDialog(frame, "Solo se puede pedir pista para kakuros con solución única!",
                "Error", JOptionPane.WARNING_MESSAGE);
		else {
			if (numeropistas == 3) JOptionPane.showMessageDialog(frame, "Solo se puede pedir 3 pistas por partida!",
	                "Error", JOptionPane.WARNING_MESSAGE);
			else {
				boolean pista = false;
				for (int i = 1; i <= n; ++i) {
					for (int j = 0; j < m; ++j) {
						String aux = mat.get(i).get(j);
						if (kakuro.get(i).get(j).equals("?") || kakuro.get(i).get(j).equals("1") || kakuro.get(i).get(j).equals("2") || kakuro.get(i).get(j).equals("3") || kakuro.get(i).get(j).equals("4")
								|| kakuro.get(i).get(j).equals("5") || kakuro.get(i).get(j).equals("6") || kakuro.get(i).get(j).equals("7") || kakuro.get(i).get(j).equals("8") || kakuro.get(i).get(j).equals("9")) {
							if (!pista && !(kakuro.get(i).get(j).equals(aux)))  {
								pista = true;
								++numeropistas;
								x.get(j+1).set(i-1, String.valueOf(aux));
								kakuro.get(i).set(j, String.valueOf(aux));
								coordi = i-1;
								coordj = j;
								pintar(true);
							}
						}
					}
				}
				if (!pista) JOptionPane.showMessageDialog(frame, "No hay ninguna pista disponible!",
		                "Error", JOptionPane.WARNING_MESSAGE);
				pista = false;
			}
		}
    }
	
	public void guardarActionPerformed(ActionEvent e) {
		timer.stop();
		int aux = JOptionPane.showConfirmDialog(
	            null,
	            
	            "<html><style>p{text-align: center;}</style><p>Desea guardar la partida?<br />Se sobreescribirá si hay una partida guardada anteriormente y perderá la opción a ranking.</p></html>",
	            "",
	            JOptionPane.YES_NO_OPTION);

	        if(aux == JOptionPane.YES_OPTION){
	    		CP.guardarPartida(kakuro, n, m, id, startTime);
	    		JOptionPane.showMessageDialog(frame, "Kakuro guardado correctamente");
	    		PantallaPrincipal P = new PantallaPrincipal(CP, usuario);
	            frame.dispose();
	        }else {
	        	timer.start();
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
        kakuro = CP.leerkakurotxt(id);
        
	    n = Integer.parseInt(kakuro.get(0).get(0));
	    m = Integer.parseInt(kakuro.get(0).get(1));
	   
        //int alt = getHeight();
        filaSize = 711/m;
        columSize = 405/n;
        
        x = transKakuro2(kakuro);    
        //transKakuro(kakuro);

       pintar(false);
	}
	private void pintar(boolean pista) {
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
	            
	            if((i-1) == coordj && j == coordi && rojo == true) {
	            	lab.setForeground(Color.red);
	            }
	            
	            if((i-1) == coordj && j == coordi && pista) {
	            	lab.setForeground(Color.orange);
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
            s = s.replaceFirst("F","");
            return s;
          }
          else{
            s = s.replaceFirst("C","");
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
	
		if (aux.charAt(0) == '?' || aux.charAt(0) == '1' || aux.charAt(0) == '2' || aux.charAt(0) == '3' || aux.charAt(0) == '4' || aux.charAt(0) == '5' || aux.charAt(0) == '6' || aux.charAt(0) == '7'
				|| aux.charAt(0) == '8' || aux.charAt(0) == '9') {
			casillaaux = b;
			aa.add(b);
		
			b.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.BLUE));
			int i = casillaaux.getY()/columSize;
			int j = casillaaux.getX()/filaSize+1;
			
			lab.setText(x.get(j).get(i));
			
			if((x.get(j).get(i)).equals("?")) {
				lab.setVisible(false);	
			}else {
				lab.setVisible(true);
			}			
		}
	}
}


/** @class Partida
 *  Clase destinada a mostrar y jugar un kakuro, pedir una pista o guardar partida
 *	@author Fèlix Forroll
 */