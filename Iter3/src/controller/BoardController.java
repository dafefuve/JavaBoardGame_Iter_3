package controller;

import model.Edge;
import model.GraphDistance;
import model.HexBoard;
import model.Space;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class BoardController
{
    HexBoard hexBoard;
    ArrayList<GraphDistance> distances;

    public BoardController()
    {
        hexBoard = new HexBoard();
        distances = new ArrayList<GraphDistance>();
        for (int i = 0; i < this.getHexBoard().getEdges().size(); i++)
        {
            distances.add(new GraphDistance(0, this.getHexBoard().getEdges().get(i)));
        }
    }

    public void addWeightsToEdges(ArrayList<Edge> edges)
    {
        edges = this.getHexBoard().getEdges();
        for (int i = 0; i < edges.size(); i++)
        {
            String spaceOneLandType = edges.get(i).getSpaceOne().getTopTileComponent().getLandType();
            String spaceTwoLandType = edges.get(i).getSpaceTwo().getTopTileComponent().getLandType();
            if (spaceOneLandType.equals(spaceTwoLandType))
            {
                edges.get(i).setWeight(0);
            }
            else if (spaceOneLandType.equals("Mountain") || spaceTwoLandType.equals("Mountain"))
            {
                edges.get(i).setWeight(2);
            }
            else
            {
                edges.get(i).setWeight(1);
            }
        }
    }

    public void establishDistances(Space startSpace)
    {
        ArrayList<Space> pathOfSpaces;

        this.addWeightsToEdges(this.getHexBoard().getEdgesForSpace(startSpace));
        //add weights only to immediate edges, not all of them

        for (int i = 0; i < this.getHexBoard().getEdgesForSpace(startSpace).size(); i++)
        {
            Edge tempE = this.getHexBoard().getEdgesForSpace(startSpace).get(i);
            int locOfTempE = this.getHexBoard().getEdges().indexOf(tempE);

            if (distances.get(locOfTempE).getDistance() + tempE.getWeight() < distances.get(locOfTempE).getDistance())
            {
                distances.get(locOfTempE).setDistance(distances.get(locOfTempE).getDistance() + tempE.getWeight());
            };

            Space partnerSpace = tempE.getPartnerSpace(startSpace);
            this.establishDistances(partnerSpace);
        }
    }

    public ArrayList<Space> getShortestPath(Space startSpace, Space endSpace)
    {
        ArrayList<Space> shortestPath = new ArrayList<Space>();
        //Establish the distances emanating from the startSpace all the way to ends of the board
        //This is because ANY path can potentially be the shortest path from startSpace to endSpace
        this.establishDistances(startSpace);

        //going back from the endSpace to the startSpace
        while (!startSpace.equals(endSpace))    //until you reach the startSpace
        {
            //get all of the edges associated with the endSpace
            ArrayList<Edge> edgesForSpace = this.getHexBoard().getEdgesForSpace(endSpace);

            //assign the minDistance to the distance from the first of these edges
            int minDistance = this.getHexBoard().getDistanceForEdge(edgesForSpace.get(0)).getDistance();

            //Create an ArrayList of the shortestDistances
            //ArrayList<GraphDistance> shortestDistances = new ArrayList<GraphDistance>();
            //shortestDistances.add(this.getHexBoard().getDistanceForEdge(edgesForSpace.get(0)));

            GraphDistance shortDist = this.getHexBoard().getDistanceForEdge(edgesForSpace.get(0));

            //for each edge emanating from endSpace, get the shortest distances
            for (int i = 1; i < edgesForSpace.size(); i++)
            {
                GraphDistance presentGD = this.getHexBoard().getDistanceForEdge(edgesForSpace.get(i));
                if (presentGD.getDistance() < minDistance)
                {
                    //shortestDistances.clear();
                    minDistance = presentGD.getDistance();
                    //shortestDistances.add(presentGD);
                    shortDist = presentGD;
                }
                /*else if(presentGD.getDistance() == minDistance)
                {
                    shortestDistances.add(presentGD);
                }*/
            }
            //we now have all of the shortest distances emanating from endSpace
            //if there are multiple shortest distances, then either one of them is the shortest path
            //just pick the first one and go from there
            //GraphDistance pathMaker = shortestDistances.get(0);

            //go to the connecting space
            Space connectingSpace = shortDist.getCorrespondingEdge().getPartnerSpace(endSpace);

            //add this space to the path of spaces
            shortestPath.add(connectingSpace);

            //repeat the while loop until you reach the startSpace
            endSpace = connectingSpace;
        }

        return shortestPath;
    }

    public int getAPforShortestPath(Space start, Space end)
    {
        int ap = 0;

        ArrayList<Space> theShortestPath = this.getShortestPath(start, end);
        for (int i = 0; i < theShortestPath.size()-1; i++)
        {
            String firstSpaceLandType = theShortestPath.get(i).getTopTileComponent().getLandType();
            String secondSpaceLandType = theShortestPath.get(i+1).getTopTileComponent().getLandType();

            if (firstSpaceLandType.equals(secondSpaceLandType))
            {
                ap+=0;  //Just to indicate that there is no AP cost in moving between same land types
            }
            else if (firstSpaceLandType.equals("Mountain") || secondSpaceLandType.equals("Mountain"))
            {
                ap+=2;   //indicates 2 AP cost in going from Mountain to Central Java
            }
            else
            {
                ap+=1;  //indicates 1 AP cost in moving between different land types
            }
        }

        return ap;
    }

    public HexBoard getHexBoard()
    {
        return this.hexBoard;
    }
}
