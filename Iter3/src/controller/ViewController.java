package controller;

import view.ViewTest;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class ViewController {
    view.MainView mainView;
    public ViewController()
    {
        mainView = new view.MainView();
    }
    public ViewController(view.MainView mainView)
    {
         this.mainView = mainView;
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

    public void recieveMainMenuKeyBindings(HashMap<KeyStroke, Action> keyBindings)
    {
        mainView.registerMainMenuKeyBindings(keyBindings);
    }

    public void recieveActiveKeyBindings(HashMap<KeyStroke, Action> keyBindings)
    {
        mainView.registerActiveKeyBindings(keyBindings);
    }

    public void recievePlanningKeyBindings(HashMap<KeyStroke, Action> keyBindings)
    {
        mainView.registerPlanningKeyBindings(keyBindings);
    }

    public int getCurrentSpace()
    {
        return mainView.getCurrentSpace();
    }
}
