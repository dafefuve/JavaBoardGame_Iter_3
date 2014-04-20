package model;

import java.util.*;

/**
 * Created by Daniel on 4/14/2014.
 */

public class HexBoard
{
    private ArrayList<Edge> edges;
    private ArrayList<Space> spaces;
    private ArrayList<GraphDistance> distances;

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

        this.defineEdge(this.getSpaces().get(0));   //start defining edges starting at Space at (0,0)
    }

    public void defineEdge(Space s)
    {
        Space southS;               //The space directly below s (direction 2)
        if (s.getRow()+1 < 15)      //ensure that now space is created outside of the board
        {
            southS = spaces.get((s.getRow()+1)*19+s.getColumn());
            this.edges.add(new Edge(s, southS, 0));
            defineEdge(southS);
        }

        Space southeastS;                   //The space southeast of s (direction 3)
        if (s.getColumn() % 2 == 0)         //even column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                southeastS = spaces.get((s.getRow()*19 + s.getColumn()+1));
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }
        else                                //odd column
        {
            if (s.getRow()+1 < 15 && s.getColumn()+1 < 19)      //no space outside board
            {
                southeastS = spaces.get((s.getRow()+1)*19+ s.getColumn()+1);
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }

        Space northeastS;               //The space northeast of s (direction 9)
        if (s.getColumn() % 2 == 0)     //even column
        {
            if (s.getRow()-1 >= 0 && s.getColumn()+1 < 19)       //no space outside board
            {
                northeastS = spaces.get((s.getRow()-1)*19+ s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
        else                            //odd column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                northeastS = spaces.get((s.getRow()+1)*19+ s.getColumn()+1);
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
    public ArrayList<Space> getNeighborsOfSpace(Space centerSpace)
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

    public GraphDistance getDistanceForEdge(Edge e)
    {
        boolean foundDistance = false; int i = 0;
        GraphDistance gd = new GraphDistance(0, e);
        while (!foundDistance)
        {
            if (this.getDistances().get(i).getCorrespondingEdge().equals(e))
            {
                gd = this.getDistances().get(i);
                foundDistance = true;
            }
            i++;
        }
        return gd;
    }

    public ArrayList<Edge> getEdges()
    {
        return this.edges;
    }

    public ArrayList<Space> getSpaces()
    {
        return this.spaces;
    }

    public ArrayList<GraphDistance> getDistances()
    {
        return this.distances;
    }

    public void setSpace(Space s, int index)
    {
        this.spaces.remove(index);
        this.spaces.add(index, s);
    }
}
