package controller.commands;

import controller.Command;
import controller.Facade;
import controller.PlayerController;

/*
 * Created by Will
 */
public class UseActionTokenCommand extends Command {
    private PlayerController playerController;

    public UseActionTokenCommand(Facade f){
        this.playerController=f.getPlayerController();
    }

    public boolean execute(){
        if(playerController.getItemCount("actionToken")>0){
            playerController.setItemCount("actionToken", playerController.getItemCount("actionToken")+1);
            return true;
        }
        else
            return false;
    }
    public void undo(){
        playerController.setItemCount("actionToken", playerController.getItemCount("actionToken")-1);
    }

    public String toString(){
        return "TODO this needs to be done USER ACTION COMMAND";
    }
}
