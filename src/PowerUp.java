public class PowerUp
{
    private int type;

    int x;
    int y;
    int z;

    public PowerUp(int _type,int _x,int _y,int _z)
    {
        type = _type;
        x = _x;
        y = _y;
        z = _z;
    }



    public void destroy()
    {
        Map.Item item = new Map.Item();

        Map.map[x][y][z] = item;
    }
}
