package controller.actions;

import controller.Facade;
import controller.GameStateManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/18/14.
 */
public class NewGameAction extends AbstractAction{

    private GameStateManager g;
    private Facade f;

    public NewGameAction(GameStateManager g, Facade f)
    {
        this.g = g;
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       g.setState(g.getActiveState());
       f.getViewController().startNewGame();
       System.out.println("frehialdo;pas");

    }
}
