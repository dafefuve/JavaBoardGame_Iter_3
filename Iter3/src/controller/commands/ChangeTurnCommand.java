package controller.commands;

import controller.BoardController;
import controller.Command;

/*
 * Created by Will
 */
public class ChangeTurnCommand extends Command {
	private BoardController boardController;

	public ChangeTurnCommand(){
		//this.boardController=boardController;
	}

	public boolean execute(){
	//	Player p playerController.getCurrentPlayer();
		return true;
	}
	public void undo(){

	}

	public void saveToStack(){

	}
	public String toString(){
	    return "TODO this needs to be done change turn command";
    }
}
