package model;

/**
 * Created by Daniel on 4/14/2014.
 */

//todo
public class Developer
{
    int spaceID;
    JavaPlayer jPlayer;

    public Developer(JavaPlayer jp)
    {
        spaceID = 0;
        jPlayer = jp;
    }

    public int getSpaceID()
    {
        return this.spaceID;
    }

    public void setSpaceID(int newSpaceID)
    {
        this.spaceID = newSpaceID;
    }

    public JavaPlayer getJPlayer()
    {
        return this.jPlayer;
    }
}
