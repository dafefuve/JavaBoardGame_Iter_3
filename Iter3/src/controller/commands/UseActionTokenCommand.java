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

    public boolean execute(){
        //playerController.setItemCount("actionToken", playerController.getItemCount("actionToken")+1);
        return false;
    }
    public void undo(){
        //playerController.setItemCount("actionToken", playerController.getItemCount("actionToken")-1);
    }

    public String toString(){
        return "TODO this needs to be done USER ACTION COMMAND";
    }
}
