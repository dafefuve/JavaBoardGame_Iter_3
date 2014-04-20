package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by alexbujduveanu on 4/19/14.
 */
public class ViewBoard
{
    private Stack<ViewHex>[][] board;

    public ViewBoard(int rows, int cols)
    {
        board = new Stack<ViewHex>[rows][cols];
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
                board[i][j] = new Stack<ViewHex>();
                board[i][j].push(new BaseViewHex(xCoord, yCoord));
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
                int x = board[i][j].peek().getCenterX();
                int y = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexHighland(x, y));
            }
        }

        //Set lowland tiles
        for (int i = 8; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
                int x = board[i][j].peek().getCenterX();
                int y = board[i][j].peek().getCenterY();
                board[i][j].push(new ViewHexLowland(x, y));
            }
        }

        //Hardcode awkward board shape
        //Note that the stack size is 2 but the 'level' of the tile at this point is only 0
        int x = board[1][4].peek().getCenterX();
        int y = board[1][4].peek().getCenterY();
        board[1][4].push(new ViewHexCentralJava(x, y));

        x = board[1][7].peek().getCenterX();
        y = board[1][7].peek().getCenterY();
        board[1][7].push(new ViewHexCentralJava(x, y));

        theBoard[1][7].getBoardHex().resetMountain();
        theBoard[1][10].getBoardHex().resetMountain();
        theBoard[1][13].getBoardHex().resetMountain();
        theBoard[2][4].getBoardHex().resetMountain();
        theBoard[2][5].getBoardHex().resetMountain();
        theBoard[2][7].getBoardHex().resetMountain();
        theBoard[2][8].getBoardHex().resetMountain();
        theBoard[2][10].getBoardHex().resetMountain();
        theBoard[2][11].getBoardHex().resetMountain();
        theBoard[2][13].getBoardHex().resetMountain();
        theBoard[2][14].getBoardHex().resetMountain();


        //Set mainland tiles
        for (int i = 3; i < 4; i++) {
            for (int j = 3; j < 17; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 4; i < 5; i++) {
            for (int j = 1; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 5; i < 6; i++) {
            for (int j = 2; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 6; i < 7; i++) {
            for (int j = 1; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 7; i < 8; i++) {
            for (int j = 2; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 8; i < 9; i++) {
            for (int j = 3; j < 16; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        for (int i = 9; i < 10; i++) {
            for (int j = 3; j < 17; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        for (int i = 10; i < 11; i++) {
            for (int j = 3; j < 16; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        for (int i = 11; i < 12; i++) {
            for (int j = 4; j < 14; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        //Here you also want to place central java tiles just like up there
        theBoard[12][5].getBoardHex().resetLowlands();
        theBoard[12][6].getBoardHex().resetLowlands();
        theBoard[12][7].getBoardHex().resetLowlands();
        theBoard[12][9].getBoardHex().resetLowlands();
        theBoard[12][11].getBoardHex().resetLowlands();
        theBoard[12][12].getBoardHex().resetLowlands();
        theBoard[12][13].getBoardHex().resetLowlands();
        theBoard[13][5].getBoardHex().resetLowlands();
        theBoard[12][6].getBoardHex().resetLowlands();
        theBoard[13][11].getBoardHex().resetLowlands();
        theBoard[13][12].getBoardHex().resetLowlands();


    }
}
