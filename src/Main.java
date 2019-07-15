import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class  Main
{
	public static JFrame mf;
	public static GamePanel game;
	public static Menu m;

	public static void main(String[] agrs)
	{
		int ablakSize = 650;
		System.out.println("HALLOSENTABITTESEHR");
		mf = new JFrame("SWINGPROJECT");
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setTitle("Hinta palinta");
		game = new GamePanel(ablakSize);
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
			game.Loop();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
