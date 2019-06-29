
public class Time
{
	public static float tick = 0;
	private static int tickTime = 0;
	public static int fullTick = 0;
	
	public static void tickMove()
	{
		if (tickTime+16 < 1000)
		{
			tickTime = tickTime+16;
			tick += (float)16/1000;
		}
		else
		{
			// A full second passed
			tickTime -= 1000;
			fullTick += 1;
		}
		
	}
	
	
}
