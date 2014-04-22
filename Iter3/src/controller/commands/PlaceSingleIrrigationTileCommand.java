package controller.commands;

import controller.BoardController;
import controller.Facade;
import controller.GameController;
import controller.PlayerController;
import model.LandType;
import model.Space;
import model.Tile;
import model.TileComponent;

/*
 * Created by Will
 */
public class PlaceSingleIrrigationTileCommand extends MovableCommands {
    private BoardController boardController;
    private PlayerController playerController;
    private GameController gameController;
    private Space space;
    private int location;
    private Facade facade;
    public PlaceSingleIrrigationTileCommand(Facade gameFacade){
        boardController = gameFacade.getBoardController();
        playerController = gameFacade.getPlayerController();
        gameController = gameFacade.getGameController();
        facade = gameFacade;
        commandCompletion = false;
        playerController.setCurrentPlayer();
    }
    public void setLocation(int newLocation){
       location = newLocation;
    }

    public boolean execute(){

        int remainingIrrigationCount = gameController.getItemCount("irrigationTile");
        space = boardController.getSpaceFromID(location);
        TileComponent topTileComponentOfSpace = space.getTopTileComponent();
        TileComponent irrigationToBePlaced = new TileComponent( new LandType("irrigation"), new Tile());
        int currentActionPoints = playerController.getItemCount("actionPoints");

        if(currentActionPoints > 0) {
            if (remainingIrrigationCount >= 0 && topTileComponentOfSpace.getLandType().equals("central"))
            {
                gameController.setItem("irrigation", remainingIrrigationCount - 1);
                space.addTileComponent(irrigationToBePlaced);
                commandCompletion = true;
            }
            else{
                commandCompletion = false;
            }
        }
        else {
            commandCompletion = false;
        }
        return commandCompletion;
    }
    public void undo(){
        gameController.getInventory().setItemCount("irrigationTile", gameController.getInventory().getItemCount("irrigationTile") + 1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }
    public int getLocation(){
        return location;
    }
}
