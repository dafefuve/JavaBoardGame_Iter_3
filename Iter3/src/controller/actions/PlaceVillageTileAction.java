package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class PlaceVillageTileAction {
    private Facade facade;

    public PlaceVillageTileAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placeVillageTile();
        //Command c = facade.getCommandController().placeVillageTileCommand(facade.getBoardController(), facade.getGameController());
        /*facade.getTempCommand().setTempCommand(c);
        if(c.execute())
           facade.getCommandStackController().push(c);
        else
            //facade.getViewController().undo();*/
    }
}
