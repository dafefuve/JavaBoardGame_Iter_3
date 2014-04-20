package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewHexCentralJava extends ViewHex
{
    public ViewHexCentralJava()
    {
        try
        {
            image = ImageIO.read(new File("Iter3/src/view/images/mainland.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        selected = false;

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
