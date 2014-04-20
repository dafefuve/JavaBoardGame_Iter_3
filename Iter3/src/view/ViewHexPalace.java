package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewHexPalace extends ViewHex
{
    public ViewHexPalace()
    {
        try
        {
            image = ImageIO.read(new File("Iter3/src/view/images/palace.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public BufferedImage getImage()
    {
        return image;
    }
}
