package controller;
/*
 * Created by Will
 */
public class placeVillageTileCommand extends Command {
	private BoardController boardController;

	public placeVillageTileCommand(BoardController boardController){
		this.boardController=boardController;
	}

    public void execute(){
        //	Player p playerController.getCurrentPlayer();
    }
    public void undo(){

    }

    public void saveToStack(){

    }
    public String toString(){
        return null;
    }
}
