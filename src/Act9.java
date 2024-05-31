import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.JFrame;

public class Act9 extends JFrame {
	
	public Act9() 
	{
		// TODO Auto-generated constructor stub
		this.setTitle("Paint"); 
		this.setSize(600,600); 
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.iniciarComponentes();
        this.getContentPane().setBackground(new Color(245, 255, 192));
        this.setVisible(true); 
	}
	
	public void iniciarComponentes()
	{
		this.repaint(); // repintar componentes.
		
	}
	
	@Override
    public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

        int numEstrellas = 25;
        int radio = 150; //radio de circulo
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;
        Random random = new Random();

        for (int i = 0; i < numEstrellas; i++) {
            double angulo = 2 * Math.PI * i  / numEstrellas;
            int x = (int) (centroX + radio * Math.cos(angulo)); //Coordenadas de la estrella
            int y = (int) (centroY + radio * Math.sin(angulo));
            //Color aleatorio
            Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g.setColor(randomColor);
            //dinujar estrells
            int[] xPoints = new int[10];
            int[] yPoints = new int[10];
            int size=80;
            for (int j = 0; j < 10; j++) {
                double angul = 2 * Math.PI * j / 10;
                int length;
                if (j % 2 == 0) {
                    length = size;
                } else {
                    length = size / 2;
                }
                xPoints[j] = x + (int) (length * Math.cos(angul));
                yPoints[j] = y + (int) (length * Math.sin(angul));
            }

            Polygon estrella = new Polygon(xPoints, yPoints, 10);
            g.fillPolygon(estrella);
        }
        
    }

}
