package model;

import java.util.ArrayList;

/**
 * Created by Daniel on 4/14/2014.
 */

public class Board
{
    HexBoard hBoard;

    public Board()
    {
        hBoard = new HexBoard();
    }

    public HexBoard getHBoard()
    {
        return this.hBoard;
    }

    public ArrayList<Edge> getEdgesForSpace(Space s)
    {
        return this.hBoard.getEdgesForSpace(s);
    }

    public ArrayList<Space> getNeighborsOfSpace(Space s)
    {
        return this.hBoard.getNeighborsOfSpace(s);
    }

    public ArrayList<Edge> getEdges()
    {
        return this.hBoard.getEdges();
    }

    public ArrayList<Space> getSpaces()
    {
        return this.hBoard.getSpaces();
    }
}
