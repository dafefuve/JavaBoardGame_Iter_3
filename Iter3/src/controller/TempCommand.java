package controller;

import controller.commands.MovableCommands;

/**
 * Created by Horacio on 4/19/14.
 */
public class TempCommand {
    private MovableCommands tempCommand;    //only commands that have a setter for location
    private Facade facade;

    public TempCommand(Facade facade){
        tempCommand = null;
        this.facade = facade;
    }

    public void setTempCommand(MovableCommands c){
        tempCommand = c;
    }
    //Todo
    public void execute(){


            if(tempCommand.execute()) {
                //notify the view if the execution failed
                facade.getCommandStackController().push(tempCommand);
                tempCommand = null;
                facade.getViewController().endPlacement(false);

            }
            else{
                facade.getViewController().endPlacement(true);
            }
    }

    public MovableCommands getTempCommand(){
        return tempCommand;
    }

    public void setLocation(int spaceId){
        tempCommand.setLocation(spaceId);       //sets the location in the loaded command
    }
}
