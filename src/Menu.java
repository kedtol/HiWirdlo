import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JPanel
{
	private int ablakSize;
	public boolean visible = true;;

	private JLabel jl = new JLabel("Bomberman 3d");
	private JButton jb_start = new JButton("Start local match");
	private JButton jb_host = new JButton("Host online match");
	private JTextField jt_ip = new JTextField("127.0.0.1");
	private JLabel jl_ip = new JLabel("Ip:");

	private JButton jb_join = new JButton("Join online match");
	private int v_x = (int)((ablakSize*2-160)*Math.random());
	private int v_y = (int)((ablakSize)*Math.random());
	private int box_x = 450;
	private int box_y = (int)((ablakSize-40)*Math.random());
	private GridBagLayout Jlayout = new GridBagLayout();

	public Menu(int _ablakSize)
	{
		ablakSize = _ablakSize;
		setLayout(Jlayout);
		GridBagConstraints gbc = new GridBagConstraints();
		jt_ip.setPreferredSize(new Dimension(100, 22));
		jt_ip.setMaximumSize(new Dimension(40,40));
		jl_ip.setForeground(Color.white);
		jl.setForeground(Color.white);
		gbc.insets = new Insets(5,5,5,5);

		this.add(Box.createHorizontalStrut(25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(jl,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(jb_start,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(jb_host,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(jb_join,gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(jl_ip,gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.add(jt_ip,gbc);
		this.add(Box.createVerticalStrut(50));
		jb_start.addActionListener(new myActionListener());
		jb_host.addActionListener(new hostActionListener());
		jb_join.addActionListener(new joinActionListener());

		//this.addKeyListener(new myKeyListener());
	}
	
	public void Loop() throws InterruptedException
	{
		while(visible == true)
		{
			repaint();
			if (box_x < ablakSize*2-160)
			{
				box_x += 5;
			}
			else
			{
				box_x = -40;
				box_y = (int)((ablakSize-40)*Math.random());
				v_x = (int)((ablakSize*2-160)*Math.random());
				v_y = (int)((ablakSize)*Math.random());
			}
			Thread.sleep(16);
			
		}
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ablakSize*2-160, ablakSize);

		//System.out.println("PAINT "+System.currentTimeMillis());
		
		g.setColor(Color.CYAN);
		g.drawString("vekni sukk", v_x, v_y);
		g.drawRect(box_x, box_y, 40, 40);
	}
	
	class myActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			visible = false;
			Main.gamePanel.game.start();
			Main.mf.remove(Main.m);
			Main.mf.add(Main.gamePanel,BorderLayout.CENTER);
			Main.mf.pack();
			Main.mf.setSize(new Dimension(ablakSize*2-160,ablakSize));


		}
		
	}

	class hostActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{

			visible = false;
			Main.gamePanel.server = true;
			Main.gamePanel.client = false;
			Main.gamePanel.visible = true;

			Main.mf.remove(Main.m);
			Main.mf.add(Main.gamePanel,BorderLayout.CENTER);
			Main.mf.pack();
			Main.mf.setSize(new Dimension(ablakSize*2-160,ablakSize));

		}

	}

	class joinActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{

			visible = false;
			Main.gamePanel.server = false;
			Main.gamePanel.client = true;
			Main.gamePanel.ip = jt_ip.getText();
			Main.gamePanel.visible = true;

			Main.mf.remove(Main.m);
			Main.mf.add(Main.gamePanel,BorderLayout.CENTER);
			Main.mf.pack();
			Main.mf.setSize(new Dimension(ablakSize*2-160,ablakSize));

		}

	}

}
