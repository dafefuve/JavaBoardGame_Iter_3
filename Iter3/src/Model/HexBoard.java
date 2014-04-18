package model;

import java.util.*;

/**
 * Created by Daniel on 4/14/2014.
 */

public class HexBoard
{
    ArrayList<Edge> edges;
    ArrayList<Space> spaces;

    public HexBoard()
    {
        edges = new ArrayList<Edge>();

        spaces = new ArrayList<Space>();
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 19; j++)
            {
                spaces.add(new Space(i, j));
            }
        }
    }

    public void defineEdge(Space s)
    {
        Space southS;               //The space directly below s (direction 2)
        if (s.getRow()+1 < 15)      //ensure that now space is created outside of the board
        {
            southS = new Space(s.getRow()+1,s.getColumn());
            this.edges.add(new Edge(s, southS, 0));
            defineEdge(southS);
        }

        Space southeastS;                   //The space southeast of s (direction 3)
        if (s.getColumn() % 2 == 0)         //even column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                southeastS = new Space(s.getRow(), s.getColumn()+1);
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }
        else                                //odd column
        {
            if (s.getRow()+1 < 15 && s.getColumn()+1 < 19)      //no space outside board
            {
                southeastS = new Space(s.getRow()+1, s.getColumn()+1);
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }

        Space northeastS;               //The space northeast of s (direction 9)
        if (s.getColumn() % 2 == 0)     //even column
        {
            if (s.getRow()-1 >= 0 && s.getColumn()+1 < 19)       //no space outside board
            {
                northeastS = new Space(s.getRow()-1, s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
        else                            //odd column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                northeastS = new Space(s.getRow()+1, s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
    }

    //returns a list of all the edges emanating from a given space
    public ArrayList<Edge> getEdgesForSpace(Space oneGivenSpace)
    {
        ArrayList<Edge> edgesForSpace = new ArrayList<Edge>();

        int i = 0;
        int numEdgesFound = 0;
        while (i < 285 && numEdgesFound < 6)
        {
            Space esOne = this.edges.get(i).getSpaceOne();
            Space esTwo = this.edges.get(i).getSpaceTwo();
            if (esOne.equals(oneGivenSpace) || esTwo.equals(oneGivenSpace))
            {
                edgesForSpace.add(this.edges.get(i));
                numEdgesFound++;
            }
        }

        return edgesForSpace;
    }

    //returns a list of all of the given space's neighbors
    public ArrayList<Space> getNeighbors(Space centerSpace)
    {
        ArrayList<Space> neighbors = new ArrayList<Space>();

        List<Edge> myEdges = this.getEdgesForSpace(centerSpace);
        for (int i = 0; i < myEdges.size(); i++)
        {
            if (myEdges.get(i).getSpaceOne().equals(centerSpace))   //centerSpace is SpaceOne
            {
                neighbors.add(myEdges.get(i).getSpaceTwo());
            }
            else                                                    //centerSpace is SpaceTwo
            {
                neighbors.add(myEdges.get(i).getSpaceOne());
            }
        }

        return neighbors;
    }

    public ArrayList<Edge> getEdges()
    {
        return this.edges;
    }

    public ArrayList<Space> getSpaces()
    {
        return this.spaces;
    }
}
