package controller;
/**
 * Created by Daniel on 4/17/2014.
 */
public class gameStateManager {
    //Different game states
    gameState active;
    gameState festival;
    gameState planning;
    gameState replay;
    //game state
    gameState gameState;

    /*
    active = new activeState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    festival = new festivalState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    planning = new planningState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    replay = new replayState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
    */
}
interface gameState {
    boolean changeTurn();
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
    public boolean changeTurn() {
        return false;
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
    public boolean changeTurn() {
        return false;
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
    public boolean changeTurn() {
        return false;
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
    public boolean changeTurn() {
        return false;
    }
}
