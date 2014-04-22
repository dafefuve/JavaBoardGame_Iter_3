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

    public PlaceTwoBlockCommand placeTwoBlockCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceTwoBlockCommand command = new PlaceTwoBlockCommand(boardController);
        //return command;
        return null;
    }

    public PlaceThreeBlockCommand placeThreeBlockCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceThreeBlockCommand command = new PlaceThreeBlockCommand(boardController);
        //return command;
        return null;
    }

    public PlaceSingleVillageTileCommand placeVillageTileCommand(Facade facade){
        //TODO this needs to be done, constructor needs the proper call
        PlaceSingleVillageTileCommand command = new PlaceSingleVillageTileCommand(facade);
        return command;
        //return null;
    }

    public PlaceIrrigationTileCommand placeIrrigationTileCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceIrrigationTileCommand command = new PlaceIrrigationTileCommand(boardController);
        //return command;
        return null;
    }

    public PlaceRiceTileCommand placeRiceTileCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceRiceTileCommand command = new PlaceRiceTileCommand(boardController);
        //return command;
        return null;
    }

    public PlaceDeveloperCommand placeDeveloperCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlaceDeveloperCommand command = new PlaceDeveloperCommand(boardController);
        //return command;
        return null;
    }

    public PlacePalaceTileCommand placePalaceTileCommand(BoardController boardController){
        //TODO this needs to be done, constructor needs the proper call
        //PlacePalaceTileCommand command = new PlacePalaceTileCommand(boardController);
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
