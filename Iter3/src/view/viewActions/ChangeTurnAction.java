package view.viewActions;

import controller.Facade;
import controller.ViewController;
import view.PlayerView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class ChangeTurnAction extends AbstractAction
{
    // Every JComponent has an input map and an action map. An input map is used to register a key event to that component.
    // An action map is used to map that key event to an action to be executed (Java does this automagically via actionPerformed())
    Facade facade;
    ViewController vc;

    public ChangeTurnAction(Facade facade, ViewController vc)
    {
        this.facade = facade;
        this.vc = vc;
    }

    public void actionPerformed(ActionEvent e) {
        //TODO
        //facade.changeTurn();
        //vc.updateView();
    }


}
