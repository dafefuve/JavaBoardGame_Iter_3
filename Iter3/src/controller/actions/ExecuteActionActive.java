package controller.actions;

import controller.Command;
import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/20/14.
 */
public class ExecuteActionActive extends AbstractAction{
    private Facade facade;

    public ExecuteActionActive(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {   if(facade.getTempCommand().getTempCommand()!=null){ //if there is a command loaded then do this
            int spaceId = facade.getViewController().getCurrentSpace();
            facade.getTempCommand().setLocation(spaceId);
            if(facade.getTempCommand().execute()){
                facade.getCommandStackController().push(facade.getTempCommand().getTempCommand());
            }
        }
    }
}
