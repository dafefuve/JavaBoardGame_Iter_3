package controller.actions;

import controller.Command;
import controller.CommandStackController;
import controller.Facade;
import controller.commands.MovableCommands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alexbujduveanu on 4/17/14.
 */
public class PlaceOneBlockAction extends AbstractAction
{
    private Facade facade;

    public PlaceOneBlockAction(Facade facade)
    {
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e)
    {
        //Facade.getViewController().placeOneBlock();
        MovableCommands c = facade.getCommandController().placeOneBlockCommand(facade.getBoardController());
        facade.getTempCommand().setTempCommand(c);


        // If we are in Planning mode, then we would not execute, but instead we would push to the planning mode's stack
        // When planning mode is done and we want to commit, we call commit() or some shit and it would empty the planning stack, put
        // it in the big boy stack and execute all dat shit
    }

}
