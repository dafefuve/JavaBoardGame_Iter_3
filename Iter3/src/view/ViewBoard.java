package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewBoard
{
    private Polygon[][] boardPolygons;
    private ViewHexStack[][] boardImages;
    private boolean[][] selected;

    public ViewBoard(int rows, int cols)
    {
        boardPolygons = new Polygon[rows][cols];
        boardImages = new ViewHexStack[rows][cols];
        selected = new boolean[rows][cols];
        //Used to set up bottom layer of hex tiles, i.e., just the polygons and their coordinates, NO IMAGES yet
        initializeBaseLayer();
        initializeHardCodedTiles();
    }

    public void initializeBaseLayer()
    {
        //Creating a new viewboard will initialize the default board layout
        int xCoord = 45;
        int yCoord = 45;
        int firstY = yCoord;
        int secondY = firstY + 26;

        ViewHexCentralJava central = new ViewHexCentralJava();
        ViewHexHighland highland = new ViewHexHighland();
        ViewHexLowland lowland = new ViewHexLowland();
        ViewHexIrrigation irrigation = new ViewHexIrrigation();


        //15 rows 19 columns
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
                boardImages[i][j] = new ViewHexStack();

                Polygon hex = new Polygon();
                for(int k = 0; k<6; k++)
                {
                    int x = (int) (xCoord + 30*Math.cos(2*k*Math.PI/6));
                    int y = (int) (yCoord + 30*Math.sin(2*k*Math.PI/6));
                    hex.addPoint(x, y);
                }

                if(i < 8)
                {
                    boardImages[i][j].pushIntoStack(highland);
                }
                else
                {
                    boardImages[i][j].pushIntoStack(lowland);
                }

                if(i == 5 && j == 4)
                {
                    boardImages[i][j].popFromStack();
                    boardImages[i][j].pushIntoStack(irrigation);
                }
                if(i == 5 && j == 13)
                {
                    boardImages[i][j].popFromStack();
                    boardImages[i][j].pushIntoStack(irrigation);
                }
                if(i == 8 && j == 8)
                {
                    boardImages[i][j].popFromStack();
                    boardImages[i][j].pushIntoStack(irrigation);
                }
                boardPolygons[i][j] = hex;
                //boardImages[i][j].pushIntoStack(new BaseViewHex());
                xCoord += 45;

                if (j % 2 == 0) {
                    yCoord = secondY;
                } else {
                    yCoord = firstY;
                }
                selected[i][j] = false;
            }

            //firstY = secondY + 26;
            //secondY = firstY + 26;
            firstY += 52;
            secondY += 52;
            xCoord = 45;
            yCoord = firstY;
        }
    }

   public void initializeHardCodedTiles()
    {
        /*
        //Set highland tiles
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 19; j++) {
                boardImages[i][j].pushIntoStack(new ViewHexHighland());
            }
        }

        //Set lowland tiles
        for (int i = 8; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
                boardImages[i][j].pushIntoStack(new ViewHexLowland());
            }
        }
*/
        //Hardcode awkward board shape
        //Note that the stack size is 2 but the 'level' of the tile at this point is only 0
        ViewHexCentralJava central = new ViewHexCentralJava();
        ViewHexHighland highland = new ViewHexHighland();
        ViewHexLowland lowland = new ViewHexLowland();

        boardImages[1][4].pushIntoStack(central);

        boardImages[1][7].pushIntoStack(central);

        boardImages[1][10].pushIntoStack(central);

        boardImages[1][13].pushIntoStack(central);

        boardImages[2][4].pushIntoStack(central);

        boardImages[2][5].pushIntoStack(central);

        boardImages[2][7].pushIntoStack(central);

        boardImages[2][4].pushIntoStack(central);


        boardImages[2][10].pushIntoStack(central);


        boardImages[2][11].pushIntoStack(central);


        boardImages[2][13].pushIntoStack(central);


        boardImages[2][14].pushIntoStack(central);

        //Set mainland tiles
        for (int i = 3; i < 4; i++) {
            for (int j = 3; j < 17; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 4; i < 5; i++) {
            for (int j = 1; j < 18; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 5; i < 6; i++) {
            for (int j = 2; j < 18; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 6; i < 7; i++) {
            for (int j = 1; j < 18; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 7; i < 8; i++) {
            for (int j = 2; j < 18; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 8; i < 9; i++) {
            for (int j = 3; j < 16; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 9; i < 10; i++) {
            for (int j = 3; j < 17; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 10; i < 11; i++) {
            for (int j = 3; j < 16; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        for (int i = 11; i < 12; i++) {
            for (int j = 4; j < 14; j++) {

                boardImages[i][j].pushIntoStack(central);
            }
        }

        //Here you also want to place central java tiles just like up there


        boardImages[12][5].pushIntoStack(central);


        boardImages[12][6].pushIntoStack(central);


        boardImages[12][7].pushIntoStack(central);


        boardImages[12][9].pushIntoStack(central);


        boardImages[12][11].pushIntoStack(central);


        boardImages[12][12].pushIntoStack(central);


        boardImages[12][13].pushIntoStack(central);


        boardImages[13][5].pushIntoStack(central);


        boardImages[13][6].pushIntoStack(central);


        boardImages[13][11].pushIntoStack(central);


        boardImages[13][12].pushIntoStack(central);
    }

    public ViewHexStack getStackAt(int x, int y)
    {
        return boardImages[x][y];
    }

    public Polygon getPolygonAt(int x, int y)
    {
        return boardPolygons[x][y];
    }

    public Polygon[][] getPolygons()
    {
        return boardPolygons;
    }

    public ViewHexStack[][] getImages()
    {
        return boardImages;
    }

    public void setSelectedAt(int i, int j)
    {
        selected[i][j] = true;
    }

    public void setDeselectedAt(int i, int j)
    {
        selected[i][j] = false;
    }

    public boolean getSelectedAt(int i, int j)
    {
        return selected[i][j];
    }
}
