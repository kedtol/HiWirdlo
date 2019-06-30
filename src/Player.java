
public class Player
{
	public int x;
	public int y;
	public int z;
	public int direction;
	public int health;
	public int color;

	private boolean damage;
	private boolean move;
	public boolean alive = true;

	public Camera camera;
	private Alarm alarm = new Alarm();
	
	public Player(int _x, int _y,int _z)
	{
		color = 1;
		x = _x;
		y = _y;
		z = _y;
		direction = 0;
		health = 3;

		damage = false;
		move = false;

		camera = new Camera(800);

		alarm.setLength(0.2f);
	}
	
	public Player()
	{
		color = 2;
		x = 1;
		y = 1;
		z = 1;
		direction = 0;
		health = 3;

		damage = false;
		move = false;

		camera = new Camera(0);

		alarm.setLength(0.2f);
	}
	
	private boolean collide(int direction,int speed, int id)
	{
		switch(direction)
		{
			case 0:
				if (Map.map[x][y][z].id == id)
				{
					return true;
				}
			break;
		
			case 2: //left y
				if (y-speed < 0 || Map.map[x][y-speed][z].id == id)
				{
					return true;
				}
			break;
			
			case 4: //right y
				if (y+speed > Map.length-1 || Map.map[x][y+speed][z].id == id)
				{
					return true;
				}
			break;
							
			case 3: //left x
				if (x-speed < 0 || Map.map[x-speed][y][z].id == id)
				{
					return true;
				}
			break;
			
			case 1: //right x
				if (x+speed > Map.length-1 || Map.map[x+speed][y][z].id == id)
				{
					return true;
				}
			break;
			
			case 5: //left z
				if (z-speed < 0 || Map.map[x][y][z-speed].id == id)
				{
					return true;
				}
			break;
			
			case 6: //right z
				if (z+speed > Map.length_z-1 || Map.map[x][y][z+speed].id == id)
				{
					return true;
				}
			break;
		}	
		return false;
	}
	
	public void collision()
	{
		if (collide(0,1,3) == true || collide(0,1,7) == true || collide(0,1,6) == true)
		{
			if (damage == false && health > 0)
			{
				health -= 1;
			}
			
			damage = true;
		}
		else
		{
			damage = false;
		}
		
		if (health == 0)
		{
			alive = false;
		}
	}

	public void moveForward(boolean follow)
	{
		if (move == true)
		{
			if (direction > 0 && collide(direction,1,1) == false && collide(direction,1,8) == false && collide(direction,1,2) == false) //speed kell majd késõbbiekben
			{
				if (alarm.finished() == true)
				{
				moveDirection(direction,follow);
				alarm.start();
				}
			}
			else
			{
				move = false;
			}
		}
	}

	public void moveInput(int key,boolean released,boolean follow)
	{
		//direction = 0;
		if (released == true)
		{
			move = false;
		}
		else
		{
			switch(key)
			{
				case 0:
					direction = camera.calculateRelativeAngle(2);
					move = true;
				break;
				
				case 1:
					direction = camera.calculateRelativeAngle(4);
					move = true;
				break;
								
				case 2:
					direction = camera.calculateRelativeAngle(3);
					move = true;
				break;
				
				case 3:
					direction = camera.calculateRelativeAngle(1);
					move = true;
				break;
				
				case 5:
					direction = camera.calculateRelativeAngle(5);
					move = true;
				break;
				
				case 4:
					direction = camera.calculateRelativeAngle(6);
					move = true;
				break;
				
				case 6:
					if (camera.angle < 2)
					{
						camera.angle += 1;
						
					}
					else
					{
						camera.angle = 0;
					}
					
					if (follow == true)
					{
						camera.follow(this);
					}
					direction = 0;
				break;
				
				case 7:
					if (camera.angle > 0)
					{
						camera.angle -= 1;
					}
					else
					{
						camera.angle = 2;
					}
					
					if (follow == true)
					{
						camera.follow(this);
					}
					direction = 0;
				break;
				
				case 8:
					placebomb();
					direction = 0;
				break;
				
			}	
		}
		
	}

	private void moveDirection(int direction,boolean follow)
	{	
		switch(direction)
		{
			case 2:
				
				if (y-1 > 0)
				{
				y -= 1;	
				}
				else
				{
				y = 0;
				}
				
			break;
			
			case 4:
				
				if (y+1 < Map.length)
				{				
				y += 1;	
				}		
				else
				{
				y = Map.length;
				}
				
			break;
							
			case 3:
				
				if (x-1 > 0)
				{
				x -= 1;	
				}
				else
				{
				x = 0;
				}
				
			break;
			
			case 1:
				
				if (x+1 < Map.length)
				{
				x += 1;	
				}
				else
				{
				x = Map.length;
				}
				
			break;
			
			case 5:
				
				if (z-1 > 0)
				{
				z -= 1;	
				}
				else
				{
				z = 0;
				}
				
			break;
			
			case 6:
				
				if (z+1 < Map.length_z-1)
				{
				z += 1;	
				}
				else
				{
				z = Map.length_z-1;
				}
				
			break;
		}	
		if (follow == true)
		{
			camera.follow(this);
		}
	}

	private void placebomb()
	{
		Bomb bomb = new Bomb();
		bomb.x = x;
		bomb.y = y;
		bomb.z = z;

		Map.setItem(x,y,z,bomb,0,true);
		System.out.println("bomb");


	}
}
