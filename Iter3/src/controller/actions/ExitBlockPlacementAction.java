package controller.actions;

import controller.Facade;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.event.ActionEvent;

/**
 * Created by Horacio on 4/21/14.
 */

//this needs to be added to gamestate
public class ExitBlockPlacementAction extends AbstractAction {
    private Facade facade;

    public ExitBlockPlacementAction(Facade facade){
        this.facade = facade;
    }

    public void actionPerformed(ActionEvent e){

        System.out.println("the escape key was registered");
        if(facade.getTempCommand().getTempCommand()!=null){
            facade.getTempCommand().setTempCommand(null);
            facade.getViewController().exitPlacement();
            System.out.println("this program just doesnt like u");
        }

    }
}
