import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

public class Act20 extends JFrame implements KeyListener{

    private int pelotaX = 250; //UBICACIÓN ORIGINAL DE LA PELOTA EN X
    private int pelotaY = 150; //UBICACIÓN ORIGINAL DE LA PELOTA EN Y
    private int velocidadX = 2; 
    private int velocidadY = 2;
    private int raquetaX = 200; //UBICACIÓN ORIGINAL DE LA RAQUETA EN X
    private int raquetaY = 400; //UBICACIÓN ORIGINAL DE LA RAQUETA EN Y
    private int raquetaAncho = 100; // TAMAÑO DE LA RAQUETA 
    private int raquetaAlto = 20;  // TAMAÑO DE LA RAQUETA
    private int contador = 0; // CONTADOR DE PUNTOS
	private JLabel lblCont;
	private boolean colisionDetectada = false;
	private int colisiones = 0;
	private Color colorPelota = Color.WHITE;
	private JButton btnPausa;
	private Timer timer;

    public Act20() {
    	this.setTitle("Juego de Tenis");
        this.setSize(500, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        getContentPane().setLayout(new BorderLayout(0, 0));
        

        juego();
        this.setVisible(true);
        addKeyListener(this);
        
    }
    

    public void juego() {
    	 JPanel panel = new JPanel() {
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.setColor(colorPelota);
                 g.fillOval(pelotaX, pelotaY, 20, 20);
                 g.setColor(Color.white);
                 g.fillRect(raquetaX, raquetaY, raquetaAncho, raquetaAlto);         
             }
         };
    	 panel.setBackground(new Color(0, 0, 0));
         panel.setLayout(new BorderLayout());
         getContentPane().add(panel, BorderLayout.CENTER);
         
         JPanel panelContador = new JPanel();
         panelContador.setBackground(new Color(192, 192, 192));
         panelContador.setBorder(new EmptyBorder(5, 10, 5, 185));
         getContentPane().add(panelContador, BorderLayout.NORTH);
         panelContador.setLayout(new BorderLayout(0, 0));
         
         btnPausa = new JButton();
         btnPausa.setBackground(new Color(128, 128, 192));
         btnPausa.setFocusable(false);
         panelContador.add(btnPausa, BorderLayout.WEST);
         btnPausa.setText("Pausa");
         btnPausa.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 pausarJuego();
             }
         });
         
         lblCont = new JLabel();
         lblCont.setFont(new Font("Tahoma", Font.PLAIN, 18));
         lblCont.setText("Puntuación: " + contador);
         panelContador.add(lblCont, BorderLayout.EAST);
         
         
         
         timer = new Timer(5, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 moverPelota();
                 repaint();
             }
         });
         timer.start();
         
    }
    

    public void moverPelota() {
        pelotaX += velocidadX; //Actualizar la posicion de la pelota
        pelotaY += velocidadY;
        if (pelotaX <= 0 || pelotaX >= 470) { // Colision con los bordes izq y derecho de la ventana 
            velocidadX = -velocidadX;
        }
        if (pelotaY <= 0) { // colision con el borde de arriba de la ventana
            velocidadY = -velocidadY;
        }
        //colision entre a pelota y la raqueta
        if (pelotaY + 20 >= raquetaY && pelotaY <= raquetaY + raquetaAlto && pelotaX + 20 >= raquetaX && pelotaX <= raquetaX + raquetaAncho) {
        	 if (!colisionDetectada) {
                 velocidadY = -velocidadY;
                 contador++;
                 lblCont.setText("Puntuación: " + contador);
                 colisionDetectada = true; // Establecer la colisión como detectada
                 colisiones++; // Incrementar el contador de colisiones
                 if (colisiones == 5) { //A la 5ta colision cambia de color
                	 cambiarColorPelota();
                	 colisiones = 0; // Restablecer contador de colisiones
                 }
        	 }
         } else {
             colisionDetectada = false; // Restablecer la variable para la próxima colisión 
        }
        if (pelotaY >= 470) { // EN CASO DE QUE PIERDA 
            pelotaX = 250;
            pelotaY = 150;
            raquetaX = 200;
            raquetaY = 400;
            JOptionPane.showMessageDialog(this, "¡Perdiste! Tu puntuación fue: " + contador, "Perdiste",
                    JOptionPane.ERROR_MESSAGE);
            contador = 0;
            lblCont.setText("Puntuación: " + contador);
        }
    }

    public void moverRaqueta(int direccion) {
        if (raquetaX >= 0 && raquetaX <= 400) { // Limites de la ventana
            raquetaX += direccion;
        }
        if (raquetaX < 0) { // Colision con borde izq
            raquetaX = 0;
        }
        if (raquetaX > 400) { // Colision con borde derecho
            raquetaX = 400;
        }
    }


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            moverRaqueta(-10);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moverRaqueta(10);
        }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	public void cambiarColorPelota() {
		do {
            colorPelota = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                    (int) (Math.random() * 256));
        } while (colorPelota.equals(Color.BLACK)); // Mientras el color sea negro, generar un nuevo color
	}
	
	 public void pausarJuego() {
	        if (timer.isRunning()) {
	            timer.stop();
	            btnPausa.setText("Continuar");
	        } else {
	            timer.start();
	            btnPausa.setText("Pausa");
	        }
	    }
}
