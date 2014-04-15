package controller;

import java.awt.event.KeyEvent;

/**
 * Created by Daniel on 4/15/2014.
 */
public class Facade {
    //Different game states
    GAMEState active;
    GAMEState festival;
    GAMEState planning;
    GAMEState replay;
    //game state
    GAMEState gameState;
    //Controllers
    ActiveController activeController;
    FestivalController festivalController;
    PlanningController planningController;
    ReplayController replayController;

    public Facade(){
        //controllers
        activeController = new ActiveController();
        festivalController = new FestivalController();
        planningController = new PlanningController();
        replayController = new ReplayController();
        //states
        active = new activeState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
        festival = new festivalState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
        planning = new planningState(this, this.activeController, this.festivalController, this.planningController, this.replayController);
        replay = new replayState(this, this.activeController, this.festivalController, this.planningController, this.replayController);

    }
    public void proccessKeyEvent( KeyEvent event)
    {
        event.getKeyChar();
    }
    public void changeState(KeyEvent event){
        event.getKeyChar();

    }

}
interface GAMEState {
    boolean changeTurn();

}
class festivalState implements GAMEState {

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
class activeState implements GAMEState {
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
class planningState implements GAMEState {
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
class replayState implements GAMEState {
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
