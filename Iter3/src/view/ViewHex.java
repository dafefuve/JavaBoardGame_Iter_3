package view;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public abstract class ViewHex
{
    public static int ID = 0;
    public int hexID;
    public Polygon hex;
    public int centerX;
    public int centerY;
    public BufferedImage image;
    public abstract BufferedImage getImage();
    public boolean selected;

    public void createPolygon(int centerX, int centerY)
    {
        //Generate the hexagon
        //Radius of hexagon assumed to be 30
        for(int k = 0; k<6; k++)
        {
            int x = (int) (centerX + 30*Math.cos(2*k*Math.PI/6));
            int y = (int) (centerY + 30*Math.sin(2*k*Math.PI/6));
            hex.addPoint(x, y);
        }

        this.hexID = ID;
        selected = false;
        ID++;
    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }
}
