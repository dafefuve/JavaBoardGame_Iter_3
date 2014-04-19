package Model;

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
    /*
    public void executeStack(){
        Stack<Command> temp = new Stack();
        this reverses the order of the commands so that 
        the first command executed in the game is at the top of the temp stack*/
        /*
        while(!stack.empty()){
            temp.push(stack.pop());
        }*/
        /*Now we take those commands and put them back into the original stack, only we execute them too*/
        /*while(!temp.empty()){
            Command tempCommand = temp.pop();
            stack.push(tempCommand);
            tempCommand.execute();
        }
    }

    public void saveFile(String fileName){
        Stack<Command> temp = new Stack();
        while(!stack.empty()){
            temp.push(stack.pop());
        }
        File file = new File(fileName);
        file.createNewFile();
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        while(!temp.empty()){
            Command tempCommand = temp.pop();
            stack.push(tempCommand);
            writer.print(tempCommand.toString());
        }
        writer.close();
    }*/
    /*
    Maybe there should be a save game command...
    */
    /*
    File Save Format:
    Important state information

    Command name
    Players involved
    */
    /*
    Don't forget that we also need a way to save the deck
    *//*
    public void loadFile(String fileName){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e){
            System.out.println("Save file not found!");
        }
        while(scanner.hasNext()){
            //scan
        }
        executeStack();
    }*/
}
