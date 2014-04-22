package controller.actions;

import controller.Facade;
import controller.GameStateManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/22/14.
 */
public class PlanningModeAction extends AbstractAction {
    private GameStateManager gameStateManager;
    private Facade facade;

    public PlanningModeAction(GameStateManager g, Facade f)
    {
        gameStateManager = g;
        facade = f;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Change state to planning mode
        gameStateManager.setState(gameStateManager.getPlanningState());

        //Change label in view to show planning mode
        facade.getViewController().setPlanningMode();
    }
}
