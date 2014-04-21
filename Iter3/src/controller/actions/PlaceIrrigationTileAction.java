package controller.actions;

import controller.Command;
import controller.Facade;
import controller.commands.MovableCommands;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceIrrigationTileAction {
    private Facade facade;

    public PlaceIrrigationTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeIrrigationTile();
        MovableCommands c = facade.getCommandController().placeIrrigationTileCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);
    }
}
