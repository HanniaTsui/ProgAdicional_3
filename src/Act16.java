import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

public class Act16 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEscribir;
	private JLabel lblTiempo;
	private JLabel lblPalabra;
	
	private String[] palabras = {"MOWGLI", "ARISTOGATOS", "STITCH", "TIGGER", "HERCULES", "TINKER BELL", "URSULA", "SPIDER MAN", "SHIELD", "YZMA", "QUASIMODO", 
			"HAKUNA MATATA", "DISNEY", "MARVEL", "ALADDIN", "CRUELLA DE VIL", "MICKEY MOUSE", "MIKE WAZOWSKI", "MUSHU", "PERRY", "WINNIE THE POOH", "CENICIENTA",
			"POCAHONTAS", "GOOFY", "IRON MAN", "NICK FURY", "JAFAR", "QUICKSILVER", "MS MARVEL", "VISION", "HAWKEYE", "HULK", "THOR", "THANOS", "LOKI", "WOLVERINE"};
    private Random random = new Random();
    private String palabraActual;
    private Timer tiempo;
	private int h,m,s, ms;
	private JLabel lblNota;
	private JButton btnEspacio;

	
	public Act16() {
		this.setTitle("Juego de palabras");
        this.setSize(800, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        getContentPane().setLayout(null);
        
        juego();
        this.setVisible(true);
        letrasPorTeclado();
	}
	
	public void juego() {
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBounds(0, 0, 786, 513);
        getContentPane().add(panelPrincipal);
        panelPrincipal.setLayout(new BorderLayout(0, 0));
        
        JPanel panelNorte = new JPanel();
        panelNorte.setBorder(new EmptyBorder(10, 0, 10, 30));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        panelNorte.setBackground(new Color(42, 71, 104));
        panelNorte.setLayout(new GridLayout(0, 3, 0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("");
        panelNorte.add(lblNewLabel_1);
        
        lblPalabra = new JLabel("");
        lblPalabra.setFont(new Font("Century", Font.PLAIN, 20));
        lblPalabra.setForeground(Color.white);
        lblPalabra.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
        panelNorte.add(lblPalabra);
        
        lblTiempo = new JLabel("  00:00:00:00");
        lblTiempo.setForeground(Color.white);
        lblTiempo.setFont(new Font("Century", Font.PLAIN, 20));
        lblTiempo.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
        panelNorte.add(lblTiempo);
        
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(149, 175, 214));
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelCentro.setLayout(null);
        
        lblNota = new JLabel("Escribe aquí");
        lblNota.setFont(new Font("Century", Font.PLAIN, 14));
        lblNota.setHorizontalAlignment(SwingConstants.CENTER);
        lblNota.setForeground(new Color(192, 192, 192));
        lblNota.setBounds(104, 102, 573, 35);
        panelCentro.add(lblNota);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(104, 150, 573, 200);
        panelBotones.setBackground(new Color(149, 175, 214));
        panelCentro.add(panelBotones);
        panelBotones.setLayout(new GridLayout(3, 9, 0, 0));
        
        lblEscribir = new JLabel("");
        lblEscribir.setHorizontalAlignment(SwingConstants.CENTER);
        lblEscribir.setForeground(new Color(255, 255, 255));
        lblEscribir.setOpaque(true);
        lblEscribir.setBackground(new Color(0, 0, 0));
        lblEscribir.setFont(new Font("Century", Font.PLAIN, 20));
        lblEscribir.setBounds(104, 83, 573, 69);
        panelCentro.add(lblEscribir);
        
        JPanel panelSur = new JPanel();
        panelSur.setBorder(new EmptyBorder(20, 0, 20, 0));
        panelSur.setBackground(new Color(0, 0, 0));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
        
        JButton btnDelete = new JButton("");
        btnDelete.setBorder(null);
        btnDelete.setFocusable(false);
        btnDelete.setBackground(new Color(0, 0, 0));
        btnDelete.setIcon(new ImageIcon(Act16.class.getResource("/img/borrar.png")));
        btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(lblEscribir.getText().length() > 0) {
					lblEscribir.setText(lblEscribir.getText().substring(0, lblEscribir.getText().length() - 1));
				}
			}
        	
        });
        panelSur.setLayout(new GridLayout(0, 3, 0, 0));
        
        
        JLabel lblNewLabel = new JLabel("");
        panelSur.add(lblNewLabel);
        
        btnEspacio = new JButton(" ");
        btnEspacio.setIcon(new ImageIcon(Act16.class.getResource("/img/espacio.png")));
        btnEspacio.setForeground(new Color(255, 255, 255));
        btnEspacio.setBorder(null);
        btnEspacio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLetra(btnEspacio.getText());
            }
        });
        btnEspacio.setBackground(new Color(0, 0, 0));
        btnEspacio.setFocusable(false);
        panelSur.add(btnEspacio);
        panelSur.add(btnDelete);
        
        for (char letras = 'A'; letras <= 'Z'; letras++) {
            JButton btn = new JButton(String.valueOf(letras));
            if (letras == 'N') {
                JButton btnN = new JButton("Ñ");
                panelBotones.add(btnN);
                btnN.setForeground(Color.BLACK);
                btnN.setFocusable(false);
                btnN.setFont(new Font("Century", Font.PLAIN, 20));
                btnN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        agregarLetra(btnN.getText());
                    }
                });
                btnN.setBackground(new Color (60, 87, 121));
            }

            btn.setFont(new Font("Century", Font.PLAIN, 20));
            btn.setForeground(Color.BLACK);
            btn.setBackground(new Color (60, 87, 121));
            btn.setFocusable(false);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarLetra(btn.getText());
                }
            });
            panelBotones.add(btn);
        }
 
        empezarJuego();
	}
	
	public void empezarJuego() {
		palabraActual = palabras[random.nextInt(palabras.length)];
        lblPalabra.setText(palabraActual);
        lblEscribir.setText("");
        lblNota.setVisible(true);
        reiniciarTiempo();
        cronometro();
        desactivarBoton();
	}
	
	
	public void reiniciarTiempo() {
        h = 0;
        m = 0;
        s = 0;
        ms=0;
        String tiempoNuevo = String.format("  %02d:%02d:%02d:%02d", h, m, s,ms);
        lblTiempo.setText(tiempoNuevo);
    }
	
	public void actualizarTiempo() {
		ms += 10;
        if (ms == 1000) {
            s++;
            ms = 0;
        }
        if (s == 60) {
            m++;
            s = 0;
        }
        if (m == 60) {
            h++;
            m = 0;
        }
        String tiempoNuevo = String.format("  %02d:%02d:%02d:%02d", h, m, s, ms);
        lblTiempo.setText(tiempoNuevo);
    }
    
    // Método para iniciar el cronómetro
    public void cronometro() {
        tiempo = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();
            }
        });
        tiempo.start();
    }

    public void agregarLetra(String letra) {
        lblNota.setVisible(false);
    	if (letra.equals(" ")) {
            lblEscribir.setText(lblEscribir.getText() + " ");
        } else {
            lblEscribir.setText(lblEscribir.getText() + letra);
        }
        if (lblEscribir.getText().equals(palabraActual)) {
            tiempo.stop();
            JOptionPane.showMessageDialog(null, "¡Correcto! Tiempo: " + lblTiempo.getText());
            empezarJuego();
        }
        desactivarBoton();
    }
    
    public void desactivarBoton() {
        if (lblEscribir.getText().isEmpty()) {
            btnEspacio.setEnabled(false);
        } else {
            btnEspacio.setEnabled(true);
        }
    }
    
    public void letrasPorTeclado() {
    	this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLetter(keyChar) || Character.isSpaceChar(keyChar)) {
                    agregarLetra(String.valueOf(keyChar).toUpperCase());
                } else if (keyChar == KeyEvent.VK_BACK_SPACE && lblEscribir.getText().length() > 0) {
                    lblEscribir.setText(lblEscribir.getText().substring(0, lblEscribir.getText().length() - 1));
                }
            }
        });
    }
}
