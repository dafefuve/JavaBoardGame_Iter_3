package controller.actions;

import controller.Facade;
import controller.GameStateManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/22/14.
 */
public class ExitPlanningAction extends AbstractAction {
    private GameStateManager gameStateManager;
    private Facade facade;

    public ExitPlanningAction(GameStateManager g, Facade f)
    {
        gameStateManager = g;
        facade = f;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Go back to active mode
        facade.getPlanningController().exitPlanning();
        gameStateManager.setState(gameStateManager.getActiveState());
    }
}
