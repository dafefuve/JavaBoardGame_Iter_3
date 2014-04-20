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
    public BufferedImage image;
    public abstract BufferedImage getImage();
    public boolean selected;
}
