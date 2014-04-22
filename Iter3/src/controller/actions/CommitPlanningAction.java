package controller.actions;


import controller.Facade;
import controller.GameStateManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/22/14.
 */
public class CommitPlanningAction extends AbstractAction {
    private GameStateManager gameStateManager;
    private Facade facade;

    public CommitPlanningAction(GameStateManager g, Facade f)
    {
        gameStateManager = g;
        facade = f;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        facade.getPlanningController().commitPlanning();
        gameStateManager.setState(gameStateManager.getActiveState());
    }
}
