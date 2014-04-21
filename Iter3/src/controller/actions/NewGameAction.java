package controller.actions;

import controller.GameStateManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class NewGameAction extends AbstractAction{

    private GameStateManager g;
    public NewGameAction(GameStateManager g)
    {
        this.g = g;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

       g.getActiveState();

    }
}
