package controller.actions;

import controller.Facade;
import model.Developer;
import model.JavaPlayer;
import model.Player;

/**
 * Created by Horacio on 4/18/14.
 */
public class SwitchDeveloperAction {
    private Facade facade;
    private JavaPlayer currentPlayer;

    public SwitchDeveloperAction(Facade facade){
        this.facade = facade;
    }

    public void actionPerformed(){
        currentPlayer = facade.getGameController().getPlayers().get(0);
        Developer developer = currentPlayer.removeDeveloper(0);
        currentPlayer.addDeveloper(developer);
        //change the selector in the view to the position of the developer
    }
}
