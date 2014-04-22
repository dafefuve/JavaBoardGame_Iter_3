package controller.commands;

import controller.BoardController;
import controller.PlayerController;

/*
 * Created by Will
 */
public class PlaceDeveloperCommand extends MovableCommands {
	private BoardController boardController;
	private PlayerController playerController;
	//private Space s;
	//SO WE NEED COORDINATES INSTEAD OF A SPACE
	public PlaceDeveloperCommand(BoardController boardController, PlayerController playerController){
		this.boardController=boardController;
		this.playerController=playerController;
	}

	public boolean execute(){
	/*
	int count = playerController.getItemCount("developer");
	if(count<=0){
		System.out.println("No developers left! Broke the rules!");
		//then do thingie to notify player that they broke the rules
	}
	else {
		playerController.setItemCount("developer", count-1);
		s = boardController.getSelectedSpace();
		boardController.placeDeveloper(s);
		return true;
	}
	*/
		return true;
	}
	public void undo(){
	/*
		//The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
		playerController.setItemCount("developer", playerController.getItemCount("developer");+1);
		boardController.removeDeveloper(s);

	*/
	}

    @Override
    public void setLocation(int newLocation) {
        //TODO
    }

    public String toString(){
        return null;
	}
}
