package controller.commands;

import controller.BoardController;
import controller.Command;
import controller.GameController;
import model.TileComponent;
import model.LandType;
import model.Tile;
import model.Space;

/*
 * Created by Will
 */
public class PlaceVillageTileCommand extends MovableCommands {
    private BoardController boardController;
    private GameController gameController;
    //private Space s;
    private int location;
    public PlaceVillageTileCommand(BoardController boardController, GameController gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public boolean execute(){
    int count = gameController.getInventory().getItem("villageTile");
        if(count<=0){
            System.out.println("No village tiles left! Broke the rules!");
            //then do thingie to notify player that they broke the rules
        }
        else {
            gameController.getInventory().setItem("villageTile", count-1);
            Space s = boardController.getSpaceFromID(location);
            TileComponent tc = new TileComponent(new LandType("village"), new Tile());
            s.addTileComponent(tc);
            return true;
        }
        return false;
    }
    public void undo(){
        gameController.getInventory().setItem("villageTile", gameController.getInventory().getItem("villageTile")+1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }
    public void setLocation(int l){
        location=l;
    }
    public int getLocation(){
        return location;
    }
}
