package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewHexLowland extends ViewHex
{
    public ViewHexLowland()
    {
        try
        {
            image = ImageIO.read(new File("Iter3/src/view/images/land.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        texture = new TexturePaint(image, new Rectangle(300, 300, 300, 300));
        selected = false;
        hexID = ID;
        ID++;

    }

    @Override
    public BufferedImage getImage()
    {
        return image;
    }

    @Override
    public TexturePaint getTP()
    {
        return texture;
    }

    public boolean getSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
