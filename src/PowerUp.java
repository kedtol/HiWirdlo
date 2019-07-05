public class PowerUp
{
   public int type;


    public PowerUp()
    {
        int random = (int)(Math.random() * 100 + 1);

        if (random > 70)
        {
            type = 0;
        }
        else
        {
            if (random > 40)
            {
                type = 1;
            }
            else
            {
                if (random > 10)
                {
                    type = 2;
                }
                else
                {
                    if (random > 5)
                    {
                        type = 3;
                    }
                    else
                    {
                        type = 4;
                    }
                }
            }
        }
    }

}
