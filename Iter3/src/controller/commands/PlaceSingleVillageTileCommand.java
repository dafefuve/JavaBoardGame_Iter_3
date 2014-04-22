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
    //private Space s;
    private int location;
    public PlaceSingleVillageTileCommand(Facade facade){

        this.boardController=facade.getBoardController();
        this.playerController=facade.getPlayerController();
        facade.getPlayerController().setCurrentPlayer(facade.getGameController().getPlayers().get(0));
    }

    public boolean execute(){
        int remainingVillageCount = playerController.getItem("villageTile");
        Space space = boardController.getSpaceFromID(location);

        TileComponent topTileComponentOfSpace = space.getTopTileComponent();             //board tile
        TileComponent villageToBePlaced = new TileComponent(new LandType("village"), new Tile());  //village tile

        if(topTileComponentOfSpace != null && (remainingVillageCount < 0 || villageToBePlaced.sameType(topTileComponentOfSpace)|| space.getDeveloper()!=null || space.getPalace()!=null || villageToBePlaced.getLandType().equals("irrigation"))){

        }
        else {
            playerController.setItem("villageTile", remainingVillageCount - 1);
            space.addTileComponent(villageToBePlaced);
            return true;
        }
        return false;
    }
    public void undo(){
        playerController.setItem("villageTile", playerController.getItem("villageTile") + 1);
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
