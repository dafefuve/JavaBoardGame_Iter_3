package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewHexIrrigation extends ViewHex
{
    Rectangle rectangle = new Rectangle(300, 300, 300, 300);
    public ViewHexIrrigation()
    {
        try
        {
            image = ImageIO.read(new File("Iter3/src/view/images/water.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        texture = new TexturePaint(image, rectangle);

        selected = false;
        hexID = ID;
        ID++;
    }

    @Override
    public TexturePaint getTP()
    {
        return texture;
    }

    @Override
    public BufferedImage getImage()
    {
        return image;
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
