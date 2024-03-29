package controller.actions;

import controller.Command;
import controller.Facade;
import controller.commands.MovableCommands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceRiceTileAction extends AbstractAction{
    private Facade facade;

    public PlaceRiceTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(facade.getTempCommand().getTempCommand()==null){
            facade.getViewController().placeRiceTile();
            MovableCommands c = facade.getCommandController().placeRiceTileCommand(facade);
            facade.getTempCommand().setTempCommand(c);
        }
    }
}
