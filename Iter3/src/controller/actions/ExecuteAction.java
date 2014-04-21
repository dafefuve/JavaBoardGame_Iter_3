package controller.actions;

import controller.Command;
import controller.Facade;

import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/20/14.
 */
public class ExecuteAction {
    private Facade facade;

    public ExecuteAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //facade.getViewController().placePalaceTile();
        int spaceId = facade.getViewController().getCurrentSpace();
        facade.getTempCommand().execute(spaceId);
    }
}
