package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceOneBlockActionPlanning {
    private Facade facade;

    public PlaceOneBlockActionPlanning(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //Facade.getViewController().placeOneBlock();
        Command c = facade.getCommandController().placeOneBlockCommand(facade.getBoardController());
        facade.getCommandStackController().push(c);
    }
}
