package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceDeveloperActionActive {
    private Facade facade;

    public PlaceDeveloperActionActive(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeOneBlock();
        Command c = facade.getCommandController().placeDeveloperCommand(facade.getBoardController());
        facade.getCommandStackController().push(c);
        facade.getCommandController().execute(c);
    }
}
