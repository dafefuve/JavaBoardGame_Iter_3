package model;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import controller.Command;

import java.util.Stack;

/**
 * Created by Horacio on 4/18/14.
 */
public class CommandStack {
    private Stack<Command> stack;

    public CommandStack(){
        stack = new Stack<Command>();
    }

    public boolean isEmpty(){
        if(stack.empty())
            return true;
        return false;
    }

    public void push(Command c){
        stack.push(c);
    }

    public Command pop(){
        return stack.pop();
    }

    public Command top(){
        return stack.peek();
    }

}
