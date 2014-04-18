package controller;

/**
 * Created by Horacio on 4/18/14.
 */
public class CommandController {

    public placeOneBlockCommand placeOneBlockCommand(BoardController boardController){
        placeOneBlockCommand command = new placeOneBlockCommand(boardController);
        return command;
    }

    public placeTwoBlockCommand placeTwoBlockCommand(BoardController boardController){
        placeTwoBlockCommand command = new placeTwoBlockCommand(boardController);
        return command;
    }

    public placeThreeBlockCommand placeThreeBlockCommand(BoardController boardController){
        placeThreeBlockCommand command = new placeThreeBlockCommand(boardController);
        return command;
    }

    public placeVillageTileCommand placeVillageTileCommand(BoardController boardController){
        placeVillageTileCommand command = new placeVillageTileCommand(boardController);
        return command;
    }

    public placeIrrigationTileCommand placeIrrigationTileCommand(BoardController boardController){
        placeIrrigationTileCommand command = new placeIrrigationTileCommand(boardController);
        return command;
    }

    public placeRiceTileCommand placeRiceTileCommand(BoardController boardController){
        placeRiceTileCommand command = new placeRiceTileCommand(boardController);
        return command;
    }

    public placeDeveloperCommand placeDeveloperCommand(BoardController boardController){
        placeDeveloperCommand command = new placeDeveloperCommand(boardController);
        return command;
    }

    public placePalaceTileCommand placePalaceTileCommand(BoardController boardController){
        placePalaceTileCommand command = new placePalaceTileCommand(boardController);
        return command;
    }

    public moveDeveloperCommand moveDeveloperCommand(BoardController boardController){
        moveDeveloperCommand command = new moveDeveloperCommand(boardController);
        return command;
    }

    public DrawPalaceCardCommand DrawPalaceCardCommand(BoardController boardController){
        DrawPalaceCardCommand command = new DrawPalaceCardCommand();
        return command;
    }

    //todo drawCardCommand

    public void execute(Command command){
        command.execute();
    }






}
