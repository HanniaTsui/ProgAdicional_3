import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Act14 extends JFrame implements MouseListener, KeyListener  {
	
	JPanel panel = new JPanel();
	public Act14() {
		// TODO Auto-generated constructor stub
		
		this.setTitle("ACTIVIDAD 14");
		this.setSize(900,740); //Login y Registro
		this.setLocationRelativeTo(null);
		this.setMaximumSize(new Dimension (800,800));
		this.setMinimumSize(new Dimension (250,250));
		//this.setResizable(false);
		this.setLayout(null); //Quitar el molde
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.iniciarComponentes();
        this.setVisible(true); 
        addMouseListener(this);
        addKeyListener(this);
	}
	
	public void iniciarComponentes()
	{
		this.botonesV2();
	}
	
	public void botonesV2() // Boton con eventos - Eliminar boton - 14/03/24
	{
		this.setSize(500,750);
		panel.setSize(this.getWidth(), this.getHeight());
		panel.setLocation(0,0);
		panel.setBackground(new Color(123, 203, 204));
		panel.setLayout(null);
		
		JButton superBoton = new JButton("Haz click");
		superBoton.setBackground(new Color (20, 72, 73));
		superBoton.setFont(new Font("Consolas", Font.BOLD, 40));
		superBoton.setForeground(Color.WHITE);
		superBoton.setFocusable(false);
		superBoton.setBounds(50, 530, 400, 70);
		panel.add(superBoton);
        
        superBoton.addActionListener(new ActionListener()      {
			@Override
			public void actionPerformed(ActionEvent e) 	{
				// TODO Auto-generated method stub
				int x=(int)Math.floor(Math.random()*450+1);
				int y=(int)Math.floor(Math.random()*650+1);
				
				int w=(int)Math.floor(Math.random()*120+1);
				int h=(int)Math.floor(Math.random()*120+1);
				
				int r=(int)Math.floor(Math.random()*255+1); //RGB
				int g=(int)Math.floor(Math.random()*255+1);
				int b=(int)Math.floor(Math.random()*255+1);
				
				JButton otroBoton = new JButton(r+","+g+","+b);
				otroBoton.setBounds(x,y,w,h);
				otroBoton.setOpaque(true);
				otroBoton.setBackground(new Color (r,g,b));
				otroBoton.setFocusable(false);
				otroBoton.setBorderPainted(true);
				otroBoton.setBorder(BorderFactory.createLineBorder(new Color(r,g,b),5));
				panel.add(otroBoton);
				
				getContentPane().repaint();
				getContentPane().revalidate();
				
				otroBoton.addActionListener(new ActionListener()	{
					@Override
					public void actionPerformed(ActionEvent e) { //Eliminar botones (Aquellos agregado con el boton hacer clic)
						JButton yo = ((JButton) e.getSource());
						panel.remove(yo);
						getContentPane().repaint();
						getContentPane().revalidate();
					}
			 
				});
			}
        });
		
		add(panel);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub 
		int w = (int) Math.floor(Math.random() * 100 + 30);
	    int h = (int) Math.floor(Math.random() * 100 + 10); 

		saySomething(" " + e.getClickCount(),e, w,h);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		int r=(int)Math.floor(Math.random()*255+1); //RGB
		int g=(int)Math.floor(Math.random()*255+1);
		int b=(int)Math.floor(Math.random()*255+1);
		panel.setBackground(new Color(r,g,b)); //Cambiar el color del panel al entrar a la ventana
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void saySomething(String eventDescription, MouseEvent e, int w, int h) {
		int r=(int)Math.floor(Math.random()*255+1); //RGB
		int g=(int)Math.floor(Math.random()*255+1);
		int b=(int)Math.floor(Math.random()*255+1);
		JButton otroBoton = new JButton( r+","+g+","+b);
		otroBoton.setOpaque(true);
		otroBoton.setBackground(new Color (r,g,b));
		otroBoton.setFocusable(false);
		otroBoton.setBounds(e.getX()-50, e.getY()-50, w, h); 
		panel.add(otroBoton);
	    getContentPane().repaint();
	    getContentPane().revalidate();
	    
	    otroBoton.addActionListener(new ActionListener()	 {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, r+","+g+","+b,
			             "Colores", JOptionPane.WARNING_MESSAGE);
			}
	 
		 });
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub88
		System.out.println(e.getKeyCode()+" "+e.getKeyChar()); //Escribir en codigo y el valor tecleado
		if (e.getKeyCode() == 8) {
			new Thread(new Runnable() {
                @Override
                public void run() {
                    Component[] elementos = panel.getComponents();
                    for (int i = 0; i < elementos.length; i++) {
                        if (elementos[i].getClass().toString().equals("class javax.swing.JButton")) {
                            JButton boton = (JButton) elementos[i];
                            try {
                                for (int size = boton.getWidth(); size > 0; size -= 10) {
                                    boton.setSize(size, size);
                                    getContentPane().repaint();
                                    getContentPane().revalidate();
                                    Thread.sleep(200); // Ajustar el tiempo para la animación
                                }
                                panel.remove(boton);
                                getContentPane().repaint();
                                getContentPane().revalidate();
                                Thread.sleep(200); // Esperar antes de proceder al siguiente botón
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }).start();
        }
		else if (e.getKeyCode() == 84) { // t - Cambiar tamaño
			Component[] elementos = panel.getComponents();
	        for (int i = 0; i < elementos.length; i++)    {
	        	if (elementos[i].getClass().toString().equals("class javax.swing.JButton")) {
	        		JButton boton = (JButton) elementos[i];
	                boton.setSize(boton.getWidth() + 10, boton.getHeight() + 10);
	                
	                getContentPane().repaint();
	                getContentPane().revalidate();
	            }
	        }
	    }
		
	    int movX = 0;
	    int movY = 0;
	    switch (e.getKeyCode()) {
	        case 37: 
	        case 65:	//a - Izquierda
	        	movX = -10;
	            break;
	        case 39:
	        case 68:	// d - derecha
	        	movX = 10;
	            break;
	        case 38:
	        case 87: // w - arriba
	        	movY = -10;
	            break;
	        case 40:
	        case 83:	// s - abajo
	        	movY = 10;
	            break;
	        default:
	            return;
	    }

	   Component[] elementos = panel.getComponents();
        for (int i = 0; i < elementos.length; i++)    {
        	if (elementos[i].getClass().toString().equals("class javax.swing.JButton")) {
        		JButton boton = (JButton) elementos[i];
        		int x2 = boton.getX() + movX;
	            int y2 = boton.getY() + movY;
	            //Para que los botones no salgan del panel
	            if (x2 < 0) {
	            	x2 = 0;
	            } else if (x2 > panel.getWidth() - boton.getWidth()) {
	            	x2 = panel.getWidth() - boton.getWidth();
	            }
	            
	            if (y2 < 0) {
	                y2 = 0;
	            } else if (y2 > panel.getHeight() - boton.getHeight()) {
	                y2 = panel.getHeight() - boton.getHeight();
	            }
	            boton.setLocation(x2, y2);
	            getContentPane().repaint();
                getContentPane().revalidate();
            }
        }
        
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
