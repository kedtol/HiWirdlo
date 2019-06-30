import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class  Main
{
	public static void main(String[] agrs)
	{
		int ablakSize = 960;
		System.out.println("HALLOSENTABITTESEHR");
		JFrame mf = new JFrame("SWINGPROJECT");
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setTitle("Hinta palinta");
		GamePanel m = new GamePanel(ablakSize);
		
		mf.add(m,BorderLayout.CENTER);
		
		mf.pack();
		mf.setSize(new Dimension(ablakSize*2,ablakSize));
		mf.setVisible(true);
		
		
		try {
			m.Loop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
