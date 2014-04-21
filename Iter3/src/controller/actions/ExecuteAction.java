package controller.actions;

import controller.Command;
import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/20/14.
 */
public class ExecuteAction extends AbstractAction{
    private Facade facade;

    public ExecuteAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        facade.getViewController().endPlacement();
        int spaceId = facade.getViewController().getCurrentSpace();
        facade.getTempCommand().execute(spaceId);
    }
}
