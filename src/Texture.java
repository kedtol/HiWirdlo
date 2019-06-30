import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class Texture
	{
		private int spriteAmount = 10;
		private int dataTagAmount = 10;
		private boolean hasDataTag = false;
		public BufferedImage[] SpriteSheet = new BufferedImage[spriteAmount];//[dataTagAmount];
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

				if (SpriteSheet[i] == null)
				{
					/*try
					{
						hasDataTag = true;
						//SpriteSheet[i][0] = ImageIO.read(getClass().getResource("SpriteSheet"+i+"_0.png"));

					}
					catch (IOException e)
					{
						System.out.println("Image missing: "+String.valueOf(i));
						SpriteSheet[i][0] = null;
						hasDataTag = false;
					}*/
				}

				/*if (SpriteSheet[i][0] != null && hasDataTag == true)
				{
					for(int i1 = 0; i1 < dataTagAmount;i1++)
					{
						try
						{
							hasDataTag = true;
							SpriteSheet[i][i1] = ImageIO.read(getClass().getResource("SpriteSheet"+i+"_"+i1+".png"));

						}
						catch (IOException e)
						{
							//empty
							System.out.println("No dataTag: "+i1+"for "+i);
							SpriteSheet[i][0] = null;
							hasDataTag = false;
						}
					}
				}*/


			}
			
		}
		
	}