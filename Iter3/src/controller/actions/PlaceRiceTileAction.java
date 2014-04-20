package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceRiceTileAction {
    private Facade facade;

    public PlaceRiceTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeRiceTile();
        Command c = facade.getCommandController().placeRiceTileCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);
    }
}
