import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Act8 extends JFrame {

	public Act8() 
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
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		g.setColor(Color.white);
		
		
		g.setColor(Color.black);
		g.fillOval((getWidth() - 200) / 2,  (getHeight() - 200) / 2, 200, 200); // Cabeza
		g.fillOval(160,160,95,95); // Oreja Izq
		g.fillOval(340,160,95,95); // Oreja Der
		
		// Cara
		g.setColor(new Color(255,219,172));
		g.fillOval((getWidth() - 150) / 2,300,150,90); // CARA
		
        
		
		g.fillOval(240,220,65,100);
		g.fillOval(290,220,65,100);
		g.fillOval(220,310,40,20);
		g.fillOval(335,310,40,20);
		
		g.fillArc(220,312,22,40,100,200);
		g.fillArc(352,312,25,45,-50,100);
		
		
		
		g.setColor(Color.white);
		g.fillOval(260,260,15,35);
		g.fillOval(320,260,15,35);
		
	//	g.fillArc(200,  280,  130,  100, 180,  200);
		
		
		g.setColor(Color.black);
		g.fillOval((getWidth() - 40) / 2,  (getHeight()) / 2,40,20);
		g.fillOval(261, 275, 12, 20);
		g.fillOval(321, 275, 12, 20);
		
		g.setColor(Color.white); 
		//Detalles ojos 
		g.fillOval(270,273,5,10);
		g.fillOval(330,273,5,10);
		
		((Graphics2D) g).setStroke(new BasicStroke(5));
        
		g.setColor(Color.BLACK); //Sonrisa
	    g.drawArc((getWidth() - 110) / 2, 300, 110, 60, 180, 180);
	    g.fillArc((getWidth() - 50) / 2, 335, 50, 45, 180, 180);
	    
	    ((Graphics2D) g).setStroke(new BasicStroke(2));
	    g.drawArc(234, 328, 23, 15, -180, -180);
	    g.drawArc(342, 328, 23, 15, -180, -180);
	    
	    
	    g.setColor(new Color(235, 180, 204)); //Lengua
	    g.fillArc((getWidth() - 30) / 2, 363, 15, 15, 0, 360);
	    g.fillArc(300, 363, 14, 15, 0, 360);
	    g.fillArc((getWidth() - 17) / 2, 370, 17, 10, 0, 360);
		
	    ((Graphics2D) g).setStroke(new BasicStroke(2));
	    g.setColor(Color.black);
	    g.drawArc(280, 364, 38, 15, 0, -180); //Delineado boca inferior 
		
		
	}
	

}
