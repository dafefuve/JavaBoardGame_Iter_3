package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class MoveDeveloperAction {
    private Facade facade;

    public MoveDeveloperAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().moveDeveloper();
        Command c = facade.getCommandController().moveDeveloperCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);
    }
}
