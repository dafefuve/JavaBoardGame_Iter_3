package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.Facade;
import controller.GameController;
/*
*created by Will
*/
public class UpgradePalaceCommand {
	private int level;//the amount by which you wish to upgrade
	private Facade f;
	public UpgradePalaceCommand(int level, Facade f){
		this.level=level;
		this.f=f;
	}
	public boolean execute(){
		if(true){
		  //so here we need to check if the current player has the right to build a palace... if they are the player
          //with the highest game piece or the player with the sequence of highest game pieces. Ties do not count
        	return true;
        }
		return false;
	}
	public void undo(){

	}
}