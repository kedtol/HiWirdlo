import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel
{
	private int ablakSize;
	
	private int mouseX, mouseY;
	//private JTextField jt = new JTextField("RANDOMSZARNEMEMEMEEMEMGY");
	private JButton jb = new JButton("HITME");
	private boolean draw_text1 = true;
	private int box_x = 450;
	private int box_y = 450;
	
	public Menu(int _ablakSize)
	{
		ablakSize = _ablakSize;
		
		//this.add(jt);
		this.add(jb);
		jb.addActionListener(new myActionListener());
		this.addMouseMotionListener(new myMouseMotionListener());
		this.addMouseListener(new myMouseListener());
		jb.addMouseListener(new my1MouseListener());
		jb.addKeyListener(new myKeyListener());
		
		//this.addKeyListener(new myKeyListener());
	}
	
	public void Loop() throws InterruptedException
	{
		while(true)
		{
			repaint();
			
			Thread.sleep(16);
			
		}
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ablakSize, ablakSize);
		g.drawString("APADAT", 100, 500);
		//System.out.println("PAINT "+System.currentTimeMillis());
		
		g.setColor(Color.CYAN);
		String mPos = "Pos: " + mouseX + " " + mouseY;
		g.drawString(mPos, mouseX, mouseY);
		
		if (draw_text1 == true)
		{
			g.drawRect(box_x, box_y, 40, 40);
		}
	}
	
	
	class myMouseMotionListener implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent e) 
		{
			// TODO Auto-generated method stub
	
		}

		@Override
		public void mouseMoved(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			mouseX = e.getX();
			mouseY = e.getY();
		}
		
	}
	
	class myMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
			//	jt.setText("BALLENYOMVA");
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		//	jt.setText("RELEASED");
		}
		
	}
	
	class myKeyListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
			System.out.println("pressed: " + arg0.getKeyCode());
			switch(arg0.getKeyCode())
			{
				case KeyEvent.VK_UP:
					
					box_y -= 5;	
					
				break;
				
				case KeyEvent.VK_DOWN:
									
					box_y +=  5;
									
				break;
								
				case KeyEvent.VK_LEFT:
					
					box_x -= 5;
					
				break;
				
				case KeyEvent.VK_RIGHT:
					
					box_x += 5;	
				break;
			}	
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("released: " + arg0.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
			System.out.println("typed: " + arg0.getKeyCode());
			
		}
		
	}
	
	class myActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			int x_calc_rand = (int) ((ablakSize-jb.getWidth())*Math.random());
			while (x_calc_rand <= 0)
			{
			x_calc_rand = (int) ((ablakSize-jb.getWidth())*Math.random());
			}
			
			int y_calc_rand = (int) ((ablakSize-jb.getHeight())*Math.random());
			while (y_calc_rand <= 0)
			{
			y_calc_rand = (int) ((ablakSize-jb.getHeight())*Math.random());
			}
			
			jb.setBounds(x_calc_rand, y_calc_rand, jb.getWidth(), jb.getHeight());
		}
		
	}
	
	class my1MouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int x_calc_rand = (int) ((ablakSize-jb.getWidth())*Math.random());
			while (x_calc_rand <= 0)
			{
			x_calc_rand = (int) ((ablakSize-jb.getWidth())*Math.random());
			}
			
			int y_calc_rand = (int) ((ablakSize-jb.getHeight())*Math.random());
			while (y_calc_rand <= 0)
			{
			y_calc_rand = (int) ((ablakSize-jb.getHeight())*Math.random());
			}
			
			jb.setBounds(x_calc_rand, y_calc_rand, jb.getWidth(), jb.getHeight());
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
			//	jt.setText("BALLENYOMVA");
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//jt.setText("RELEASED");
		}
		
	}
	
}
