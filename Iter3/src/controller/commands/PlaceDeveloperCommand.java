package controller.commands;

import controller.*;
import model.*;

/*
 * Created by Will
 */
public class PlaceDeveloperCommand extends MovableCommands {
	private BoardController boardController;
	private PlayerController playerController;
	private int location;
	Developer d;
	//private Space s;
	//SO WE NEED COORDINATES INSTEAD OF A SPACE

	public PlaceDeveloperCommand(Facade f){
		boardController=f.getBoardController();
		playerController=f.getPlayerController();
		d = new Developer(f.getTurnController().getCurrentPlayer());
	}

	public boolean execute(){
	
	int count = playerController.getItemCount("developer");
	if(count<=0){
		System.out.println("No developers left! Broke the rules!");
		//then do thingie to notify player that they broke the rules
	}
	else {
		playerController.setItemCount("developer", count-1);
		Space s = boardController.getSpaceFromID(location);
		s.setHasDeveloper(true);
		d.setSpaceID(location);
		playerController.addDeveloper(d);
		return true;
	}
	
		return true;
	}
	public void undo(){
		//The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
		playerController.setItemCount("developer", playerController.getItemCount("developer")+1);
		Space s = boardController.getSpaceFromID(location);
		s.setHasDeveloper(false);
		playerController.removeDeveloper(d);
	}

    @Override
    public void setLocation(int newLocation) {
        //TODO
    }
    public int getLocation(){
        return location;
    }
    public String toString(){
        return null;
	}
}
