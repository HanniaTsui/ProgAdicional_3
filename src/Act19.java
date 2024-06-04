
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Act19 extends JFrame {
	private JPanel panelDibujo;
	private JLabel lblEscribir;
	private JLabel[] erroresLabel;
    private int errores, puntos=100;
    private String palabraActual;
    private JLabel[] partesAhorcado;
    private StringBuilder palabraMostrada;
    private List<String> palabras = new ArrayList<>();
	private JLabel lblPuntos;
	private String palabraSinEspacios;
	private JPanel panelLetras;
    
	
	public Act19() {
		this.setTitle("Ahorcado");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        getContentPane().setLayout(null);
        palabras.addAll(Arrays.asList(
                "S P I D E R M A N", "I R O N M A N", "T H O R", "B L A C K W I D O W", 
                "H U L K", "C A P I T A N", "H A W K E Y E", "A N T M A N", "W A S P", 
                "S T R A N G E", "S C A R L E T W I T C H", "B L A C K P A N T H E R", 
                "M A R V E L", "V I S I O N", "F A L C O N", "A V E N G E R S", 
                "C A B L E", "S T A R L O R D", "G R O O T", "R O C K E T", 
                "D R A X", "N E B U L A", "G A M O R A", "T H A N O S", "L O K I", 
                "U L T R O N", "D A R E D E V I L", "V E N O M", "N A M O R", 
                "D O O M", "K A N G", "M Y S T E R I O", "E L E C T R O", 
                "S A N D M A N", "B I S H O P", "R H I N O", "W O L V E R I N E", 
                "C I C L O P E", "S T O R M", "B E S T I A", "F E N I X", 
                "N I G H T C R A W L E R", "P R O F E S O R X", "M A G N E T O", 
                "M Y S T I Q U E", "D E A D P O O L", "H A W K G I R L", 
                "M A G I K", "H E L A", "P U N I S H E R", "W A R L O C K", 
                "F U R Y", "V A L Q U I R I A", "S A B R E T O O T H", "O K O Y E", 
                "G O R R", "Z E M O", "T H I N G", "A N T O R C H A", "M O D O K", 
                "M A N D A R I N", "T A S K M A S T E R", "R O N A N", "E G O", 
                "G A M B I T O", "B U I T R E", "K I L L M O N G E R", "R O G U E", 
                "I C E M A N", "P Y R O", "J U B I L O", "A N G E L", "D O R M A M M U", 
                "H A V O K", "V U L C A N O", "B A N S H E E", "C O L O S O", 
                "P S Y L O C K E"
            ));
        
        tablero();
        this.setVisible(true);
        letrasPorTeclado();
	}
 
    
    public void tablero() {
    	
    	JPanel panelMenu = new JPanel();
    	panelMenu.setBorder(new TitledBorder(null, "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelMenu.setBounds(45, 75, 290, 94);
        getContentPane().add(panelMenu);
        panelMenu.setLayout(null);
        
        JButton btnResolver = new JButton("Resolver");
        btnResolver.setForeground(new Color(255, 255, 255));
        btnResolver.setBackground(new Color(44, 77, 86));
        btnResolver.setFocusable(false);
        btnResolver.setBounds(10, 63, 110, 21);
        panelMenu.add(btnResolver);
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverJuego();
            }
        });  
        
        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBackground(new Color(195, 170, 114));
        btnIniciar.setFocusable(false);
        btnIniciar.setBounds(10, 20, 270, 21);
        panelMenu.add(btnIniciar);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(192, 192, 192));
        btnSalir.setFocusable(false);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSalir.setBounds(170, 63, 110, 21);
        panelMenu.add(btnSalir);
        
        panelDibujo = new JPanel();
        panelDibujo.setBorder(new TitledBorder(null, "Ahorcado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDibujo.setBounds(380, 75, 261, 352);
        getContentPane().add(panelDibujo);
        panelDibujo.setLayout(null);
        
        partesAhorcado = new JLabel[7]; 
		for (int i = 1; i < partesAhorcado.length; i++) {
		    partesAhorcado[i] = new JLabel("",0);
		    partesAhorcado[i].setIcon(new ImageIcon(Act19.class.getResource("/img/ahorcado" + i + ".png")));
		    panelDibujo.add(partesAhorcado[i]);
		    partesAhorcado[i].setVisible(false);
		}
		partesAhorcado[1].setBounds(48, 95, 60, 60); 
		partesAhorcado[2].setBounds(76, 150, 4, 90);
		partesAhorcado[3].setBounds(38, 150, 44, 51); 
		partesAhorcado[4].setBounds(76, 150, 44, 51);
		partesAhorcado[5].setBounds(38, 238, 44, 51); 
		partesAhorcado[6].setBounds(76, 238, 44, 51);
        
        JLabel lblAhorcado = new JLabel("");
        lblAhorcado.setBounds(0, 10, 261, 305);
        panelDibujo.add(lblAhorcado);
        
        ImageIcon icono = new ImageIcon(Act19.class.getResource("/img/ahorcad.png"));
        Image imagen = icono.getImage().getScaledInstance(261, 305, java.awt.Image.SCALE_SMOOTH);
        lblAhorcado.setIcon(new ImageIcon(imagen));
        
        lblPuntos = new JLabel("Puntos: " +puntos);
        lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
        lblPuntos.setBounds(10, 329, 241, 13);
        panelDibujo.add(lblPuntos);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel.setBounds(45, 195, 290, 232);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel panelPalabra = new JPanel();
        panelPalabra.setBorder(new TitledBorder(null, "Palabra oculta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPalabra.setBounds(10, 10, 270, 96);
        panel.add(panelPalabra);
        panelPalabra.setLayout(null);
        
        lblEscribir = new JLabel("");
        lblEscribir.setOpaque(true);
        lblEscribir.setBackground(new Color(0, 0, 0));
        lblEscribir.setForeground(new Color(255, 255, 255));
        lblEscribir.setHorizontalAlignment(SwingConstants.CENTER);
        lblEscribir.setBounds(10, 19, 250, 63);
        lblEscribir.setFont(new Font("Mistral", Font.PLAIN, 20));
        panelPalabra.add(lblEscribir);
        
        JPanel panelErrores = new JPanel();
        panelErrores.setBorder(new TitledBorder(null, "Errores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelErrores.setBounds(10, 123, 270, 89);
        panel.add(panelErrores);
        panelErrores.setLayout(null);
        
        erroresLabel = new JLabel[6];
        for (int i = 0; i < erroresLabel.length; i++) {
            erroresLabel[i] = new JLabel("");
            erroresLabel[i].setIcon(new ImageIcon(Act19.class.getResource("/img/error.png")));
            erroresLabel[i].setBounds(10 + i * 43, 31, 32, 32);
            panelErrores.add(erroresLabel[i]);
        }
        
        panelLetras = new JPanel();
        panelLetras.setBounds(45, 453, 596, 170);
        panelLetras.setLayout(new GridLayout(3, 9, 0, 0));
        getContentPane().add(panelLetras);
        
        JLabel lblTitulo = new JLabel("Ahorcado - Version Marvel");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("Century", Font.PLAIN, 24));
        lblTitulo.setBounds(0, 22, 686, 33);
        getContentPane().add(lblTitulo);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Act19.class.getResource("/img/fondo.png")));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 686, 663);
        getContentPane().add(lblNewLabel);
    	
		for (char letras = 'A'; letras <= 'Z'; letras++) {
	         JButton btn = new JButton(String.valueOf(letras));
	         if (letras == 'N') {
	             JButton btnN = new JButton("Ñ");
	             panelLetras.add(btnN);
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
	         panelLetras.add(btn);
	     }
    	
		
    }
    
    private void iniciarJuego() {
    	puntos=100;
        lblPuntos.setText("Puntos: " + puntos);
    	for (Component componente : panelLetras.getComponents()) {
            if (componente instanceof JButton) {
                JButton btn = (JButton) componente;
                btn.setEnabled(true);
            }
        }
    	for (int i = 1; i < partesAhorcado.length; i++) {
		    partesAhorcado[i].setVisible(false);
		}
    	Collections.shuffle(palabras);
        palabraActual = palabras.get(1);
        for (String palabra : palabras) {
            palabraSinEspacios = palabraActual.replace(" ", "");
        }
        palabraMostrada = new StringBuilder("_ ".repeat(palabraSinEspacios.length()));
        errores = 0;
        lblEscribir.setText(palabraMostrada.toString());
        for (JLabel errorLabel : erroresLabel) {
            errorLabel.setVisible(false);
        }
    }
    
    private void agregarLetra(String letra) {
        boolean acierto = false;
        if (palabraActual == null) {
            JOptionPane.showMessageDialog(this, "Inicia el juego primero.");
            return;
        }
        for (Component componente : panelLetras.getComponents()) {
            if (componente instanceof JButton) {
                JButton btn = (JButton) componente;
                if (btn.getText().equals(letra)) {
                    btn.setEnabled(false); // Desactiva el botón seleccionado
                    break;
                }
            }
        }
        for (int i = 0; i < palabraActual.length(); i++) {
            if (palabraActual.charAt(i) == letra.charAt(0)) {
                palabraMostrada.setCharAt(i, letra.charAt(0));
                acierto = true;
            } 
        }
        
        if (acierto) {
            lblEscribir.setText(palabraMostrada.toString());
            puntos+=10;
            lblPuntos.setText("Puntos: " + puntos);
            if (palabraMostrada.toString().replaceAll(" ", "").equals(palabraActual.replaceAll(" ", "")))  {
                JOptionPane.showMessageDialog(this, "¡Correcto! Has ganado. Puntaje: "+puntos);
                iniciarJuego();
            }
        } else {
            errores++;
            if (errores <= 6) {
            	if (puntos <= 10) {
                    puntos = 0;
                } else { 
                    puntos -= 10;
                }
                lblPuntos.setText("Puntos: " + puntos);
                erroresLabel[errores - 1].setVisible(true);
                partesAhorcado[errores].setVisible(true); 
            }
            if (errores >= 6) {
                JOptionPane.showMessageDialog(this, "Has perdido. La palabra era: " + palabraActual + " Puntaje: "+puntos);
                iniciarJuego();
            }
        }
    }
    
    private void resolverJuego() {
    	if (palabraActual == null) {
            JOptionPane.showMessageDialog(this, "Inicia el juego primero.");
            return;
        }
        JOptionPane.showMessageDialog(this, "La palabra era: " + palabraActual);
        iniciarJuego();
    }
    
    public void letrasPorTeclado() {
    	this.addKeyListener(new KeyAdapter() {
    		public void keyPressed(KeyEvent e) {
    	        char keyChar = e.getKeyChar();
    	        if (Character.isLetter(keyChar)) {
    	            String letra = String.valueOf(keyChar).toUpperCase();
    	            agregarLetra(letra);
    	        }
    	    }
        });
    }
}

