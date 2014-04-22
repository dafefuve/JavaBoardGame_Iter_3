package controller;

import controller.actions.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Daniel on 4/17/2014.
 */
public class GameStateManager {
    //Different game states
    gameState active;
    gameState festival;
    gameState mainMenuState;
    gameState pause;
    gameState planning;
    gameState replay;

    //current state
    gameState currentState;

    //Facade
    Facade theFacade;

    public GameStateManager()
    {
        //Default starting state is main Menu
        theFacade = new Facade();

        active = new activeState(this, theFacade);
        festival = new festivalState(this, theFacade);
        mainMenuState = new mainMenuState(this, theFacade);
        pause = new pauseGameState(this, theFacade);
        planning = new planningState(this, theFacade);
        replay = new replayState(this, theFacade);

        currentState = mainMenuState;

        currentState.bindKeys();

    }
    public gameState getActiveState(){return active;}
    public gameState getFestivalState(){return festival;}
    public gameState getMainMenuState(){return mainMenuState;}
    public gameState getPauseState(){return pause;}
    public gameState getPlanningState(){return planning;}
    public gameState getReplayState(){return replay;}
    public void setState(gameState inState){ currentState = inState; currentState.bindKeys();}
}
interface gameState {
    //boolean changeTurn();
    //TODO figure out how to change state and if that is determined by the kay the user presses;
    void bindKeys();
}
class activeState implements gameState {

    GameStateManager stateManager;
    Facade theFacade;

    public activeState(GameStateManager manager, Facade facade)
    {
        stateManager = manager;
        theFacade = facade;
    }

    @Override
    public void bindKeys()
    {
        HashMap<KeyStroke, AbstractAction> activeActions = new HashMap<KeyStroke, AbstractAction>();

        //Stores all the actions and their associated keys for activeMode, then calls Facade and passes this map
        //Let me know if I've missed any actions!!
        //Movement

        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), new MoveNorthAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), new MoveNorthEastAction(theFacade));

        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), new MoveSouthEastAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), new MoveSouthAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), new MoveSouthWestAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), new MoveNorthWestAction(theFacade));

        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), new PlaceVillageTileAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), new PlaceRiceTileAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), new PlaceTwoBlockAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), new PlaceIrrigationTileAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), new ChangeTurnAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), new ExecuteAction(theFacade));
        activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), new ExitBlockPlacementAction(theFacade));


        //System.out.println("KEY BINDINGS WORK?");
        //Developer
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), new SwitchDeveloperAction(theFacade));
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), new MoveDeveloperAction(theFacade));

        //Blocks
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), new PlaceDeveloperAction(theFacade));
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), new PlaceOneBlockAction(theFacade));
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), new PlaceTwoBlockAction(theFacade));
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), new PlaceThreeBlockAction(theFacade));

        //Etc
        //activeActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), new ChangeTurnAction(theFacade));

        theFacade.bindActiveKeys(activeActions);

    }
}

class festivalState implements gameState {

    GameStateManager stateManager;
    Facade theFacade;

    public festivalState(GameStateManager manager, Facade facade)
    {
        stateManager = manager;
        theFacade = facade;
    }

    @Override
    public void bindKeys()
    {

    }
}

class mainMenuState implements gameState{

    GameStateManager stateManager;
    Facade theFacade;

    public mainMenuState(GameStateManager manager, Facade facade)
    {
        stateManager = manager;
        theFacade = facade;
        bindKeys();
    }
    @Override
    public void bindKeys()
    {
        //TODO

        HashMap<KeyStroke, AbstractAction> mainMenuActions = new HashMap<KeyStroke, AbstractAction>();
        mainMenuActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), new NewGameAction(stateManager, theFacade));
        mainMenuActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), new LoadGameAction());
        mainMenuActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), new QuitGameAction());

        theFacade.bindMainMenuKeys(mainMenuActions);

    }
}

class pauseGameState implements gameState{

    GameStateManager stateManager;
    Facade theFacade;

    public pauseGameState(GameStateManager manager, Facade facade)
    {
        stateManager = manager;
        theFacade = facade;
    }

    @Override
    public void bindKeys() {

    }
}

class planningState implements gameState {
    GameStateManager stateManager;
    Facade theFacade;

    public planningState(GameStateManager manager, Facade facade)
    {
        stateManager = manager;
        theFacade = facade;
    }

    @Override
    public void bindKeys()
    {
        HashMap<KeyStroke, Action> planningActions = new HashMap<KeyStroke, Action>();
        //TODO GET THIS WORKING!
        //Movement
        /*
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), new MoveNorthPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), new MoveNorthEastPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), new MoveSouthEastPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), new MoveSouthPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), new MoveSouthWestPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), new MoveNorthWestPlanningAction(gameFacade));

        //Developer
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), new SwitchDeveloperPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), new MoveDeveloperPlanningAction(gameFacade));

        //Blocks
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), new PlaceDeveloperPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), new PlaceOneBlockPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), new PlaceTwoBlockPlanningAction(gameFacade));
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), new PlaceThreeBlockPlanningAction(gameFacade));

        //Etc
        //ChangeTurnPlanningAction will prompt user to end the planning mode (i.e., save or discard changes)
        planningActions.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), new ChangeTurnPlanningAction(gameFacade));

        theFacade.bindPlanningKeys(planningActions);
        */

    }
}

class replayState implements gameState {
    GameStateManager stateManager;
    Facade theFacade;

    public replayState(GameStateManager manager, Facade facade)
    {
        stateManager = manager;
        theFacade = facade;
    }

    @Override
    public void bindKeys()
    {

    }
}
