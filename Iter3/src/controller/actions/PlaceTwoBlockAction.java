package controller.actions;

import controller.Command;
import controller.Facade;
import controller.commands.MovableCommands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceTwoBlockAction extends AbstractAction{

    private Facade facade;

    public PlaceTwoBlockAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(facade.getTempCommand().getTempCommand()==null){
            facade.getViewController().placeTwoBlock();
            MovableCommands c = facade.getCommandController().placeTwoBlockCommand(facade.getBoardController());
            facade.getTempCommand().setTempCommand(c);
        }
    }
}
