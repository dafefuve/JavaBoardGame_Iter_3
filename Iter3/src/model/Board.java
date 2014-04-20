package model;

import java.util.ArrayList;

/**
 * Created by Daniel on 4/14/2014.
 */

public class Board
{
    HexBoard hBoard;
    //This board consists of a HexBoard and nothing more
    //All boards have spaces and edges between those spaces

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
        return this.getHBoard().getEdgesForSpace(s);
    }

    public ArrayList<Space> getNeighborsOfSpace(Space s)
    {
        return this.getHBoard().getNeighborsOfSpace(s);
    }

    public ArrayList<Edge> getEdges()
    {
        return this.getHBoard().getEdges();
    }

    public ArrayList<Space> getSpaces()
    {
        return this.getHBoard().getSpaces();
    }

    public void setSpace(Space s, int index)
    {
        this.getHBoard().setSpace(s,index);
    }
}
