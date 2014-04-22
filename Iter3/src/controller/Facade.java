package controller;

import controller.actions.TurnController;
import model.Game;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by Daniel on 4/15/2014.
 */
public class Facade {
    
    private ActiveController activeController;
    private BoardController boardController;
    private CommandStackController commandStackController;
    private CommandController commandController;
    private FestivalController festivalController;
    private GameController gameController;
    private PlanningController planningController;
    private PlayerController playerController;
    private ReplayController replayController;
    private ViewController viewController;
    private TempCommand tempCommand;
    private GameSetupController gameSetupController;
    private TurnController turnController;

    public Facade(){
        activeController = new ActiveController();
        boardController = new BoardController(this);
        commandStackController = new CommandStackController();
        festivalController = new FestivalController();
        gameController = new GameController();
        planningController = new PlanningController();
        playerController = new PlayerController(this);
        replayController = new ReplayController();
        viewController = new ViewController();
        commandController = new CommandController();
        tempCommand = new TempCommand(this);
        gameSetupController = new GameSetupController(this);
        turnController = new TurnController(this);
    }

    public TurnController getTurnController(){
        return turnController;
    }

    public GameSetupController getGameSetupController(){ return gameSetupController;}

    public ActiveController getActiveController(){

        return activeController;
    }

    public BoardController getBoardController(){
        return boardController;
    }

    public CommandStackController getCommandStackController(){
        return commandStackController;
    }

    public CommandController getCommandController(){
        return commandController;
    }

    public FestivalController getFestivalController(){
        return festivalController;
    }

    public GameController getGameController(){
        return gameController;
    }

    public PlanningController getPlanningController(){
        return planningController;
    }

    public PlayerController getPlayerController(){
        return playerController;
    }

    public ReplayController getReplayController(){
        return replayController;
    }

    public ViewController getViewController(){
        return viewController;
    }

    public TempCommand getTempCommand(){
        return tempCommand;
    }

    public void bindMainMenuKeys(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        //Pass key bindings to the view controller which will add them to the main view
        viewController.recieveMainMenuKeyBindings(keyBindings);
    }

    public void bindActiveKeys(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        //Pass key bindings to the view controller which will add them to the main view
        viewController.recieveActiveKeyBindings(keyBindings);
    }

    public void bindPlanningKeys(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        //Pass key bindings to the view controller which will add them to the main view
        viewController.recievePlanningKeyBindings(keyBindings);
    }
}
