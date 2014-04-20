package controller;

/**
 * Created by Horacio on 4/19/14.
 */
public class TempCommand {
    private Command tempCommand;
    private Facade facade;

    public TempCommand(Facade facade){
        tempCommand = null;
        this.facade = facade;
    }

    public void setTempCommand(Command c){
        tempCommand = c;
    }
    //Todo
    public void execute(){
//        if(tempCommand!=null)
//            //check if the command requires a location and if it does set it for the command
//            if(!tempCommand.execute()) {
//                //notify the view if the execution failed
//                facade.getCommandStackController().push(tempCommand);
//                tempCommand = null;
//
//            }
//            else{
//                //tell the ViewContoller that no action can be executed
//            }
    }
}
