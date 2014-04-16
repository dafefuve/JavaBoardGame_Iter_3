package model;

/**
 * Created by Arnav on 4/16/14.
 */

public class Edge
{
    Space s1;
    Space s2;
    int weight;     //Needed for pathfinding, change in AP between spaces, etc.

    public Edge(Space sOne, Space sTwo, int w)
    {
        s1 = sOne;
        s2 = sTwo;
        weight = w;
    }

    public Space getSpaceOne()
    {
        return this.s1;
    }

    public Space getSpaceTwo()
    {
        return this.s2;
    }

    public int getWeight()
    {
        return this.weight;
    }

    public void setWeight(int newWeight)
    {
        this.weight = newWeight;
    }
}
