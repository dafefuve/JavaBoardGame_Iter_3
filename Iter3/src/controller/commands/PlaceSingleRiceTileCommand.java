package controller.commands;

import controller.BoardController;
import controller.Facade;
import controller.PlayerController;
import model.LandType;
import model.Space;
import model.Tile;
import model.TileComponent;

/*
 * Created by Will
 */
public class PlaceSingleRiceTileCommand extends MovableCommands {
    private BoardController boardController;
    private PlayerController playerController;
    private Facade facade;
    private int location;
    public PlaceSingleRiceTileCommand(Facade gameFacade){
        boardController = gameFacade.getBoardController();
        playerController = gameFacade.getPlayerController();
        commandCompletion = false;
        facade = gameFacade;
        facade.getPlayerController().setCurrentPlayer();
    }
    public void setLocation(int newLocation){
        this.location = newLocation;
    }


    public boolean execute(){

        int remainingRiceCount = playerController.getInventory().getItemCount("riceTile");
        Space space = boardController.getSpaceFromID(location);
        TileComponent topTileComponentOfSpace = space.getTopTileComponent();
        TileComponent riceToBePlaced = new TileComponent(new LandType("riceTile"), new Tile());
        int currentActionPoints = playerController.getItemCount("actionPoints");
        if(currentActionPoints > 0) {

            if (remainingRiceCount > 0 && boardController.checkDifferentOneSpaceBeneath(space) && !topTileComponentOfSpace.getLandType().equals("highland") && !topTileComponentOfSpace.getLandType().equals("lowland") && !topTileComponentOfSpace.getLandType().equals("irrigation") && space.getDeveloper() == null && space.getPalace() == null) {
                System.out.print("got into placing a tile");
                playerController.setItemCount("riceTile", remainingRiceCount - 1);
                playerController.setItemCount("actionPoints", currentActionPoints -1 );
                space.addTileComponent(riceToBePlaced);
                String name = facade.getTurnController().getCurrentPlayer().getName();
                facade.getViewController().setRiceBlocks(remainingRiceCount - 1, name);
                commandCompletion = true;
            } else {
                commandCompletion = false;
            }
        }
        else {
            commandCompletion = false;
        }
        return commandCompletion;
    }
    public void undo(){
        //TODO implement a method in BoardController that removes a developer/block/tile from a selected space
        //The aforementioned method could be a general method that "purges" the space and completely removes any piece/top tile on it, or a method that removes a piece on the board
        playerController.getInventory().setItemCount("riceTile", playerController.getInventory().getItemCount("riceTile") + 1);
        playerController.getInventory().setItemCount("actionPoints", playerController.getInventory().getItemCount("actionPoints") + 1);
        boardController.getSpaceFromID(location).removeTopTileComponent();
    }

    public String toString(){
        return null;
    }
    public int getLocation(int l){
        return location;
    }
}
