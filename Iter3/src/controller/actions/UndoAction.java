package controller.actions;

import controller.Facade;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/22/14.
 */
public class UndoAction extends AbstractAction{
    private Facade facade;

    public UndoAction(Facade facade)
    {
        this.facade = facade;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
