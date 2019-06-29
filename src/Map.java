

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

//	map class
/*

	contains the map class and item class
	
*/
public class Map 
{
	public static int length = 21;
	public static int length_z = 21;

	public static Item[][][] map;
	public static int maxPlayers;
	public static Player[] playerList;
	
	public static class Item
	{
		public int contain;
		public Bomb bomb;
		public boolean hasBomb;
		public int planted;
		public Item()
		{
			contain = 0;
			planted = 0;
			hasBomb = false;
		}
		
	}

	public static abstract class MapDrawing
	{
		private int item_width = 32;
		public int screen_px = 200;
		public int screen_py = 200;
		
		private void drawItem(Graphics g, int x, int y, boolean top)
		{
			double x_calc_1;
			double y_calc_1;
			int angle = 4;
			
			if (top == true)
			{
				g.setColor(Color.RED);
				x_calc_1 = x+Math.sin(Math.toRadians((360/angle)+45))*item_width;
				y_calc_1 = y+Math.cos(Math.toRadians((360/angle)+45))*item_width;
			}
			else
			{
				g.setColor(Color.GREEN);
				x_calc_1 = x+Math.sin(Math.toRadians((360/angle*2)+45))*item_width;
				y_calc_1 = y+Math.cos(Math.toRadians((360/angle*2)+45))*item_width;
			}
			
			for (int i = 2; i <= angle+1; i++)
			{
				double x_calc = x+Math.sin(Math.toRadians(360/angle*i+45))*item_width;
				double y_calc = y+Math.cos(Math.toRadians(360/angle*i+45))*item_width;
				g.drawLine((int) x_calc,(int) y_calc,(int) x_calc_1,(int) y_calc_1);
				x_calc_1 = x_calc;
				y_calc_1 = y_calc;
			}
		}
		
		private void drawItemFill(Graphics g, int x, int y, boolean fill)
		{
			int item_width_c = (int) Math.sqrt(Math.pow(item_width,2)+Math.pow(item_width,2))+2;

			//g.setColor(Color.RED);
			if (fill == true)
			{
			g.fillRect(x-item_width_c/2,y-item_width_c/2,item_width_c,item_width_c);
			}
			else
			{
			g.drawRect(x-item_width_c/2,y-item_width_c/2,item_width_c,item_width_c);
			}
		}
		
		private void drawItemTexture(Graphics g, int x, int y, int id, Texture texture, float alpha)
		{
			int item_width_c = (int) Math.sqrt(Math.pow(item_width,2)+Math.pow(item_width,2))+2;

			
			
			Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER , alpha );
			
			//AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.CLEAR, alpha);
	        ((Graphics2D) g).setComposite(comp);
	       
			if (id != 0)
			{
				
				 g.drawImage(texture.SpriteSheet[id],x-item_width_c/2,y-item_width_c/2,item_width_c,item_width_c,null);
				 g.drawRect(x-item_width_c/2,y-item_width_c/2,item_width_c,item_width_c);
			}

			
		}
	
		public void drawDebug(Graphics g)
		{
			g.drawString("tick: "+String.valueOf(Time.tick),50, 50);

			for (int i = 0; i < maxPlayers; i++)
			{
				Player iPlayer =  playerList[i];

				g.drawString("Camera"+String.valueOf(i)+"Angle: "+String.valueOf(iPlayer.camera.angle),screen_px-30+(100*i), 120);
				g.drawString("Camera"+String.valueOf(i)+": "+String.valueOf(iPlayer.camera.x)+";"+String.valueOf(iPlayer.camera.y)+";"+String.valueOf(iPlayer.camera.z),screen_px-30+(100*i), 100);
				g.drawString("Player"+String.valueOf(i)+": "+String.valueOf(iPlayer.x)+";"+String.valueOf(iPlayer.y)+";"+String.valueOf(iPlayer.z),screen_px-30+(100*i), 80);
				g.drawString("Health "+String.valueOf(iPlayer.health),screen_px-30+(100*i), 60);
			}
		}
		
		public void drawMapOrder(Graphics g, Texture SpriteSheet, Camera camera, boolean alpha)
		{
			
			int camX = camera.x;
			int camY = camera.y;
			int camX_rs = camera.x_rs;
			int camY_rs = camera.y_rs;
			int[] cell;
			cell = new int[3];
			cell[0] = -1;
			cell[1] = -2;
			cell[2] = camera.z;
			
			
			int[] cellR;
			cellR = new int[3];
			cellR[0] = 0;
			cellR[1] = 0;
			cellR[2] = 0;
			
			switch (camera.angle)
			{
				case 0:
					camX = camera.x;
					camY = camera.y;
					camX_rs = camera.x_rs;
					camY_rs = camera.y_rs;
					cell[0] = -1;
					cell[1] = -2;
					cell[2] = camera.z;


				break;
				
				case 1:
					camX = camera.x;
					camY = camera.z;
					camX_rs = camera.x_rs;
					camY_rs = camera.z_rs;
					cell[0] = -1;
					cell[1] = camera.y;
					cell[2] = -2;
				break;
				
				case 2:
					camX = camera.z;
					camY = camera.y;
					camX_rs = camera.z_rs;
					camY_rs = camera.y_rs;
					cell[0] = camera.x;
					cell[1] = -2;
					cell[2] = -1;
				break;
				
				}
			
			for (int i = camX-camX_rs-1; i < camX+camX_rs; i++)
			{
				for (int i1 = camY-camY_rs-1; i1 < camY+camY_rs; i1++)
				{
					if (alpha == true)
					{
						for (int i2 = -1; i2 <= 1; i2++)
						{
							cellR = cellSet(i, i1, cell,i2);
							drawMap(g, SpriteSheet, cellR[0], cellR[1], cellR[2], i, i1, camX, camY, camX_rs, camY_rs, camera,true,i2);
						}
					}
					else
					{
						cellR = cellSet(i, i1, cell,0);
						drawMap(g, SpriteSheet, cellR[0], cellR[1], cellR[2], i, i1, camX, camY, camX_rs, camY_rs, camera,false,0);
					}
				}
				
			}
		}
		
		private int[] cellSet(int iterator,int iterator1,int[] _cell,int iterator2)
		{
			int[] cell_;
			cell_ = new int[3];
			
			for (int i2 = 0; i2 < 3; i2++)
			{
				if (_cell[i2] == -1)
				{
					cell_[i2] = iterator;
				}
				else
				{
					if (_cell[i2] == -2)
					{
						cell_[i2] = iterator1;
					}
					else
					{
						cell_[i2] = _cell[i2]+iterator2;
					}
				}
			}
			return cell_;
		}
		
		private void drawMap(Graphics g, Texture SpriteSheet, int cellX, int cellY, int cellZ, int cam_i, int cam_i1, int camX, int camY, int camX_rs, int camY_rs, Camera camera,boolean alpha,int cam_i2)
		{
			double item_width_c = Math.sqrt(Math.pow(item_width,2)+Math.pow(item_width,2))+2;
			float padding_camera_x = cam_i-(camX-camX_rs);
			float padding_camera_y = cam_i1-(camY-camY_rs);
			float alphaValue = 1f;

			if (alpha == true)
			{

				int padding_camera_z = 1 * cam_i2;

				if (cam_i2 != 0)
				{
					padding_camera_x = padding_camera_x + padding_camera_z / 1.5f;
					padding_camera_y = padding_camera_y + padding_camera_z / 1.5f;
					alphaValue = 0.5f;
				}
			}

			if (map[cellX][cellY][cellZ].hasBomb == true)
			{
				drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),5,SpriteSheet,alphaValue);
			}

			for (int i = 0; i < maxPlayers; i++)
			{
				Player iPlayer = playerList[i];

				if (cellX == iPlayer.x && cellY == iPlayer.y && cellZ == iPlayer.z) {
					if (iPlayer.alive == true) {
						drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),9, SpriteSheet, alphaValue);
					}
				}
			}
			drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c)+camera.drawPadding,screen_py+(int)(padding_camera_y * item_width_c),map[cellX][cellY][cellZ].contain,SpriteSheet,alphaValue);
			//drawItemTexture(g,screen_px+padding_camera_x*((int) item_width_c)+camera.drawPadding,screen_py+padding_camera_y *((int) item_width_c),map[cellX][cellY][cellZ].contain,SpriteSheet,alphaValue);
		}
		
		public void drawMapAlpha(Graphics g, Texture SpriteSheet, Camera camera)
		{
			for (int i2 = -1; i2 <= 1; i2++)
			{
				for (int i = camera.x-camera.x_rs-1; i < camera.x+camera.x_rs; i++)
				{
					for (int i1 = camera.y-camera.y_rs-1; i1 < camera.y+camera.y_rs; i1++)
					{
						double item_width_c = Math.sqrt(Math.pow(item_width,2)+Math.pow(item_width,2))+2;
						float padding_camera_x = i-(camera.x-camera.x_rs);
						float padding_camera_y = i1-(camera.y-camera.y_rs);
						
						float alpha = 1f;
						int padding_camera_z = 1*i2;
						
						if (i2 != 0)
						{
							padding_camera_x = padding_camera_x+padding_camera_z/1.5f;
							padding_camera_y = padding_camera_y+padding_camera_z/1.5f;
							alpha = 0.5f;
						}
						
						if (map[i][i1][camera.z+i2].hasBomb == true)
						{
							drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c),screen_py+(int)(padding_camera_y * item_width_c),5,SpriteSheet,alpha);
						}
						drawItemTexture(g,screen_px+(int)(padding_camera_x*item_width_c),screen_py+(int)(padding_camera_y * item_width_c),map[i][i1][camera.z+i2].contain,SpriteSheet,alpha);
					}
					
				}
			}
		}
	}

	public Map(int _length,int _length_z,int _generatorId,int _maxPlayers)
	{
		length = _length;
		length_z = _length_z;
		map = new Item[length][length][length_z];
		//player1 = new Player(length-2,length-2,length_z-2);
		maxPlayers = _maxPlayers;
		playerList = new Player[maxPlayers];

		// fill playerList
		for (int i = 0; i < maxPlayers; i++) {
			Player iPlayer;
			if (i == 0)
			{
				iPlayer =  new Player();
			}
			else
			{
				iPlayer =  new Player(length-2,length-2,length_z-2);
			}


			playerList[i] = iPlayer;
		}


		if (_generatorId == 1)
		{
			for (int i = 0; i < length; i++)
			{
				for (int i1 = 0; i1 < length; i1++)
				{
					for (int i2 = 0; i2 < length_z; i2++)
					{
					Item item = new Item();
						int random = (int)(Math.random() * 100 + 1);
						
						if (random > 50)
						{
							if (random > 65)
							{
								item.contain = 2;
							}
							else
							{
								random = (int)(Math.random() * 100 + 1);
								
								if (random > 60)
								{
								item.contain = 8;
								}
								else
								{
								item.contain = 1;
								}
							}
						}
						else
						{
							item.contain = 0;
						}
					map[i][i1][i2] = item;
					System.out.println(map[i][i1][i2].contain);
					}
				}
			}
		}
		else
		{
			for (int i = 0; i < length; i++)
			{
				for (int i1 = 0; i1 < length; i1++)
				{
					for (int i2 = 0; i2 < length_z; i2++)
					{
					Item item = new Item();
					
					int random = (int)(Math.random() * 100 + 1);
					
					item.contain = 2;
					
					if (i % 2 == 0 && i1 % 2 == 0)
					{
						if (random > 60)
						{
						item.contain = 8;
						}
						else
						{
						item.contain = 1;
						}
					}
					
					if (i == 1 && i1 == 1 && i2 == 1)
					{
						item.contain = 0;
					}
					
					if (i == length-2 && i1 == length-2 && i2 == length_z-2)
					{
						item.contain = 0;
					}
					
					if (i1 == 1 || i1 == length-2)
					{
						item.contain = 0;
					}
					
					if (i == 1 || i == length-2)
					{
						item.contain = 0;
					}

					
					if (i == 0 || i == length-1)
					{
						if (random > 60)
						{
						item.contain = 8;
						}
						else
						{
						item.contain = 1;
						}
					}
					
					if (i1 == 0 || i1 == length-1)
					{
						if (random > 60)
						{
						item.contain = 8;
						}
						else
						{
						item.contain = 1;
						}
					}
					
					if (i2 == 0 || i2 == length_z-1)
					{
						if (random > 60)
						{
						item.contain = 8;
						}
						else
						{
						item.contain = 1;
						}
					}
					
					
					
					map[i][i1][i2] = item;
					System.out.println(map[i][i1][i2].contain);
					}
				}
			}
		}
	}

	public static void setItem(int _x, int _y, int _z, Bomb _bomb, int _contain, boolean _hasBomb)
	{
		Item item = new Item();
		item.bomb = _bomb;
		item.contain = _contain;
		item.hasBomb = _hasBomb;

		map[_x][_y][_z] = item;
	}

	private void itemMoveTick()
	{
		for (int i = 0; i < length; i++)
		{
			for (int i1 = 0; i1 < length; i1++)
			{
				for (int i2 = 0; i2 < length_z; i2++)
				{
					if (map[i][i1][i2].hasBomb == true)
					{
						map[i][i1][i2].bomb.explode();
					}
					
					if (map[i][i1][i2].planted > 0)
					{
						if (Time.fullTick-map[i][i1][i2].planted >= 8)
						{
							map[i][i1][i2].contain = 0;
						}
					}
				}
			}
		}
	}

	public void actionLoop()
	{
		// player movement + collision
		for (int i = 0; i < maxPlayers; i++)
		{
			Player iPlayer = playerList[i];

			if (iPlayer.alive == true)
			{
				iPlayer.collision();
				iPlayer.moveForward(true);
			}
		}

		Time.tickMove();
		itemMoveTick();
	}

	public void draw(Graphics g, int _mode, Texture _texture)
	{
		for (int i = 0; i < maxPlayers; i++)
		{
			Player iPlayer = playerList[i];

			if (_mode == 0)
			{
				iPlayer.camera.drawMapOrder(g, _texture,iPlayer.camera,true);
			}
			else
			{
				iPlayer.camera.drawMapOrder(g, _texture,iPlayer.camera,false);
			}

		}
	}
}
