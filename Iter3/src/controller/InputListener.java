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

    public InputListener(String[] playerNames)
    {
        facade = new Facade();
        viewController = new ViewController();
        viewController.initializeView(playerNames);
        viewController.bindKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //TODO
        //facade.processKeyEvent(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
