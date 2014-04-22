package controller.commands;

import controller.Command;

/**
 * Created by Horacio on 4/20/14.
 */
public abstract class MovableCommands extends Command{

    protected boolean commandCompletion;
    public abstract boolean execute();
    public abstract void undo();
    public abstract void setLocation(int newLocation);
}
