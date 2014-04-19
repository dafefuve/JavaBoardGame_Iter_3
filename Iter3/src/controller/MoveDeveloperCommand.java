package controller;
/*
 * Created by Will
 */
public class MoveDeveloperCommand extends Command {
	private BoardController boardController;

	public MoveDeveloperCommand(BoardController boardController){
		this.boardController=boardController;
	}

	public void execute(){
	//	Player p playerController.getCurrentPlayer();
	}
	public void undo(){

	}
	public String toString(){
        return null;
	}
}
