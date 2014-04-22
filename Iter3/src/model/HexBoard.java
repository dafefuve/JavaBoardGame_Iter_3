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

        //make the N-S edges
        for (int eachCol = 0; eachCol < 19; eachCol++)
        {
            for (int aRow = 0; (aRow+1) < 15; aRow++)
            {
                //add to edges the Edge(northSpace, southSpace, 0)
                edges.add(new Edge(spaces.get(aRow*19 + eachCol), spaces.get((aRow+1)*19 + eachCol), 0));
            }
        }

        //make the NW-SE edges
        for (int eachCol = 0; eachCol < 19; eachCol++)
        {
            for (int aRow = 0; aRow+1 < 15; aRow++)
            {
                if (eachCol % 2 == 0)
                {
                    //make edge from a space with row-evenCol to a space with row-oddCol
                    edges.add(new Edge(spaces.get(aRow*19+eachCol), spaces.get(aRow*19+eachCol+1), 0));
                }
                else
                {
                    //make edge from a space with row-oddCol to a space with row-evenCol
                    edges.add(new Edge(spaces.get((aRow)*19+eachCol), spaces.get((aRow+1)*19+eachCol+1), 0));
                }
            }
        }

        //make the NE-SW edges
        for (int eachCol = 0; eachCol < 19; eachCol++)
        {
            for (int aRow = 14; aRow > 0; aRow--)
            {
                if (eachCol % 2 == 0)
                {
                    edges.add(new Edge(spaces.get(aRow*19+eachCol), spaces.get((aRow-1)*19+eachCol+1), 0));
                }
                else
                {
                    edges.add(new Edge(spaces.get(aRow*19+eachCol), spaces.get(aRow*19+eachCol+1), 0));
                }
            }
        }

        //this.defineEdge(this.getSpaces().get(0));   //start defining edges starting at Space at (0,0)
    }

    public void defineEdge(Space s)
    {
                       //The space directly below s (direction 2)
        if (s.getRow()+1 < 15)      //ensure that now space is created outside of the board
        {
            Space southS = spaces.get((s.getRow()+1)*19+s.getColumn());
            this.edges.add(new Edge(s, southS, 0));
            defineEdge(southS);
        }

        //Space southeastS;                   //The space southeast of s (direction 3)
        if (s.getColumn() % 2 == 0)         //even column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                Space southeastS = spaces.get((s.getRow()*19 + s.getColumn()+1));
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }
        else                                //odd column
        {
            if (s.getRow()+1 < 15 && s.getColumn()+1 < 19)      //no space outside board
            {
                Space southeastS = spaces.get((s.getRow()+1)*19+ s.getColumn()+1);
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }

        //Space northeastS;               //The space northeast of s (direction 9)
        if (s.getColumn() % 2 == 0)     //even column
        {
            if (s.getRow()-1 >= 0 && s.getColumn()+1 < 19)       //no space outside board
            {
                Space northeastS = spaces.get((s.getRow()-1)*19+ s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
        else                            //odd column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                Space northeastS = spaces.get((s.getRow())*19+ s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
    }

    //returns a list of all the edges emanating from a given space
    public ArrayList<Edge> getEdgesForSpace(Space oneGivenSpace)
    {
        //System.out.println("Inside get edges for space");
        ArrayList<Edge> edgesForSpace = new ArrayList<Edge>();
        //System.out.println("is edgesForSpace empty?: " + edgesForSpace.isEmpty());

        int i = 0;
        //System.out.println("i: " + 1);
        int numEdgesFound = 0;
        //System.out.println("numEdgesFound: " + numEdgesFound);
        //System.out.println("this.edges.size() " + this.getEdges().size());
        while (i < this.getEdges().size() && numEdgesFound < 6)
        {
            Space esOne = this.getEdges().get(i).getSpaceOne();
            //System.out.println("esOne id: " + esOne.getId());
            Space esTwo = this.getEdges().get(i).getSpaceTwo();
            //System.out.println("esTwo id: " + esTwo.getId());
            if (esOne.equals(oneGivenSpace) || esTwo.equals(oneGivenSpace))
            {
                edgesForSpace.add(this.edges.get(i));
                numEdgesFound++;
                //System.out.println("NumEdgesFound " + numEdgesFound);
            }
            i++;
        }

        return edgesForSpace;
    }

    //returns a list of all of the given space's neighbors
    public ArrayList<Space> getNeighborsOfSpace(Space centerSpace)
    {
        //System.out.println("Inside neighbors of space");
        ArrayList<Space> neighbors = new ArrayList<Space>();
        //System.out.println(neighbors.isEmpty());

        ArrayList<Edge> myEdges = new ArrayList<Edge>();
        //System.out.println(myEdges.isEmpty());
        myEdges = this.getEdgesForSpace(centerSpace);
        //System.out.println("myEdges size: " + myEdges.size());
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
        //System.out.println(neighbors.size());

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
    public void addTileComponent(int spaceID, TileComponent tc){
        spaces.get(spaceID).addTileComponent(tc);
    }

    public TileComponent removeTopTileComponent(int spaceID){
        return spaces.get(spaceID).removeTopTileComponent();
    }
}
