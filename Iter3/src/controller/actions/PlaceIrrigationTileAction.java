package controller.actions;

import controller.Facade;
import controller.commands.MovableCommands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceIrrigationTileAction extends AbstractAction{
    private Facade facade;

    public PlaceIrrigationTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(facade.getTempCommand().getTempCommand()==null){

            MovableCommands c = facade.getCommandController().placeIrrigationTileCommand(facade);
            facade.getTempCommand().setTempCommand(c);
        }
    }
}
