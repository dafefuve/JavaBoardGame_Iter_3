package controller.actions;

import controller.Command;
import controller.Facade;

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
        //facade.getViewController().placeDeveloper();
        Command c = facade.getCommandController().placeDeveloperCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);
    }
}
