package controller.commands;

import controller.BoardController;
import controller.Command;

/*
 * Created by Will
 */
public class MoveDeveloperCommand extends Command {
	private BoardController boardController;
	/*
	private Space a;
	private Space b;
	*/
	public MoveDeveloperCommand(BoardController boardController){
		this.boardController=boardController;
	}

	public boolean execute(){	
	/*
	FindShortestPath will decrement the players AP by how many times the developer changes land type
	//TODO a method that finds the space that the cursor is currently hovering over

	*/
		return true;
	}
	public void undo(){

	}
	public String toString(){
        return null;
	}
}
