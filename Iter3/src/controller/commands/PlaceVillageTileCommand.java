package controller.commands;

import controller.*;
import model.TileComponent;
import model.LandType;
import model.Tile;
import model.Space;

/*
 * Created by Will
 */
public class PlaceVillageTileCommand extends MovableCommands {
    private BoardController boardController;
    private PlayerController playerController;
    //private Space s;
    private int location;
    public PlaceVillageTileCommand(Facade facade){

        this.boardController=facade.getBoardController();
        this.playerController=facade.getPlayerController();
        facade.getPlayerController().setCurrentPlayer(facade.getGameController().getPlayers().get(0));
    }

    public boolean execute(){
        int count = playerController.getItem("villageTile");
        Space s = boardController.getSpaceFromID(location);

        TileComponent bt = s.getTopTileComponent();             //board tile
        TileComponent vt = new TileComponent(new LandType("village"), new Tile());  //village tile

        if(bt!=null && (count<0 || vt.sameType(bt)|| s.getDeveloper()!=null || s.getPalace()!=null || vt.getLandType().equals("irrigation"))){

        }
        else {
            playerController.setItem("villageTile", count - 1);
            s.addTileComponent(vt);
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
