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

    public Facade(){
        active = new activeState(this);

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

}
class festivalState implements GAMEState {
    Facade gameFacade;
    public festivalState(Facade facade)
    {
        gameFacade = facade;
    }
}
class activeState implements GAMEState {
    Facade gameFacade;
    public activeState(Facade facade)
    {
        gameFacade = facade;
    }

}
class planningState implements GAMEState {
    Facade gameFacade;
    public planningState(Facade facade)
    {
        gameFacade = facade;
    }

}
class replayState implements GAMEState {
    Facade gameFacade;
    public replayState(Facade facade)
    {
        gameFacade = facade;
    }
}

