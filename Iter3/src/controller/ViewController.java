package controller;

import view.ViewTest;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class ViewController {
    view.MainView mainView;
    private boolean invalidPlacement;
    public ViewController()
    {
        mainView = new view.MainView();
        invalidPlacement = false;

    }

    public void initializeView(String[] players)
    {

    }
    //public void bindKeyListener(InputListener listener )
    {

    }
    public void registerChangeTurnAction()
    {
        ViewTest.registerChangeTurn();
    }
    public void changeTurn()
    {

    }

    public void recieveMainMenuKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        mainView.registerMainMenuKeyBindings(keyBindings);
    }

    public void recieveActiveKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        mainView.registerActiveKeyBindings(keyBindings);
    }

    public void recievePlanningKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        mainView.registerPlanningKeyBindings(keyBindings);
    }

    public int getCurrentSpace()
    {
        return mainView.getCurrentSpace();
    }

    public void startNewGame()
    {
        mainView.startNewGame();
    }

    public void placeVillageTile()
    {
        mainView.placeVillageTile();
    }

    public void moveNorth()
    {
        mainView.moveNorth();
    }

    public void moveNorthEast()
    {
        mainView.moveNorthEast();
    }

    public void moveSouth()
    {
        mainView.moveSouth();
    }

    public void moveSouthEast()
    {
        mainView.moveSouthEast();
    }

    public void moveSouthWest()
    {
        mainView.moveSouthWest();
    }

    public void moveNorthWest()
    {
        mainView.moveNorthWest();
    }

    public void endPlacement()
    {
        mainView.endPlacement(invalidPlacement);
    }

    public void notifyInvalidPlacement()
    {
        invalidPlacement = true;
    }

    public void exitPlacement(){
        mainView.exitPlacement();
    }

}
