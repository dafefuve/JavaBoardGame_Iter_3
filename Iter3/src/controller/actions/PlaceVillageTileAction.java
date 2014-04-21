package controller.actions;

import controller.Command;
import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceVillageTileAction extends AbstractAction{
    private Facade facade;

    public PlaceVillageTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {

        Command c = facade.getCommandController().placeVillageTileCommand(facade.getBoardController(), facade.getGameController());
        facade.getTempCommand().setTempCommand(c);
    }
}
