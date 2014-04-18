package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceTwoBlockActionActive {

    private Facade facade;

    public PlaceTwoBlockActionActive(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        Facade.getViewController().placeOneBlock();
        Command c = Facade.getCommandController().placeTwoBlockCommand();
        CommandStack.add(c);
        Facade.getCommandController().execute(c);



    }
}
