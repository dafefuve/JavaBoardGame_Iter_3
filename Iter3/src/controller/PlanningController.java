package controller;

import controller.commands.MovableCommands;
import model.Tile;
import model.TileComponent;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Daniel on 4/15/2014.
 */
public class PlanningController {
    private Facade facade;
    private Stack<MovableCommands> planningStack;

    public PlanningController(Facade facade){
        this.facade = facade;
        planningStack = new Stack<MovableCommands>();
    }

    public void pushIntoPlanningStack(MovableCommands c){
        planningStack.push(c);
    }

    public Command popFromPlanningStack(){
        return planningStack.pop();
    }

    public void commitPlanning(){
        //empties the planning stack into a
        ArrayList<MovableCommands> temp = new ArrayList<MovableCommands>();
        for(int i = 0; i < planningStack.size(); i++){
            temp.add(planningStack.pop());
        }

        //sends the commands to the commandStack after they are executed
        for (int i=1; i < temp.size(); i++){
            MovableCommands c = temp.get(temp.size() - i);
            facade.getCommandStackController().push(c);
        }
    }

    public void exitPlanning(){
        for(int i = 0; i < planningStack.size(); i++){
        planningStack.pop().undo();

        }
    }
}
