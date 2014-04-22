package controller.commands;

import controller.*;
import model.TileComponent;
import model.LandType;
import model.Tile;
import model.Space;

/*
 * Created by Will
 */
public class PlaceSingleVillageTileCommand extends MovableCommands {
    private BoardController boardController;
    private PlayerController playerController;
    private int location;
    private Facade facade;

    public PlaceSingleVillageTileCommand(Facade facade){

        this.boardController=facade.getBoardController();
        this.playerController=facade.getPlayerController();
        facade.getPlayerController().setCurrentPlayer();
        commandCompletion = false;
        this.facade = facade;
    }
    //this must happen before execute
    public void setLocation(int newLocation){
        location = newLocation;
    }
    public boolean execute(){
        int remainingVillageCount = playerController.getItemCount("villageTile");
        Space space = boardController.getSpaceFromID(location);


        TileComponent topTileComponentOfSpace = space.getTopTileComponent();             //board til
        TileComponent villageToBePlaced = new TileComponent(new LandType("village"), new Tile());  //village tile
        int currentActionPoints = playerController.getItemCount("actionPoints");
        if(currentActionPoints > 0) {

            if (remainingVillageCount > 0 && !topTileComponentOfSpace.getLandType().equals("highland") && !topTileComponentOfSpace.getLandType().equals("lowland") && !topTileComponentOfSpace.getLandType().equals("irrigation") && space.getDeveloper() == null && space.getPalace() == null) {

                playerController.setItemCount("villageTile", remainingVillageCount - 1);
                space.addTileComponent(villageToBePlaced);
                String name = facade.getTurnController().getCurrentPlayer().getName();
                facade.getViewController().setSingleVillageBlocks(remainingVillageCount - 1, name);
//                if(boardController.willSurroundIrrigationTile(space))
//                    playerController.addFamePoints(space);

                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }
    public void undo(){
        playerController.setItemCount("villageTile", playerController.getItemCount("villageTile") + 1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }

    public int getLocation(){
        return location;
    }
}
