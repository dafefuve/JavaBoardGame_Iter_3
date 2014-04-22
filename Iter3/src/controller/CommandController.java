package controller;

import controller.commands.*;

/**
 * Created by Horacio on 4/18/14.
 */
public class CommandController {
    public CommandController(){
    }
    public PlaceOneBlockCommand placeOneBlockCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceOneBlockCommand command = new PlaceOneBlockCommand(boardController);
        // return command;
        return null;
    }

    public PlaceTwoTileCommand placeTwoBlockCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceTwoTileCommand command = new PlaceTwoTileCommand(boardController);
        //return command;
        return null;
    }

    public PlaceThreeTileCommand placeThreeBlockCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceThreeTileCommand command = new PlaceThreeTileCommand(boardController);
        //return command;
        return null;
    }

    public PlaceSingleVillageTileCommand placeVillageTileCommand(Facade facade){
        //TODO this needs to be done, constructor needs the proper call
        PlaceSingleVillageTileCommand command = new PlaceSingleVillageTileCommand(facade);
        return command;
        //return null;
    }

    public PlaceSingleIrrigationTileCommand placeIrrigationTileCommand(Facade facade){
        //TODO this needs to be done, constructor needs the proper call
        PlaceSingleIrrigationTileCommand command = new PlaceSingleIrrigationTileCommand(facade);
        return command;
    }

    public PlaceSingleRiceTileCommand placeRiceTileCommand(Facade facade){
        PlaceSingleRiceTileCommand command = new PlaceSingleRiceTileCommand(facade);
        return command;
    }

    public PlaceDeveloperCommand placeDeveloperCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceDeveloperCommand command = new PlaceDeveloperCommand(boardController);
        //return command;
        return null;
    }

    public PlaceSinglePalaceTileCommand placePalaceTileCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceSinglePalaceTileCommand command = new PlaceSinglePalaceTileCommand(boardController);
        //return command;
        return null;
    }

    public MoveDeveloperCommand moveDeveloperCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //MoveDeveloperCommand command = new MoveDeveloperCommand(boardController);
        //return command;
        return null;
    }

    public DrawFromDeckCommand DrawFromDeckCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        // DrawFromDeckCommand command = new DrawFromDeckCommand();
        //return command;
        return null;
    }

    //todo drawCardCommand

    public void execute(Command command){
        command.execute();
    }






}
