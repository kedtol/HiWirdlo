import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class Texture
{
	private int spriteAmount = 10;
	private int dataTagAmount = 10;
	private boolean hasDataTag = false;
	public BufferedImage[][] SpriteSheet = new BufferedImage[spriteAmount][dataTagAmount];

	public Texture()
	{

		for(int i = 0; i < spriteAmount;i++)
		{
			try
			{

				File f = new File("spr/SpriteSheet"+i+".png");
				if(f.exists() && !f.isDirectory())
				{
					// do something
					SpriteSheet[i][0] = ImageIO.read(getClass().getResource("SpriteSheet"+i+".png"));

				}
				else
				{
					System.out.println("Image missing: "+String.valueOf(i));
					SpriteSheet[i][0] = null;
				}

				//SpriteSheet[i] = ImageIO.read(getClass().getResource("/spr/SpriteSheet"+String.valueOf(i)+".png"));
				//SpriteSheet[i][0] = ImageIO.read(getClass().getResource("SpriteSheet"+String.valueOf(i)+".png"));
			}
			catch (IOException e)
			{
				//Image error.
				SpriteSheet[i][0] = null;
			}

			if (SpriteSheet[i][0] == null)
			{
				try
				{
					hasDataTag = true;
					File f = new File("spr/SpriteSheet"+i+"_0.png");
					if(f.exists() && !f.isDirectory())
					{
					//SpriteSheet[i][0] = ImageIO.read(getClass().getResource("SpriteSheet"+i+"_0.png"));
					System.out.println("DataTag found: "+i);
					SpriteSheet[i][0] = ImageIO.read(getClass().getResource("SpriteSheet"+i+"_0.png"));
					}
					else
					{
						System.out.println("No dataTag: "+i);
						SpriteSheet[i][0] = null;
						hasDataTag = false;
					}
				}
				catch (IOException e)
				{
					SpriteSheet[i][0] = null;
					hasDataTag = false;
				}
			}

			if (SpriteSheet[i][0] != null && hasDataTag == true)
			{
				for(int i1 = 0; i1 < dataTagAmount;i1++)
				{
					try
					{
						File f = new File("spr/SpriteSheet"+i+"_"+i1+".png");
						if(f.exists() && !f.isDirectory())
						{
							//SpriteSheet[i][i1] = ImageIO.read(getClass().getResource("SpriteSheet"+i+"_"+i1+".png"));
							System.out.println("DataTag confirmed: "+i1+" at "+i);
							SpriteSheet[i][i1] = ImageIO.read(getClass().getResource("SpriteSheet"+i+"_"+i1+".png"));
							hasDataTag = true;
						}
						else
						{
							System.out.println("No dataTag Texture: "+i1+" at "+i);
							SpriteSheet[i][i1] = null;
							hasDataTag = false;
						}
					}
					catch (IOException e)
					{
						//empty
						SpriteSheet[i][i1] = null;
						hasDataTag = false;
					}
				}
			}

		}

	}

}