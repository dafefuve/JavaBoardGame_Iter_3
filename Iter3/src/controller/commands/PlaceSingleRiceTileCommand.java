package controller.commands;

import controller.BoardController;
import controller.GameController;
import model.LandType;
import model.Space;
import model.Tile;
import model.TileComponent;

/*
 * Created by Will
 */
public class PlaceSingleRiceTileCommand extends MovableCommands {
    private BoardController boardController;
    private GameController gameController;
    //private Space s;
    private int location;
    public PlaceSingleRiceTileCommand(BoardController boardController, GameController gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public boolean execute(){
    //TODO a method that finds the space that the cursor is currently hovering over
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
    //TODO I assume there is a method in gameController that accesses the communal inventory
        int remainingVillageCount = gameController.getInventory().getItemCount("riceTile");
        if(remainingVillageCount<1){
            System.out.println("No rice tiles left! Broke the rules!");
            //then do thingie to notify player that they broke the rules
        }
        else {
        //TODO I assume boardController has a sort of placeTile() method
            gameController.getInventory().setItemCount("riceTile", remainingVillageCount - 1);
            Space s = boardController.getSpaceFromID(location);
            TileComponent tc = new TileComponent(new LandType("rice"), new Tile());
            s.addTileComponent(tc);
            return true;
        }
        return false;
    }
    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        gameController.getInventory().setItemCount("riceTile", gameController.getInventory().getItemCount("riceTile") + 1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }
    public void setLocation(int newLocation){
        this.location = newLocation;
    }
    public int getLocation(int l){
        return location;
    }
}
