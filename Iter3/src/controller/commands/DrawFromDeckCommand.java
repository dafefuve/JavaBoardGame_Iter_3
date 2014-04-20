package controller.commands;

import controller.Command;

/*
 * Created by Will
 */
public class DrawFromDeckCommand extends Command {
	//private GameController gameController;
	//private PlayerController playerController;
	//private PalaceCard card;
	public DrawFromDeckCommand(){
		//GameController gameInventory 
		//this.playerController=playerController;
		//this.gameController=gameController;
	}

	public boolean execute(){
		//Decrement the players AP by 1
		//card = gameInventory.drawCardFromDeck();
		//playerController.addPalaceCard(card);
		return true;

	}
	public void undo(){
		//increments the players AP by 1
		//playerController.removePalaceCard(card);
	}

	public String toString(){
        return null;
	}
}