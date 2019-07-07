
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel
{
	private int ablakSize;
	
	private int mouseX, mouseY;
	private JTextField jt = new JTextField("vince egy szar");
	private JButton jb = new JButton("New RANDOM");
	private JButton jb1 = new JButton("New FIX");
	private JCheckBox jcb = new JCheckBox("server");
	private boolean draw_text1 = true;
	private Game game = new Game();
	private Game game1 = new Game();
	static JLabel bind = new JLabel();
	static JLabel bind1 = new JLabel();
	
	public GamePanel(int _ablakSize)
	{
		ablakSize = _ablakSize;
		this.add(jt);
		this.add(jb);
		this.add(jb1);
		this.add(jcb);
		jb.addActionListener(new myActionListener());
		jb1.addActionListener(new myActionListener1());
		jt.addKeyListener(new myKeyListener());
		jcb.addActionListener(new jcbActionListener());
		bindSetup();
		this.add(bind);
		this.add(bind1);
	}
	
	public void bindSetup()
	{
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "up");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "down");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "left");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "right");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "forward");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"), "backward");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "camup");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "camdown");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "plant");
		
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,true), "up_r");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,true), "down_r");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,true), "left_r");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,true), "right_r");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E,0,true), "forward_r");
		bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q,0,true), "backward_r");
		
		bind.getActionMap().put("up", new keyAction(0,false,0));
		bind.getActionMap().put("down", new keyAction(1,false,0));
		bind.getActionMap().put("left", new keyAction(2,false,0));
		bind.getActionMap().put("right", new keyAction(3,false,0));
		bind.getActionMap().put("forward", new keyAction(4,false,0));
		bind.getActionMap().put("backward", new keyAction(5,false,0));
		bind.getActionMap().put("camup", new keyAction(6,false,0));
		bind.getActionMap().put("camdown", new keyAction(7,false,0));
		bind.getActionMap().put("plant", new keyAction(8,false,0));
		bind.getActionMap().put("up_r", new keyAction(0,true,0));
		bind.getActionMap().put("down_r", new keyAction(1,true,0));
		bind.getActionMap().put("left_r", new keyAction(2,true,0));
		bind.getActionMap().put("right_r", new keyAction(3,true,0));
		bind.getActionMap().put("forward_r", new keyAction(4,true,0));
		bind.getActionMap().put("backward_r", new keyAction(5,true,0));
		
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("I"), "up");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("K"), "down");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("J"), "left");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("L"), "right");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("O"), "forward");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("U"), "backward");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "camup");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "camdown");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "plant");
		
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_I,0,true), "up_r");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_K,0,true), "down_r");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_J,0,true), "left_r");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L,0,true), "right_r");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_O,0,true), "forward_r");
		bind1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U,0,true), "backward_r");
		
		bind1.getActionMap().put("up", new keyAction(0,false,1));
		bind1.getActionMap().put("down", new keyAction(1,false,1));
		bind1.getActionMap().put("left", new keyAction(2,false,1));
		bind1.getActionMap().put("right", new keyAction(3,false,1));
		bind1.getActionMap().put("forward", new keyAction(4,false,1));
		bind1.getActionMap().put("backward", new keyAction(5,false,1));
		bind1.getActionMap().put("camup", new keyAction(6,false,1));
		bind1.getActionMap().put("camdown", new keyAction(7,false,1));
		bind1.getActionMap().put("plant", new keyAction(8,false,1));
		bind1.getActionMap().put("up_r", new keyAction(0,true,1));
		bind1.getActionMap().put("down_r", new keyAction(1,true,1));
		bind1.getActionMap().put("left_r", new keyAction(2,true,1));
		bind1.getActionMap().put("right_r", new keyAction(3,true,1));
		bind1.getActionMap().put("forward_r", new keyAction(4,true,1));
		bind1.getActionMap().put("backward_r", new keyAction(5,true,1));
	}
	
	private class keyAction extends AbstractAction 
	{

        int key;
        boolean released;
        int player;

        keyAction(int _key, boolean _released, int _player) 
        {

            key = _key;
            released = _released;
            player = _player;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
        	//System.out.println("something is broken :"+released);
        	game.keyInput(key,released,false,player);
        }
    }
	
	public void Loop() throws InterruptedException
	{
		while(true)
		{
			repaint();
			game.tickMove();
			Thread.sleep(16);
			
			
		}
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		
		Graphics2D g2d = (Graphics2D) g.create();
		super.paintComponent(g2d);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, ablakSize*2-160, ablakSize);
		
		//System.out.println("PAINT "+System.currentTimeMillis());
		
		g2d.setColor(Color.RED);
		
		if (draw_text1 == true)
		{
		game.draw(g2d);
		}
		
	}
	
	
	class myKeyListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
			
			//System.out.println("pressed: " + arg0.getKeyCode());
			
			game.keyInput(arg0.getKeyCode(),false,true,0);
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			//System.out.println("released: " + arg0.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			
			//System.out.println("typed: " + arg0.getKeyCode());
			
		}
		
	}
	
	class myActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			game.restart(1);
		}
		
	}
	
	class myActionListener1 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			game.restart(0);
		}
		
	}
	
	class jcbActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if (game.serverState == false)
			{
				game.serverState = true;
			}
			else
			{
				game.serverState = false;
			}
		}
		
	}
}
