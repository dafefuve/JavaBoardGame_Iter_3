package controller.actions;

import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class MoveWestAction extends AbstractAction{
    private Facade f;

    public MoveWestAction(Facade f)
    {
        this.f = f;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //f.getViewController().moveWest();
    }
}
