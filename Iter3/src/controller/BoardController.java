package controller;

import com.sun.deploy.panel.JavaPanel;
import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class BoardController
{
    private HexBoard hexBoard;
    private ArrayList<GraphDistance> distances;
    private Facade facade;
    private GameController gc;

    public BoardController(Facade facade)
    {
        hexBoard = new HexBoard();
        this.facade = facade;
        gc = facade.getGameController();
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
                //Ignore this code, once tested, delete it
                /*else if(presentGD.getDistance() == minDistance)
                {
                    shortestDistances.add(presentGD);
                }*/
            }

            //Ignore these comments, once tested, delete this comment block
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

    public int getAPForShortestPath(Space start, Space end)
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

    public ArrayList<Space> getAreaOfVillage(Space s)
    {
        ArrayList<Space> theCity = new ArrayList<Space>();
        ArrayList<Space> queue = new ArrayList<Space>();

        if (s.getTopTileComponent().getLandType().equals("Village"))
        {
            theCity.add(s);
            queue.add(s);
        }

        while (!queue.isEmpty())
        {
            //Get the neighbors of the first space in the queue
            ArrayList<Space> neighbors = this.getHexBoard().getNeighborsOfSpace(queue.get(0));

            //For each neighbor
            for (int i = 0; i < neighbors.size(); i++)
            {
                //Check if that neighbor has a village tile (is part of a village)
                if (neighbors.get(i).getTopTileComponent().getLandType().equals("Village"))
                {
                    //add this space to the city
                    theCity.add(neighbors.get(i));
                    //add this space to the queue
                    queue.add(neighbors.get(i));
                }
            }
            //remove the first space from the queue, leaving the others in
            queue.remove(0);
        }

        return theCity;
    }

    public boolean hasOneHighestRankingDeveloper(ArrayList<Space> city)
    {
        boolean highestUp = false;

        //an ArrayList of Spaces containing all those space with developers on them
        ArrayList<Space> spacesWithDevelopers = new ArrayList<Space>();

        //go through city to find spaces with developers on them
        for (int i = 0; i < city.size(); i++)
        {
            if (city.get(i).getHasDeveloper() == true)
            {
                spacesWithDevelopers.add(city.get(i));
            }
        }

        //if there is at least one space with a developer on it
        if (!spacesWithDevelopers.isEmpty())
        {
            //Developer hrd is the highest ranking developer
            Developer hrd = spacesWithDevelopers.get(0).getDeveloper();
            int levelOfHRD = this.getHexBoard().getSpaces().get(hrd.getSpaceID()).getLevel();

            //check all of the spaces with developers to find the highest ranking developer and his level
            for (int j = 0; j < spacesWithDevelopers.size(); j++)
            {
                if (spacesWithDevelopers.get(j).getLevel() > levelOfHRD)
                {
                    Space s = this.getHexBoard().getSpaces().get(hrd.getSpaceID());
                    spacesWithDevelopers.remove(s);
                    hrd = spacesWithDevelopers.get(j).getDeveloper();
                    levelOfHRD = spacesWithDevelopers.get(j).getLevel();
                    j = 0;
                }
                else if(spacesWithDevelopers.get(j).getLevel() < levelOfHRD)
                {
                    spacesWithDevelopers.remove(j);
                    j = 0;
                }
                //if equal, do nothing
            }
        }

        //more than one developer at the same level
        if (spacesWithDevelopers.size() > 1)
        {
            //get all of the players involved
            ArrayList<JavaPlayer> players = new ArrayList<JavaPlayer>();
            players.add(spacesWithDevelopers.get(0).getDeveloper().getJPlayer());

            //find all of the players involved
            for (int i = 0; i < spacesWithDevelopers.size(); i++)
            {
                if (!players.contains(spacesWithDevelopers.get(i).getDeveloper().getJPlayer()))
                {
                    players.add(spacesWithDevelopers.get(i).getDeveloper().getJPlayer());
                }
            }

            //create an array which holds the number of developers for each player
            int[] numDevelopers = new int[players.size()];
            for (int j = 0; j < numDevelopers.length; j++)
            {
                //initialize each element in the array to 0
                numDevelopers[j] = 0;
            }

            //for each space with a developer on it
            for (int k = 0; k < spacesWithDevelopers.size(); k++)
            {
                //check the player who owns the developer
                JavaPlayer jp = spacesWithDevelopers.get(k).getDeveloper().getJPlayer();
                int index = players.indexOf(jp);
                //increment that player's element in the numDeveloper array
                numDevelopers[index] = numDevelopers[index]++;
            }

            //all of the tied players that have the same number of developers
            ArrayList<JavaPlayer> tiedPlayers = new ArrayList<JavaPlayer>();
            tiedPlayers.add(players.get(0));
            int maxDevs = numDevelopers[0];
            for (int p = 1; p < numDevelopers.length; p++)
            {
                if(numDevelopers[p] > maxDevs)
                {
                    maxDevs = numDevelopers[p];
                    tiedPlayers.clear();
                    tiedPlayers.add(players.get(p));
                }
                else if(numDevelopers[p] == maxDevs)
                {
                    tiedPlayers.add(players.get(p));
                }
            }

            //if there are indeed tied players, nothing can be done, else
            if (tiedPlayers.size() == 1)
            {
                //check if highest ranking developer belongs to current player
                if (tiedPlayers.get(0).equals(gc.getPlayers().get(0)))
                {
                    highestUp = true;
                }
            }
        }
        else
        {
            //check if highest ranking developer belongs to current player
            if (spacesWithDevelopers.get(0).getDeveloper().getJPlayer().equals(gc.getPlayers().get(0)))
            {
                highestUp = true;
            }
        }

        return highestUp;
    }

    public boolean isConnectingCities(Space s)
    {
        boolean connector = false;
        //neighbors of Space s which could lead to cities
        ArrayList<Space> neighbors = this.getHexBoard().getNeighborsOfSpace(s);
        //number of cities found so far
        int citiesFound = 0;

        //for each neighbor
        for (int i = 0; i < neighbors.size(); i++)
        {
            boolean palaceFound = false;

            //queue containing village tiles
            ArrayList<Space> queue = new ArrayList<Space>();
            String lType = neighbors.get(i).getTopTileComponent().getLandType();
            if (lType.equals("Village"))
            {
                queue.add(neighbors.get(i));    //add village to the queue
            }
            else if (lType.equals("Palace"))
            {
                palaceFound = true;             //a palace has been found, don't go to while loop
                citiesFound++;                  //increment cities found
            }

            while (!queue.isEmpty() && !palaceFound)
            {
                //Get the neighbors of the first space in the queue
                ArrayList<Space> queueNeighbors = this.getHexBoard().getNeighborsOfSpace(queue.get(0));

                //For each neighbor
                for (int j = 0; j < queueNeighbors.size(); j++)
                {
                    String lt = neighbors.get(j).getTopTileComponent().getLandType();
                    //Check if that neighbor has a village tile (is part of a village)
                    if (lt.equals("Village"))
                    {
                        //add this space to the queue
                        queue.add(neighbors.get(i));
                    }
                    else if (lt.equals("Palace"))
                    {
                        //indicates that a palace, and thus a city, has been found
                        citiesFound++;
                        //set palaceFound to true, thus ending the while loop
                        palaceFound = true;
                    }
                }
                //remove the first space from the queue, leaving the others in
                queue.remove(0);
            }
        }

        if (citiesFound > 1)        //multiple cities found
        {
            connector = true;       //space is connecting cities, set connector to true
        }

        return connector;
    }

    public Space getSpaceFromID(int spaceID)
    {
        return this.getHexBoard().getSpaces().get(spaceID);
    }

    public HexBoard getHexBoard()
    {
        return this.hexBoard;
    }

    public void initializeBoard(){
        int boardSpaces = 285;
        //Sets the top half of the board to higlands
        TileComponent highland = new TileComponent(new LandType("highland"), new Tile());
        for(int i=0; i<boardSpaces/2; i++){
            addTileComponent(i,highland);
        }
        //Sets the bottom half of the board to lowlands
        TileComponent lowland = new TileComponent(new LandType("lowland"), new Tile());
        for(int i=boardSpaces/2; i<boardSpaces; i++){
            addTileComponent(i, lowland);
        }

        TileComponent central = new TileComponent(new LandType("central"), new Tile());
        removeTopTileComponent(23);
        addTileComponent(23,central);

        removeTopTileComponent(26);
        addTileComponent(26,central);

        removeTopTileComponent(29);
        addTileComponent(29,central);

        removeTopTileComponent(32);
        addTileComponent(32,central);

        removeTopTileComponent(42);
        addTileComponent(42,central);

        removeTopTileComponent(42);
        addTileComponent(42,central);

        removeTopTileComponent(45);
        addTileComponent(45,central);

        removeTopTileComponent(46);
        addTileComponent(46,central);

        removeTopTileComponent(48);
        addTileComponent(48,central);

        removeTopTileComponent(49);
        addTileComponent(49,central);

        removeTopTileComponent(51);
        addTileComponent(51,central);

        for(int i=60; i<74; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for (int i=77; i<94; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=97; i<113; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=115; i<132; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=135; i<151; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=155; i<168; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=174; i<188; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=193; i<206; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=213; i<223; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        for(int i=233; i<236; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        removeTopTileComponent(237);
        addTileComponent(237,central);

        for(int i=239; i<242; i++){
            removeTopTileComponent(i);
            addTileComponent(i,central);
        }

        removeTopTileComponent(252);
        addTileComponent(252,central);

        removeTopTileComponent(253);
        addTileComponent(253,central);

        removeTopTileComponent(258);
        addTileComponent(258,central);

        removeTopTileComponent(259);
        addTileComponent(259,central);

        //Placing irrigation tiles
        TileComponent irrigation = new TileComponent(new LandType("irrigation"), new Tile());
        removeTopTileComponent(99);
        addTileComponent(99,irrigation);

        removeTopTileComponent(108);
        addTileComponent(108,irrigation);

        removeTopTileComponent(160);
        addTileComponent(160,irrigation);
    }

    public void addTileComponent(int spaceID, TileComponent tc){
        hexBoard.addTileComponent(spaceID,tc);
    }

    public TileComponent removeTopTileComponent(int spaceID){
        return hexBoard.removeTopTileComponent(spaceID);
    }
}
