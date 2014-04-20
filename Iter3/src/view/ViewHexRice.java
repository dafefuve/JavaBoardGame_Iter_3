package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewHexRice extends ViewHex
{
    public ViewHexRice(int x, int y)
    {
        try
        {
            image = ImageIO.read(new File("Iter3/src/view/images/rice.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        createPolygon(x, y);
    }


    @Override
    public BufferedImage getImage()
    {
        return image;
    }
}
