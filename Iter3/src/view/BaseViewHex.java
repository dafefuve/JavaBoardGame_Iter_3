package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class BaseViewHex extends ViewHex
{
    public BaseViewHex(int x, int y)
    {
        image = null;
        createPolygon(x, y);
    }

    @Override
    public BufferedImage getImage()
    {
        return image;
    }
}