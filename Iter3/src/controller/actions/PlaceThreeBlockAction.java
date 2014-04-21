package controller.actions;

import controller.Command;
import controller.Facade;
import controller.commands.MovableCommands;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceThreeBlockAction {
    private Facade facade;

    public PlaceThreeBlockAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeThreeBlock();
        MovableCommands c = facade.getCommandController().placeThreeBlockCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);
    }
}
