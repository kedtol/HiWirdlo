import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture
	{
		private int spriteAmount = 10;
		public BufferedImage[] SpriteSheet = new BufferedImage[spriteAmount];
		
		public Texture()
		{
			
			for(int i = 0; i < spriteAmount;i++)
			{
				try
			    {               
					SpriteSheet[i] = ImageIO.read(getClass().getResource("SpriteSheet"+String.valueOf(i)+".png"));

			    }
			    catch (IOException e)
			    {
			    	//No image.
			    	System.out.println("Image missing: "+String.valueOf(i));
			    	SpriteSheet[i] = null;
			    } 
			}
			
		}
		
	}