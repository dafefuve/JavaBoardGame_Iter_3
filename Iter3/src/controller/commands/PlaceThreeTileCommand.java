package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.GameController;

/*
 * Created by Will
 */
public class PlaceThreeTileCommand extends MovableCommands {
	private BoardController boardController;
    private GameController gameController;
    //private Space s;
    private int location;
    public PlaceThreeTileCommand(BoardController boardController, GameController gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public boolean execute(){
    //TODO a method that finds the space that the cursor is currently hovering over
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
    //TODO I assume there is a method in gameController that accesses the communal inventory
    /*
    int count = gameController.getItemCount("threeBlock");
    if(count<=0){
        System.out.println("No three blocks left! Broke the rules!");
        //then do thingie to notify player that they broke the rules
    }
    else {
    //TODO I assume boardController has a sort of placeTile() method
        gameController.setItemCount("threeBlock", count-1);
        s = boardController.getSelectedSpace();
        boardController.placeBlock(s);
        return true;
    }
    */
        return true;
    }

    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        /*gameController.setItemCount("threeBlock", gameController.getItemCount("threeBlock")-1);
        boardController.removeBlock(s);*/
    }
    
    public String toString(){
        return null;
    }
    public void setLocation(int newLocation){
        this.location = newLocation;
    }
    public int getLocation(){
        return location;
    }
}

