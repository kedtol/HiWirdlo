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
            random = (int)(Math.random() * 100 + 1);

            if (random > 60)
            {
                type = 4;
            }
            else
            {
                if (random > 30)
                {
                    type = 2;
                }
                else
                {
                    if (random > 15)
                    {
                        type = 3;
                    }
                    else
                    {
                        type = 1;
                    }
                }
            }
        }
    }

}
