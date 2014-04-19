package controller;

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
        //this.saveToStack();
    }
    public void undo(){
        //playerController.setItem("actionToken", playerController.getItem("actionToken")-1);
    }

    public void saveToStack(){
        //c.push(this);
    }

    public String toString(){
        return "TODO this needs to be done USER ACTION COMMAND";
    }
}
