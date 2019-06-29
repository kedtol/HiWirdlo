public class Camera extends Map.MapDrawing
{
	public int x;
	public int x_rs;
	public int y;
	public int y_rs;
	public int z;
	public int z_rs;
	public int angle;
	public int drawPadding;
	
	public Camera(int _drawPadding)
	{
		x = 8;
		x_rs = 6;
		y = 8;
		y_rs = 6;
		z = 8;
		z_rs = 6;
		angle = 0;
		drawPadding = _drawPadding;
	}
	
	public void follow(Player _player)
	{
		
		int pangleX = 0;
		int pangleY = 0;
		int pangleZ = 0;
		switch (angle)
		{
			case 0:
				
			pangleX = 1;
			pangleY = 1;
			pangleZ = -z_rs;
			
			break;
			
			case 1:
				
			pangleX = 1;
			pangleY = -y_rs;
			pangleZ = 1;
			
			break;
				
			case 2:
				
			pangleX = -x_rs;
			pangleY = 1;
			pangleZ = 1;
			
			break;
		}
		
		if (_player.x < x_rs+pangleX)
		{
		x = x_rs+pangleX;
		}
		else
		{
			if (_player.x > Map.length-(x_rs+pangleX))
			{
			x = Map.length-(x_rs);
			}
			else
			{
				if (pangleX > 0)
				{
				x = _player.x+1;
				}
				else
				{
				x = _player.x;
				}
			}
		}
		
		if (_player.y < y_rs+pangleY)
		{
		y = y_rs+pangleY;
		}
		else
		{
			if (_player.y > Map.length-(y_rs+pangleY))
			{
			y = Map.length-(y_rs);
			}
			else
			{
				if (pangleY > 0)
				{
				y = _player.y+1;
				}
				else
				{
				y = _player.y;
				}
			}
		}
		
		if (_player.z < z_rs+pangleZ)
		{
		z = z_rs+pangleZ;
		}
		else
		{
			if (_player.z > Map.length_z-(z_rs+pangleZ))
			{
			z = Map.length_z-(z_rs);
			}
			else
			{
				if (pangleZ > 0)
				{
				z = _player.z+1;
				}
				else
				{
				z = _player.z;
				}
			}
		}
	
	}
	
	public int calculateRelativeAngle(int _angle)
	{
		int angle_ = 0;
		
		switch (angle)
		{
			case 0:
				
				angle_ = _angle;
				
			break;
			
			case 1:
				
				switch (_angle)
				{
					
					case 2: //w
					angle_ = 5;	
					break;
					
					case 4: //s
					angle_ = 6;	
					break;
					
					case 1: //d
					angle_ = 1;	
					break;
					
					case 3: //a
					angle_ = 3;	
					break;
					
					case 5: //q
					angle_ = 2;	
					break;
					
					case 6: //e
					angle_ = 4;	
					break;
				}
			break;
				
			case 2:
				
				switch (_angle)
				{
					case 1:
					angle_ = 6;	
					break;
					
					case 2:
					angle_ = 2;	
					break;
					
					case 3:
					angle_ = 5;	
					break;
					
					case 4:
					angle_ = 4;	
					break;
					
					case 5:
					angle_ = 3;	
					break;
					
					case 6:
					angle_ = 1;	
					break;
				}
				
			break;
		}
		
		return angle_;
	}
	
}
