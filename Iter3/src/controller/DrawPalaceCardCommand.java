package controller;
import src.Model;
/*
 * Created by Will
 */
public class DrawPalaceCardCommand extends Command {
	//private GameController gameController;
	//private PlayerController playerController;
	//private PalaceCard card;
	public DrawPalaceCardCommand(){
		//GameController gameInventory 
		//this.playerController=playerController;
		//this.gameController=gameController;
	}

	public void execute(){
		//card = gameInventory.drawCardFromDeck();
		//playerController.addPalaceCard(card);
		this.saveToStack();
	}
	public void undo(){
		//playerController.removePalaceCard(card);
	}

	public void saveToStack(){
		//c.add(this);
	}

	public String toString(){

	}
}