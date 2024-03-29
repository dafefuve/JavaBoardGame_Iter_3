package controller.actions;

import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/17/14.
 */
public class ChangeTurnAction extends AbstractAction
{

    private Facade facade;

    public ChangeTurnAction(Facade facade)
    {
        this.facade = facade;
    }


    public void actionPerformed(ActionEvent e)
    {
        facade.getViewController().changeTurn();
        facade.getTurnController().changeTurn();
    }

}
