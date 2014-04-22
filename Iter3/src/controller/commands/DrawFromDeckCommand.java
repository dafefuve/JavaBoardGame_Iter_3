package controller.commands;

import controller.Command;
import controller.*;
import model.*;

/*
 * Created by Will
 */
public class DrawFromDeckCommand extends Command {
	private GameController gameController;
	private PlayerController playerController;
	private PalaceCard card;
	public DrawFromDeckCommand(Facade f){
		this.playerController=f.getPlayerController();
		this.gameController=f.getGameController();
	}

	public boolean execute(){
		//Decrement the players AP by 1
		playerController.setItemCount("famePoints", playerController.getItemCount("famePoints")-1);
		card = gameController.getInventory().drawCardFromDeck();
		playerController.addPalaceCard(card);
		return true;

	}
	public void undo(){
		playerController.setItemCount("famePoints", playerController.getItemCount("famePoints")+1);
		playerController.removePalaceCard(card);
		gameController.getInventory().putOnTopOfDeck(card);
	}

	public String toString(){
        return null;
	}
}