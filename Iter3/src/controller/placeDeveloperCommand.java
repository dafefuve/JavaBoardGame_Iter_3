package controller;
/*
 * Created by Will
 */
public class placeDeveloperCommand extends Command {
	private BoardController boardController;

	public placeDeveloperCommand(BoardController boardController){
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
