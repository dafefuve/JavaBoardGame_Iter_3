package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.GameController;
import model.TileComponent;
import model.LandType;
import model.Palace;
import model.Tile;
import model.Space;

/*
 * Created by Will
 */
public class PlacePalaceTileCommand extends Command {
    private BoardController boardController;
    private GameController gameController;
    //private Space s;
    private int location;
    private int level;
    public PlacePalaceTileCommand(BoardController boardController, GameController gameController, int level){
        this.boardController=boardController;
        this.gameController=gameController;
        this.level=level;
    }

    public boolean execute(){
    //TODO a method that finds the space that the cursor is currently hovering over
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
    //TODO I assume there is a method in gameController that accesses the communal inventory
        int count = gameController.getInventory().getItem("palaceTile");
        if(count<=0){
            System.out.println("No palace tiles left! Broke the rules!");
            //then do thingie to notify player that they broke the rules
        }
        else {
        //TODO I assume boardController has a sort of placeTile() method
            gameController.getInventory().setItem("palaceTile", count-1);
            Space s = boardController.getSpaceFromID(location);
            TileComponent tc = new TileComponent(new LandType("palace"), new Tile());
            s.addTileComponent(tc);
            s.setPalace(new Palace(level));
            return true;
        }
        return false;
    }
    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        gameController.getInventory().setItem("palaceTile", gameController.getInventory().getItem("palaceTile")+1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }
    public void setLocation(int l){
        location=l;
    }
    public int getLocation(int l){
        return location;
    }
}