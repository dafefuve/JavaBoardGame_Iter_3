package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.GameController;
import model.TileComponent;
import model.LandType;
import model.Palace;
import model.Tile;
import model.Space;
import controller.PlayerController;
/*
 * Created by Will
 */
public class PlacePalaceTileCommand extends Command {
    private BoardController boardController;
    private GameController gameController;
    private PlayerController playerController;
    //private Space s;
    private int location;
    private int level;
    public PlacePalaceTileCommand(BoardController boardController, PlayerController playerController, GameController gameController, int level){
        this.boardController=boardController;
        this.gameController=gameController;
        this.level=level;
    }

    public boolean execute(){
    //TODO a method that finds the space that the cursor is currently hovering over
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
    //TODO I assume there is a method in gameController that accesses the communal inventory
        int count = gameController.getInventory().getItem("palaceTile");
        Space s = boardController.getSpaceFromID(location);
        if(count<=0){
            System.out.println("No palace tiles left! Broke the rules!");
            //then do thingie to notify player that they broke the rules
        }
        else if(!s.getTopTileComponent().getLandType().equals("village")){
            //TODO make tile components more OOP and less POOP
            System.out.println("The tile atop the space is not a village")
        }
        else if(){
            //so here we need to check if the current player has the right to build a palace... if they are the player
            //with the highest game piece or the player with the sequence of highest game pieces. Ties do not count
        }
        else {
        //TODO I assume boardController has a sort of placeTile() method
            gameController.getInventory().setItem("palaceTile", count-1);
            TileComponent tc = new TileComponent(new LandType("palace"), new Tile());
            s.addTileComponent(tc);
            s.setPalace(new Palace(level));
            playerController.setItem("famePoints",playerController.getItem("famePoints")+level/2);
            return true;
        }
        return false;
    }
    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        gameController.getInventory().setItem("palaceTile", gameController.getInventory().getItem("palaceTile")+1);
         playerController.setItem("famePoints",playerController.getItem("famePoints")-level/2);
        s.removeTopTileComponent();
        s.setPalace(null);
    }

    public String toString(){
        return null;
    }
    public void setLocation(int l){
        location=l;
    }
    public int getLocation(){
        return location;
    }
}