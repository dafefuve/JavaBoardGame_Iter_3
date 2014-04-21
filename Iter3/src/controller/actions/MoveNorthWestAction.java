package controller.actions;

import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class MoveNorthWestAction extends AbstractAction{
    private Facade f;

    public MoveNorthWestAction(Facade f)
    {
        this.f = f;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        f.getViewController().moveNorthWest();
    }
}
