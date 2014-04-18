package controller;

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
        Facade.getViewController().changeTurn();
        Facade.getTurnController().changeTurn();
    }
}
