package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewHexCentralJava extends ViewHex
{
    public ViewHexCentralJava(int x, int y)
    {
        try
        {
            image = ImageIO.read(new File("Iter3/src/view/images/mainland.jpg"));
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
