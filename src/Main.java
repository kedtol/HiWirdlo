import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class  Main
{
	public static JFrame mf;
	public static GamePanel gamePanel;
	public static Menu m;

	public static void main(String[] agrs)
	{
		int ablakSize = 650;
		System.out.println("HALLOSENTABITTESEHR");
		mf = new JFrame("SWINGPROJECT");
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setTitle("Hinta palinta");
		gamePanel = new GamePanel(ablakSize);
		m = new Menu(ablakSize);

		mf.add(m,BorderLayout.CENTER);
		mf.pack();
		mf.setSize(new Dimension(ablakSize*2-160,ablakSize));
		mf.setVisible(true);

		try {
			m.Loop();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			gamePanel.Loop();

		} catch (InterruptedException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
