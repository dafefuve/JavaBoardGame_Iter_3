package controller.commands;

import controller.Command;

/**
 * Created by Horacio on 4/20/14.
 */
public abstract class MovableCommands extends Command{

    public boolean execute(){
        return false;
    }
    public void undo(){}
    public void saveToStack(){}
    public void setLocation(int l){};
}
