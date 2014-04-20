package controller;

/*
 * Created by Will
 */
public abstract class Command {
	/*
	Needs someway to get the commandstack that it will be saving to
	*/
	public boolean execute(){
		return false;
	}
	public void undo(){}
	public void saveToStack(){}
}
