package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewBoard
{
    private ViewHexStack[][] board;

    public ViewBoard(int rows, int cols)
    {
        board = new ViewHexStack[rows][cols];
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

        //15 rows 19 columns
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
                board[i][j] = new ViewHexStack();
                board[i][j].pushIntoStack(new BaseViewHex(xCoord, yCoord));
                xCoord += 45;

                if (j % 2 == 0) {
                    yCoord = secondY;
                } else {
                    yCoord = firstY;
                }
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
        //Set highland tiles
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 19; j++) {
                int x = board[i][j].peekIntoStack().getCenterX();
                int y = board[i][j].peekIntoStack().getCenterY();
                board[i][j].pushIntoStack(new ViewHexHighland(x, y));
            }
        }

        //Set lowland tiles
        for (int i = 8; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
                int x = board[i][j].peekIntoStack().getCenterX();
                int y = board[i][j].peekIntoStack().getCenterY();
                board[i][j].pushIntoStack(new ViewHexLowland(x, y));
            }
        }

        //Hardcode awkward board shape
        //Note that the stack size is 2 but the 'level' of the tile at this point is only 0
        int x = board[1][4].peekIntoStack().getCenterX();
        int y = board[1][4].peekIntoStack().getCenterY();
        board[1][4].pushIntoStack(new ViewHexCentralJava(x, y));

        x = board[1][7].peekIntoStack().getCenterX();
        y = board[1][7].peekIntoStack().getCenterY();
        board[1][7].pushIntoStack(new ViewHexCentralJava(x, y));

        x = board[1][10].peekIntoStack().getCenterX();
        y = board[1][10].peekIntoStack().getCenterY();
        board[1][10].pushIntoStack(new ViewHexCentralJava(x, y));

        x = board[1][13].peekIntoStack().getCenterX();
        y = board[1][13].peekIntoStack().getCenterY();
        board[1][13].pushIntoStack(new ViewHexCentralJava(x, y));

        x = board[2][4].peekIntoStack().getCenterX();
        y = board[2][4].peekIntoStack().getCenterY();
        board[2][4].pushIntoStack(new ViewHexCentralJava(x, y));

        x = board[2][5].peek().getCenterX();
        y = board[2][5].peek().getCenterY();
        board[2][5].push(new ViewHexCentralJava(x, y));

        x = board[2][7].peek().getCenterX();
        y = board[2][7].peek().getCenterY();
        board[2][7].push(new ViewHexCentralJava(x, y));

        x = board[2][8].peek().getCenterX();
        y = board[2][8].peek().getCenterY();
        board[2][4].push(new ViewHexCentralJava(x, y));

        x = board[2][10].peek().getCenterX();
        y = board[2][10].peek().getCenterY();
        board[2][10].push(new ViewHexCentralJava(x, y));

        x = board[2][11].peek().getCenterX();
        y = board[2][11].peek().getCenterY();
        board[2][11].push(new ViewHexCentralJava(x, y));

        x = board[2][13].peek().getCenterX();
        y = board[2][13].peek().getCenterY();
        board[2][13].push(new ViewHexCentralJava(x, y));

        x = board[2][14].peek().getCenterX();
        y = board[2][14].peek().getCenterY();
        board[2][14].push(new ViewHexCentralJava(x, y));

        //Set mainland tiles
        for (int i = 3; i < 4; i++) {
            for (int j = 3; j < 17; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 4; i < 5; i++) {
            for (int j = 1; j < 18; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 5; i < 6; i++) {
            for (int j = 2; j < 18; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 6; i < 7; i++) {
            for (int j = 1; j < 18; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 7; i < 8; i++) {
            for (int j = 2; j < 18; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 8; i < 9; i++) {
            for (int j = 3; j < 16; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 9; i < 10; i++) {
            for (int j = 3; j < 17; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 10; i < 11; i++) {
            for (int j = 3; j < 16; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        for (int i = 11; i < 12; i++) {
            for (int j = 4; j < 14; j++) {
                int a = board[i][j].peek().getCenterX();
                int b = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(a, b));
            }
        }

        //Here you also want to place central java tiles just like up there

        int c = board[12][5].peek().getCenterX();
        int d = board[12][5].peek().getCenterY();
        board[12][5].push(new ViewHexCentralJava(c, d));

        c = board[12][6].peek().getCenterX();
        d = board[12][6].peek().getCenterY();
        board[12][6].push(new ViewHexCentralJava(c, d));

        c = board[12][7].peek().getCenterX();
        d = board[12][7].peek().getCenterY();
        board[12][7].push(new ViewHexCentralJava(c, d));

        c = board[12][9].peek().getCenterX();
        d = board[12][9].peek().getCenterY();
        board[12][9].push(new ViewHexCentralJava(c, d));

        c = board[12][11].peek().getCenterX();
        d = board[12][11].peek().getCenterY();
        board[12][11].push(new ViewHexCentralJava(c, d));

        c = board[12][12].peek().getCenterX();
        d = board[12][12].peek().getCenterY();
        board[12][12].push(new ViewHexCentralJava(c, d));

        c = board[12][13].peek().getCenterX();
        d = board[12][13].peek().getCenterY();
        board[12][13].push(new ViewHexCentralJava(c, d));

        c = board[13][5].peek().getCenterX();
        d = board[13][5].peek().getCenterY();
        board[13][5].push(new ViewHexCentralJava(c, d));

        c = board[13][6].peek().getCenterX();
        d = board[13][6].peek().getCenterY();
        board[13][6].push(new ViewHexCentralJava(c, d));

        c = board[13][11].peek().getCenterX();
        d = board[13][11].peek().getCenterY();
        board[13][11].push(new ViewHexCentralJava(c, d));

        c = board[13][12].peek().getCenterX();
        d = board[13][12].peek().getCenterY();
        board[13][12].push(new ViewHexCentralJava(c, d));
    }

    public Stack<ViewHex> getStackAt(int x, int y)
    {
        return board[x][y];
    }
}
