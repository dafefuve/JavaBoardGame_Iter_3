package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class InputListener implements KeyListener
{
    private Facade facade;
    private ViewController viewController;
    private boolean changeState;

    public InputListener(String[] playerNames)
    {
        facade = new Facade();
        viewController = new ViewController();
        viewController.initializeView(playerNames);
        viewController.bindKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if(changeState){
            facade.changeState(e);
            changeState = false;
        }
        else
        {
            facade.proccessKeyEvent(e);
        }
        //TODO
        //facade.processKeyEvent(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F1){
            changeState = true;
        }
        else{
            changeState = false;
        }
    }
}
