package view;

import java.awt.Polygon;

//TODO DELETE THIS CLASS
public class BoardHex
{
	private static int ID = 0;
	private int spaceID;
	private int level;
	private Polygon hex;
	private boolean selected;
    private boolean isLowlands;
    private boolean isMountain;
    private boolean isIrrigation;
    private int x;
    private int y;

	public BoardHex(int centerX, int centerY)
	{
		spaceID = ID;
		level = 0;
		hex = new Polygon();
		selected = false;
        isLowlands = false;
        isMountain = false;
        isIrrigation = false;
        x = centerX;
        y = centerY;
		ID++;

		//Generate the hexagon
		//Radius of hexagon assumed to be 30
		for(int k = 0; k<6; k++)
		{
			int x = (int) (centerX + 30*Math.cos(2*k*Math.PI/6));
			int y = (int) (centerY + 30*Math.sin(2*k*Math.PI/6));
			hex.addPoint(x, y);
		}
	}

	public int getLevel()
	{
		return level;
	}

	public int getSpaceID()
	{
		return spaceID;
	}

	public BoardHex getBoardHex()
	{
		return this;
	}

	public Polygon getPolygon()
	{
		return hex;
	}

	public void setSelected(boolean isSelected)
	{
		selected = isSelected;
	}

	public boolean getSelected()
	{
		return selected;
	}

    public void setMountain()
    {
        isMountain = true;
    }

    public void resetMountain()
    {
        isMountain = false;
    }

    public void resetLowlands()
    {
        isLowlands = false;
    }

    public void setLowlands()
    {
        isLowlands = true;
    }

    public void setIrrigation()
    {
        isIrrigation = false;
    }

    public boolean getIsMountain()
    {
        return isMountain;
    }

    public boolean getIsLowlands()
    {
        return isLowlands;
    }

    public int getCenterX()
    {
        return x;
    }

    public int getCenterY()
    {
        return y;
    }

    public void levelUp()
    {
        level++;
    }


}
