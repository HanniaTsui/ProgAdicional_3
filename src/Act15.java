import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Act15 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Timer tiempo;
    private int h, m, s, ms;
    private List<String> imagenes = new ArrayList<>();
    private JLabel lblTiempo;
    private JButton[] btnMemorama;
    private int paresEncontrados;
    private int primerIndice = -1;
    private int segundoIndice = -1;
    private JButton primerBoton;
    private boolean presionar = true;
    

    public Act15() {
        this.setTitle("Memorama");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        getContentPane().setLayout(new BorderLayout(0, 0));
        imagenes.add("img/logoHufflepuff.jpg");
        imagenes.add("img/logoGryffindor.jpg");
        imagenes.add("img/logoRavenclaw.jpg");
        imagenes.add("img/logoSlytherin.jpg");
        imagenes.add("img/logoHufflepuff.jpg");
        imagenes.add("img/logoGryffindor.jpg");
        imagenes.add("img/logoRavenclaw.jpg");
        imagenes.add("img/logoSlytherin.jpg");
        juego();
        this.setVisible(true);
    }

    public void juego() {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(238, 228, 132), 8));
        panel.setBackground(new Color(0, 0, 0));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panelNorte = new JPanel();
        panelNorte.setBackground(new Color(0, 0, 0));
        panel.add(panelNorte, BorderLayout.NORTH);

        lblTiempo = new JLabel(" 00:00:00:00");
        lblTiempo.setFont(new Font("Century", Font.BOLD, 20));
        lblTiempo.setForeground(new Color(255, 255, 255));
        panelNorte.add(lblTiempo);

        JPanel panelCentro = new JPanel();
        panelCentro.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelCentro.setBackground(new Color(0, 0, 0));
        panel.add(panelCentro, BorderLayout.CENTER);
        panelCentro.setLayout(new GridLayout(2, 4, 10, 10));
        
        btnMemorama = new JButton[8];
        Collections.shuffle(imagenes);
        
        for (int i = 0; i < btnMemorama.length; i++) {
            btnMemorama[i] = new JButton();
            btnMemorama[i].setFocusable(false);
            btnMemorama[i].setBackground(new Color(145, 140, 106));
            int index = i; 
            btnMemorama[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	tiempo.start();
                	if (!presionar) {
                        return;
                    }
                    JButton btn = (JButton) e.getSource();
                    if(btn.getIcon()!=null)
                    {
                    	return;
                    }
                    if (primerIndice == -1) {
                        primerIndice = index;
                        primerBoton = btn;
                        if (btn.getIcon() == null) {
                        btn.setIcon(cargarImagen(imagenes.get(index)));
                        }
                        
                    } else {
                    	
                        segundoIndice = index;
                        if (btn.getIcon() == null) {
                        btn.setIcon(cargarImagen(imagenes.get(index)));
                        
                        if (imagenes.get(primerIndice).equals(imagenes.get(segundoIndice))) {
                            paresEncontrados++;
                            
                            if (paresEncontrados == 4) {
                                tiempo.stop();
                                JOptionPane.showMessageDialog(null, "¡Felicidades! Has encontrado todos los pares.Tiempo:" + lblTiempo.getText());
                                reiniciarJuego();
                                
                            }
                        } else {
                        	presionar = false; 
                            Timer timer = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    primerBoton.setIcon(null);
                                    btn.setIcon(null);
                                    presionar = true; 
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                        primerIndice = -1; // Restablecer los índices
                        segundoIndice = -1;
                    }}
                }
            });
            panelCentro.add(btnMemorama[i]);
        }

        JPanel panelSur = new JPanel();
        panelSur.setBorder(new EmptyBorder(5, 0, 5, 0));
        panelSur.setBackground(new Color(0, 0, 0));
        panel.add(panelSur, BorderLayout.SOUTH);

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBackground(new Color(145, 140, 106));
        btnReiniciar.setFont(new Font("Century", Font.PLAIN, 14));
        btnReiniciar.setFocusable(false);
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
                tiempo.stop();
            }
        });
        panelSur.add(btnReiniciar);

        cronometro();
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

    public void cronometro() {
        tiempo = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();
            }
        });
    }
    public void reiniciarJuego() {
    	h = 0;
        m = 0;
        s = 0;
        ms = 0;
        String tiempoNuevo = String.format("  %02d:%02d:%02d:%02d", h, m, s, ms);
        lblTiempo.setText(tiempoNuevo);
        
        Collections.shuffle(imagenes);
        primerIndice = -1;
        segundoIndice = -1;
        paresEncontrados = 0;
        
        for (JButton btn : btnMemorama) {
            btn.setIcon(null);
        }
        presionar=true;
    }
    
    private ImageIcon cargarImagen(String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage();
        Image imagenRedim = img.getScaledInstance(120, 210, Image.SCALE_SMOOTH); //redimenzionar imagen
        return new ImageIcon(imagenRedim);
    }
   
}