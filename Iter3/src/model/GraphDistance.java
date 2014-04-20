package model;

import controller.BoardController;

/**
 * Created by Arnav on 4/18/14.
 */
public class GraphDistance
{
    BoardController bc;
    int distance;
    Edge correspondingEdge;
    int edgeIndex;

    public GraphDistance(int d, Edge ce)
    {
        distance = d;
        correspondingEdge = ce;
        edgeIndex = bc.getHexBoard().getEdges().indexOf(correspondingEdge);
    }

    public int getDistance()
    {
        return this.distance;
    }

    public void setDistance(int newDistance)
    {
        this.distance = newDistance;
    }

    public Edge getCorrespondingEdge()
    {
        return this.correspondingEdge;
    }

    public int getEdgeIndex()
    {
        return this.edgeIndex;
    }
}
