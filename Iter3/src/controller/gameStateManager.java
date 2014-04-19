package controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Daniel on 4/17/2014.
 */
public class gameStateManager {
    //Different game states
    gameState active;
    gameState festival;
    gameState planning;
    gameState replay;
    gameState mainMenuState;

    //current state
    gameState currentState;

    /*
    active = new activeState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    festival = new festivalState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    planning = new planningState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    replay = new replayState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    */

    public gameStateManager()
    {
        //Default starting state is mainmenu
        currentState = new mainMenuState();
        currentState.bindKeys();
    }
}
interface gameState {
    //boolean changeTurn();
    void bindKeys();
}
class activeState implements gameState {
    ActiveController activeController;
    FestivalController festivalController;
    PlanningController planningController;
    ReplayController replayController;
    Facade gameFacade;
    public activeState(Facade facade, ActiveController aController, FestivalController fController, PlanningController pController, ReplayController rController)
    {
        gameFacade = facade;
        activeController = aController;
        festivalController = fController;
        planningController = pController;
        replayController = rController;
    }

    @Override
    public void bindKeys()
    {
        HashMap<KeyStroke, Action> activeActions = new HashMap<KeyStroke, Action>();
        //TODO GET THIS WORKING!!
        //Stores all the actions and their associated keys for activeMode, then calls Facade and passes this map
        //Let me know if I've missed any actions!!
        //Movement
        /*
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), new MoveNorthAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), new MoveNorthEastAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), new MoveSouthEastAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), new MoveSouthAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), new MoveSouthWestAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), new MoveNorthWestAction(gameFacade));

        //Developer
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), new SwitchDeveloperAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), new MoveDeveloperAction(gameFacade));

        //Blocks
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), new PlaceDeveloperAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), new PlaceOneBlockAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), new PlaceTwoBlockAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), new PlaceThreeBlockAction(gameFacade));

        //Etc
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), new ChangeTurnAction(gameFacade));

        gameFacade.bindActiveKeys(activeActions);
        */
    }
}

class planningState implements gameState {
    ActiveController activeController;
    FestivalController festivalController;
    PlanningController planningController;
    ReplayController replayController;
    Facade gameFacade;
    public planningState(Facade facade, ActiveController aController, FestivalController fController, PlanningController pController, ReplayController rController)
    {
        gameFacade = facade;
        activeController = aController;
        festivalController = fController;
        planningController = pController;
        replayController = rController;
    }

    @Override
    public void bindKeys()
    {
        HashMap<KeyStroke, Action> planningActions = new HashMap<KeyStroke, Action>();
        //TODO GET THIS WORKING!
        //Movement
        /*
         activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), new MoveNorthPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), new MoveNorthEastPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), new MoveSouthEastPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), new MoveSouthPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), new MoveSouthWestPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), new MoveNorthWestPlanningAction(gameFacade));

        //Developer
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), new SwitchDeveloperPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), new MoveDeveloperPlanningAction(gameFacade));

        //Blocks
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), new PlaceDeveloperPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), new PlaceOneBlockPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), new PlaceTwoBlockPlanningAction(gameFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), new PlaceThreeBlockPlanningAction(gameFacade));

        //Etc
        //ChangeTurnPlanningAction will prompt user to end the planning mode (i.e., save or discard changes)
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), new ChangeTurnPlanningAction(gameFacade));

        gameFacade.bindPlanningKeys(activeActions);
        */

    }
}

class replayState implements gameState {
    ActiveController activeController;
    FestivalController festivalController;
    PlanningController planningController;
    ReplayController replayController;
    Facade gameFacade;
    public replayState(Facade facade, ActiveController aController, FestivalController fController, PlanningController pController, ReplayController rController)
    {
        gameFacade = facade;
        activeController = aController;
        festivalController = fController;
        planningController = pController;
        replayController = rController;
    }

    @Override
    public void bindKeys()
    {

    }
}

class festivalState implements gameState {

    ActiveController activeController;
    FestivalController festivalController;
    PlanningController planningController;
    ReplayController replayController;
    Facade gameFacade;
    public festivalState(Facade facade, ActiveController aController, FestivalController fController, PlanningController pController, ReplayController rController)
    {
        gameFacade = facade;
        activeController = aController;
        festivalController = fController;
        planningController = pController;
        replayController = rController;
    }
    @Override
    public void bindKeys()
    {

    }
}

class mainMenuState implements gameState
{
    @Override
    public void bindKeys()
    {
        //TODO
        /*
        HashMap<KeyStroke, Action> mainMenuActions = new HashMap<KeyStroke, Action>();
        mainMenuActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), new NewGameAction());
        mainMenuActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), new LoadGameAction());
        mainMenuActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), new QuitGameAction());

         gameFacade.bindMainMenuKeys(activeActions);
        */
    }
}
