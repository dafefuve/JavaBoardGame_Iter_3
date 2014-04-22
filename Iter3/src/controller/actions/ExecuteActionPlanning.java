package controller.actions;

import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/22/14.
 */
public class ExecuteActionPlanning extends AbstractAction{
    private Facade facade;

    public ExecuteActionPlanning(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {   if(facade.getTempCommand().getTempCommand()!=null){ //if there is a command loaded then do this
        int spaceId = facade.getViewController().getCurrentSpace();
        facade.getTempCommand().setLocation(spaceId);
        if(facade.getTempCommand().execute()){
            facade.getPlanningController().pushIntoPlanningStack(facade.getTempCommand().getTempCommand());
        }
    }
    }
}
