package controller;

import model.CommandStack;


/**
 * Created by Horacio on 4/18/14.
 */
public class CommandStackController {
    private CommandStack stack;

    public CommandStackController(){
        stack = new CommandStack();
    }

    public boolean isEmpty(){

        return stack.isEmpty();
    }

    public void push(Command c){
        stack.push(c);
    }

    public Command pop(){
        return stack.pop();
    }

    public Command top(){
        return stack.top();
    }
}
