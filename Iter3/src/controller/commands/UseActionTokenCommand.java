package controller.commands;

import controller.Command;

/*
 * Created by Will
 */
public class UseActionTokenCommand extends Command {
    //private PlayerController playerController;

    public UseActionTokenCommand(){
        //this.playerController=playerController;
    }

    public void execute(){
        //playerController.setItem("actionToken", playerController.getItem("actionToken")+1);
    }
    public void undo(){
        //playerController.setItem("actionToken", playerController.getItem("actionToken")-1);
    }

    public String toString(){
        return "TODO this needs to be done USER ACTION COMMAND";
    }
}
