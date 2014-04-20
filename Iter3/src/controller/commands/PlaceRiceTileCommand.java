package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.GameController;

/*
 * Created by Will
 */
public class PlaceRiceTileCommand extends Command {
    private BoardController boardController;
    private GameController gameController;
    //private Space s;
    private int location;
    public PlaceRiceTileCommand(BoardController boardController, GameController gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public boolean execute(){
    //TODO a method that finds the space that the cursor is currently hovering over
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
        //TODO I assume there is a method in gameController that accesses the communal inventory
    /*
    int count = gameController.getItem("riceTile");
    if(count<=0){
        System.out.println("No rice tiles left! Broke the rules!");
        //then do thingie to notify player that they broke the rules
    }
    else {
    //TODO I assume boardController has a sort of placeTile() method
        gameController.setItem("riceTile", count-1);
        s = boardController.getSelectedSpace();
        boardController.placeTile(s);
        return true;
    }
    */
        return true;
    }
    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        /*gameController.setItem("riceTile", gameController.getItem("riceTile")-1);
        boardController.removeTile(s);*/
    }

    public String toString(){
        return null;
    }
    public void setLocation(int l){
        location=l;
    }
    public int getLocation(int l){
        return location;
    }
}
