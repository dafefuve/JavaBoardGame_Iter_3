package controller.commands;

import controller.BoardController;
import controller.PlayerController;
import controller.Command;
import model.Developer;

/*
 * Created by Will
 */
public class MoveDeveloperCommand extends MovableCommands {
	private BoardController boardController;
	private PlayerController playerController;
	private int apAmount;
	private int destination;
	private int origin;

	public MoveDeveloperCommand(BoardController boardController, PlayerController playerController, Developer d){
		this.boardController=boardController;
		this.playerController=playerController;
	}

	public boolean execute(){	
	/*
	FindShortestPath will decrement the players AP by how many times the developer changes land type
	//TODO a method that finds the space that the cursor is currently hovering over
		apAmount=boardController.getAPForShortestPath();
		d.setLocation(destination);
	*/
		return true;
	}
	public void undo(){
		/*
		d.setLocation(origin);
		*/
	}
	public String toString(){
        return null;
	}
	public void setLocation(int l){
        destination=l;
    }
    public int getLocation(int l){
        return destination;
    }
}
