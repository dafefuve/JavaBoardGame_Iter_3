package controller;

import java.awt.event.KeyEvent;

/**
 * Created by Daniel on 4/15/2014.
 */
public class Facade {
    ;
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