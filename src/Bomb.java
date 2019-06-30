public class Bomb
{
	Map.Item[][][] map = Map.map;
	public int x;
	public int y;
	public int z;
	private int created;
	private int explosionTime;
	private int fireTime;
	private int size;
	private int[] explosionIndex = new int[6];
	private boolean explosion;

	private Alarm alarm = new Alarm();
	
	public Bomb()
	{
		x = 0;
		y = 0;
		z = 0;

		explosionIndex[0] = 0;
		explosionIndex[1] = 0;
		explosionIndex[2] = 0;
		explosionIndex[3] = 0;
		explosionIndex[4] = 0;
		explosionIndex[5] = 0;

		explosionTime = 5;
		size = 3;

		fireTime = 3;
		alarm.setLength(5);
		alarm.start();

		explosion = true;

	}
	
	public void explode()
	{
		System.out.println(alarm.getState());

		if (alarm.finished() == true)
		{
			while (explosionIndex[1] > -1 || explosionIndex[4] > -1 || explosionIndex[5] > -1 || explosionIndex[2] > -1 || explosionIndex[3] > -1 || explosionIndex[0] > -1)
			{
				if (explosion == true)
				{
					if (explosionIndex[0] == 0)
					{
						if (collide(0,0,0) == 1)
						{
							setExplosion(0,0);
						}
						else
						{
							System.out.println("systemTick - bomb disarmed");
							map[x][y][z].hasBomb = false;
						}
						explosionIndex[0] = 1;
						explosionIndex[1] = 1;
						explosionIndex[2] = 1;
						explosionIndex[3] = 1;
						explosionIndex[4] = 1;
						explosionIndex[5] = 1;
					}
					else
					{
						for (int i = 0; i < 6; i++)
						{
							
							if (explosionIndex[i] != -1 && explosionIndex[i] <= size)
							{
								if (collide(i+1, explosionIndex[i],0) == 1)
								{
								setExplosion(i+1, explosionIndex[i]);
								}
								else
								{
									if (collide(i+1, explosionIndex[i],2) == 1)
									{
									setExplosion(i+1, explosionIndex[i]);
									}
									explosionIndex[i] = -1;
								}
							}
							else
							{
								explosionIndex[i] = -1;
							}
						}
						for (int i = 0; i < 6; i++)
						{
							if (explosionIndex[i] != -1)
							{
							explosionIndex[i]+= 1;
							}
						}
					}
				}
			}
			if (explosion == true)
			{
				alarm.setLength(3);
				explosion = false;
				alarm.start();

			}
			
			explosion = false;

		}
		
		
		if (explosion == false && alarm.finished() == true)
		{
			explosionIndex[0] = 0;
			explosionIndex[1] = 0;
			explosionIndex[2] = 0;
			explosionIndex[3] = 0;
			explosionIndex[4] = 0;
			explosionIndex[5] = 0;
			while (explosionIndex[1] > -1 || explosionIndex[4] > -1 || explosionIndex[5] > -1 || explosionIndex[2] > -1 || explosionIndex[3] > -1 || explosionIndex[0] > -1)
			{
				if (explosionIndex[0] == 0)
				{
					if (collide(0,0,3) == 1 || collide(0,0,6) == 1 || collide(0,0,3) == 7)
					{
						setClear(0,0);
					}
					else
					{
						setClear(0,0);
						System.out.println("systemTick - bomb disarmed");
						map[x][y][z].hasBomb = false;
					}
					explosionIndex[0] = 1;
					explosionIndex[1] = 1;
					explosionIndex[2] = 1;
					explosionIndex[3] = 1;
					explosionIndex[4] = 1;
					explosionIndex[5] = 1;
				}
				else
				{
					for (int i = 0; i < 6; i++)
					{
						if (explosionIndex[i] != -1 && (collide(i+1, explosionIndex[i],3) == 1 || collide(i+1, explosionIndex[i],6) == 1 || collide(i+1, explosionIndex[i],7) == 1) && explosionIndex[i] <= size)
						{
							setClear(i+1, explosionIndex[i]);
						}
						else
						{
							explosionIndex[i] = -1;
							
						}
					}
					for (int i = 0; i < 6; i++)
					{
						if (explosionIndex[i] != -1)
						{
						explosionIndex[i]+= 1;
						}
					}
				}
			}
			System.out.println("systemTick - bomb disarmed");
			map[x][y][z].hasBomb = false;
		}
	}
	
	private void setExplosion (int direction, int speed)
	{
		switch(direction)
		{
			case 2: //up
				map[x][y-speed][z].id = 3;
				map[x][y-speed][z].dataTag = 0;
				map[x][y-speed][z].planted = created;
			break;
			
			case 4: //down 
				map[x][y+speed][z].id = 3;
				map[x][y+speed][z].dataTag = 0;
				map[x][y+speed][z].planted = created;
			break;
							
			case 3: //left
				map[x-speed][y][z].id = 3;
				map[x-speed][y][z].dataTag = 1;
				map[x-speed][y][z].planted = created;
			break;
			
			case 1: //right
				map[x+speed][y][z].id = 3;
				map[x+speed][y][z].dataTag = 1;
				map[x+speed][y][z].planted = created;
			break;
			
			case 5: //z up
				map[x][y][z-speed].id = 3;
				map[x][y][z-speed].dataTag = 3;
				map[x][y][z-speed].planted = created;
			break;
			
			case 6: //z down 
				map[x][y][z+speed].id = 3;
				map[x][y][z+speed].dataTag = 3;
				map[x][y][z+speed].planted = created;
			break;
			
			case 0: //none
				map[x][y][z].id = 3;
				map[x][y][z].dataTag = 2;
				map[x][y][z].planted = created;
			break;
		}	
	}
	
	private void setClear (int direction, int speed)
	{
		switch(direction)
		{
			case 2: //up
				map[x][y-speed][z].id = 0;
			break;
			
			case 4: //down 
				map[x][y+speed][z].id = 0;
			break;
							
			case 3: //left
				map[x-speed][y][z].id = 0;
			break;
			
			case 1: //right
				map[x+speed][y][z].id = 0;
			break;
			
			case 5: //z up
				map[x][y][z-speed].id = 0;
			break;
			
			case 6: //z down
				map[x][y][z+speed].id = 0;
			break;
			
			case 0: //none
				map[x][y][z].id = 0;
			break;
		}	
	}
	
	private int collide(int direction, int speed, int id)
	{
		switch(direction)
		{
			case 2: //up
				if (y-speed < 0)
				{
					return 2;
				}
				else
				{
					if ( map[x][y-speed][z].id == id)
					{
						return 1;
					}
				}
			break;
			
			case 4: //down 
				if (y+speed > Map.length-1)
				{
					return 2;
				}
				else
				{
					if (map[x][y+speed][z].id == id)
					{
						return 1;
					}
				}
			break;
							
			case 3: //left
				if (x-speed < 0)
				{
					return 2;
				}
				else
				{
					if (map[x-speed][y][z].id == id)
					{
						return 1;
					}
				}
			break;
			
			case 1: //right
				if (x+speed > Map.length-1)
				{
					return 2;
				}
				else
				{
					if (map[x+speed][y][z].id == id)
					{
						return 1;
					}
				}
			break;
			
			case 5: //z up
				if (z-speed < 0)
				{
					return 2;
				}
				else
				{
					if (map[x][y][z-speed].id == id)
					{
						return 1;
					}
				}
			break;
			
			case 6: //z down
				if (z+speed > Map.length_z-1)
				{
					return 2;
				}
				else
				{
					if (map[x][y][z+speed].id == id)
					{
						return 1;
					}
				}
			break;
			
			case 0: //none
				if (map[x][y][z].id == id)
				{
					return 1;
				}
			break;
		}	
		return 0;
	}
}
