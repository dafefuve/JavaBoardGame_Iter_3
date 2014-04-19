package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceThreeBlockActionPlanning {
    private Facade facade;

    public PlaceThreeBlockActionPlanning(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeOneBlock();
        Command c = facade.getCommandController().placeThreeBlockCommand(facade.getBoardController());
        facade.getCommandStackController().push(c);

    }
}
