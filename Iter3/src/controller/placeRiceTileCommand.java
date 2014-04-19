package controller;
/*
 * Created by Will
 */
public class PlaceRiceTileCommand extends Command {
    private BoardController boardController;
    private GameComtroller gameController;
    //private Space s;
    public PlaceRiceTileCommand(BoardController boardController, GameComtroller gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public void execute(){
    //TODO add a method in BoardController that returns the currently selected space
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
        //TODO I assume there is a method in gameController that accesses the communal inventory
    /*
    int count = gameController.getItem("riceTile");
    if(count<=0){
        System.out.println("No rice tiles left! Broke the rules!");
        //then do thingie to notify player that they broke the rules
    }
    else {
    //TODO I assume boardController has a sort of placeTile() method
        gameController.setItem("riceTile", count-1);
        s = boardController.getSelectedSpace();
        boardController.placeTile(s);
        saveToStack();
    }
    */
    }
    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        /*gameController.setItem("riceTile", gameController.getItem("riceTile")-1);
        boardController.removeTile(s);*/
    }

    public void saveToStack(){

    }
    public String toString(){
        return null;
    }
}
