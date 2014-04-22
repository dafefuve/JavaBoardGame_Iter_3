package controller;

import model.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Horacio on 4/21/14.
 */
public class GameSetupController {

    private Facade facade;

    public GameSetupController(Facade facade){
        this.facade = facade;
    }

    public void setUpGame(){

        int numPlayers = 2; //This value should come from the view itself

        Board board = new Board();       //Automatically creates the board when it is instantiated
        ArrayList<JavaPlayer> players = new ArrayList<JavaPlayer>();
        GameInventory gameInventory = createGameResources();    //calls that method to create the GameInventory

        //setting up the inventory for each player
        for (int i= 0; i < numPlayers; i++ ){
            String name = "Player"+(i+1);
            players.add(new JavaPlayer(name , createPlayerResources(gameInventory)));
        }


        Game game = new Game(players, gameInventory, board);    //The game is created here

        facade.getGameController().setGame(game);   //gives GameController the reference to game

        initializeBoard();


    }

    private GameInventory createGameResources(){
        Stack<PalaceCard> initDeck = createDeck();          //Calls the function createDeck to initialize the deck
        HashMap<String, Integer> items = new HashMap<String, Integer>();

        items.put("threeBlock", 56);
        items.put("irrigationTile", 16);
        items.put("palaceTileLevelTwo", 6);
        items.put("palaceTileLevelFour", 7);
        items.put("palaceTileLevelSix", 8);
        items.put("palaceTileLevelEight", 9);
        items.put("palaceTileLevelTen", 10);

        GameInventory inventory = new GameInventory(initDeck, items);   //Sets up and returns the game inventory

        return inventory;

    }

    private Stack<PalaceCard> createDeck(){
        Stack<PalaceCard> deck = new Stack<PalaceCard>();
        ArrayList<PalaceCard> rawDeck = new ArrayList<PalaceCard>();
        //Calls the addCards method to create all the cards and add them to an array
        addCards("drum",null,0,5,rawDeck);
        addCards("mask",null,1,5,rawDeck);
        addCards("puppet",null,2,5,rawDeck);
        addCards("drum","mask",3,5,rawDeck);
        addCards("drum", "puppet",4, 5, rawDeck);
        addCards("mask","puppet",5,5,rawDeck);

        //The array is shuffled and then used to populate the deck stack
        Collections.shuffle(rawDeck);
        PalaceCard card;
        for(int i=0; i<rawDeck.size(); i++){
            card = rawDeck.remove(0);
            deck.push(card);
        }

        return deck;
    }
    //Creates the palace cards
    private void addCards(String symbol1, String symbol2,int ID, int count, ArrayList<PalaceCard> cards){
        for(int i=0; i<count;i++){
            cards.add(new PalaceCard(symbol1,symbol2,ID));
        }
    }

    private PlayerInventory createPlayerResources(GameInventory gameInventory){
        ArrayList<PalaceCard> hand = new ArrayList<PalaceCard>();
        HashMap<String,Integer> items = new HashMap<String, Integer>();

        items.put("developer", 12);
        items.put("actionToken", 3);
        items.put("twoBlock", 5);
        items.put("riceTile", 3);
        items.put("villageTile", 2);
        items.put("actionPoints", 6);
        items.put("famePoints", 0);

        for(int i=0; i<3; i++){
           hand.add(gameInventory.drawCardFromDeck());
        }

        PlayerInventory inventory = new PlayerInventory(hand,items);

        return inventory;
    }

    public void initializeBoard(){
        facade.getBoardController().initializeBoard();
    }

    //todo PalaceFestival set up
}
