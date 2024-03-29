package controller.actions;

import controller.Command;
import controller.Facade;
import controller.commands.MovableCommands;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceDeveloperAction {
    private Facade facade;

    public PlaceDeveloperAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(facade.getTempCommand().getTempCommand()==null){
            //facade.getViewController().placeDeveloper();
            MovableCommands c = facade.getCommandController().placeDeveloperCommand(facade);
            facade.getTempCommand().setTempCommand(c);
        }
    }
}
