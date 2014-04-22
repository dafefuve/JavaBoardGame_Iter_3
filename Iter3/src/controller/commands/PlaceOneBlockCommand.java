package controller.commands;

import controller.BoardController;
import controller.GameController;
//TODO might want wo delete this class
/*
 * Created by Will
 */
public class PlaceOneBlockCommand extends MovableCommands {
 	private BoardController boardController;
    private GameController gameController;
    //private Space s;
    private int location;
    public PlaceOneBlockCommand(BoardController boardController, GameController gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public boolean execute(){
     /*
    int count = gameController.getItemCount("oneBlock");
    if(count<=0){
        System.out.println("No one blocks left! Broke the rules!");
        //then do thingie to notify player that they broke the rules
    }
    else {
        gameController.setItemCount("oneBlock", count-1);
        s = boardController.getSelectedSpace();
        boardController.placeBlock(s);
        return true;
    }
    */
        return true;
    }
    public void undo(){
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        /*gameController.setItemCount("oneBlock", gameController.getItemCount("oneBlock")-1);
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
