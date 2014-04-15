package view;

import java.awt.Polygon;


public class BoardHex 
{
	private static int ID = 0;
	private int spaceID;
	private int level;
	private Polygon hex;
	private boolean selected;
	
	public BoardHex(int centerX, int centerY)
	{
		spaceID = ID;
		level = 0;
		hex = new Polygon();
		selected = false;
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
}
