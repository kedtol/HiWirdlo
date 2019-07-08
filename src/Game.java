import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Game 
{
	public Map map;
	public Texture texture = new Texture();
	public int mode = 1;
	public boolean serverState = false;
	
	public Game()
	{
		map = new Map(15,15,0,2);
		
	}
	
	public void draw(Graphics g)
	{
		map.playerList[0].camera.drawDebug(g);

		map.draw(g,mode,texture);

	}
	
	public void keyInput(int key, boolean released, boolean listener, int _player)
	{
		if (listener == true)
		{
			//map.player.movinglistener(key,true);
			//map.camera.movement(key);
			switch(key)
			{
				case KeyEvent.VK_C:
					if (mode == 0)
					{
						mode = 1;
					}
					else
					{
						mode = 0;
					}
				break;
			}
		}
		else
		{
			if (map.playerList[_player].alive == true)
			{
				map.playerList[_player].moveInput(key, released, true);
			}
		}
	}
	
	public void restart(int generator)
	{
		map.generate(generator);
		Time.tick = 0;
		Time.fullTick = 0;
	}
	
	public void tickMove()
	{
		map.actionLoop();
	}
}
