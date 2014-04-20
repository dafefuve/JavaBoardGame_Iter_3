package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.GameController;

public class UpgradePalaceCommand {
	private int level;//the amount by which you wish to upgrade
	public UpgradePalaceCommand(int level){
		this.level=level;
	}
	public boolean execute(){
		/*
		//TODO a method that finds the space that the cursor is currently hovering over
		*/
		return false;
	}
	public void undo(){

	}
}