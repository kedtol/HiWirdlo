public class Alarm
{
    public float startTick;
    public float length;

    public Alarm()
    {
        length = 0f;
        startTick = 0f;
    }

    public void start()
    {
        startTick = Time.tick;
    }

    public void setLength(float _length)
    {
        length = _length;
    }

    public float getState()
    {
        return Time.tick-startTick;
    }

    public boolean finished()
    {
        if (startTick+length < Time.tick)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
