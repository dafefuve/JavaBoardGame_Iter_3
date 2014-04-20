package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlacePalaceTileAction {
    private Facade facade;

    public PlacePalaceTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placePalaceTile();
        Command c = facade.getCommandController().placePalaceTileCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);
    }
}
