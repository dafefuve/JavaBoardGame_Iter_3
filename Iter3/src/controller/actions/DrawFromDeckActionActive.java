package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class DrawFromDeckActionActive {
    private Facade facade;

    public DrawFromDeckActionActive(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeOneBlock();
        Command c = facade.getCommandController().DrawFromDeckCommand(facade.getBoardController());
        facade.getCommandStackController().push(c);
        facade.getCommandController().execute(c);
    }
}
