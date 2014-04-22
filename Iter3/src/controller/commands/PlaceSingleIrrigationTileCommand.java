package controller.commands;

import controller.BoardController;
import controller.GameController;
import model.TileComponent;
import model.LandType;
import model.Tile;
import model.Space;

/*
 * Created by Will
 */
public class PlaceSingleIrrigationTileCommand extends MovableCommands {
    private BoardController boardController;
    private GameController gameController;
    private Space s;
    private int location;
    public PlaceSingleIrrigationTileCommand(BoardController boardController, GameController gameController){
        this.boardController=boardController;
        this.gameController=gameController;
    }

    public boolean execute(){
    //TODO a method that finds the space that the cursor is currently hovering over
    //TODO add a method in BoardController that places a singular tile onto a space on the board 
    //TODO I assume there is a method in gameController that accesses the communal inventory
        int count = gameController.getInventory().getItemCount("irrigationTile");
        if(count<=0){
            System.out.println("No irrigation tiles left! Broke the rules!");
            //then do thingie to notify player that they broke the rules
        }
        else if(s.getLevel() != 0)
        {
            System.out.println("You're not placing on the board! Broke the rules!");
            //Here we will check if the target location is directly on the board, i.e the target space has no other tiles on it
        }
        else if(true){
            //It is illegal to place the tiles directly on the borders of the board, and here we check that
        }
        else {
            gameController.getInventory().setItemCount("irrigationTile", count - 1);
            Space s = boardController.getSpaceFromID(location);
            TileComponent tc = new TileComponent(new LandType("irrigation"), new Tile());
            s.addTileComponent(tc);
            return true;
        }
        return false;
    }
    public void undo(){
        gameController.getInventory().setItemCount("irrigationTile", gameController.getInventory().getItemCount("irrigationTile") + 1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }
    public void setLocation(int newLocation){
        this.location = newLocation;
    }
    public int getLocation(){
        return location;
    }
}
