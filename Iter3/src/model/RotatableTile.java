package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnav on 4/16/14.
 */
public class RotatableTile extends Tile
{
    TileComponent centralTileComponent;
    ArrayList<TileComponent> rotatingTileComponents;

    public RotatableTile(TileComponent ctc, ArrayList<TileComponent> rtc)
    {
        centralTileComponent = ctc;
        rotatingTileComponents = rtc;
    }

    public void rotateClockwise()
    {

    }

    public void rotateCounterClockwise()
    {

    }
}
