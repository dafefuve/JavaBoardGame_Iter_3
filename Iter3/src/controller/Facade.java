package controller;

import java.awt.event.KeyEvent;

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
    private ResourceController resourceController;
    private ViewController viewController;

    public Facade(){
        activeController = new ActiveController();
        boardController = new BoardController();
        commandStackController = new CommandStackController();
        commandController = new CommandController();
        festivalController = new FestivalController();
        gameController = new GameController();
        planningController = new PlanningController();
        playerController = new PlayerController();
        replayController = new ReplayController();
        resourceController = new ResourceController();
        viewController = new ViewController();
    }


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

    public GameController gameController(){
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

    public ResourceController getResourceController(){
        return resourceController;
    }

    public ViewController getViewController(){
        return viewController;
    }

    /*TODO need to reconsider this methods
    public void proccessKeyEvent( KeyEvent event)
    {
        event.getKeyChar();
    }
    public void changeState(KeyEvent event){
        event.getKeyChar();

    }*/
}
//TODO
//Add getters for each controller, ex getTurnController(), getViewController(), etc etc 
