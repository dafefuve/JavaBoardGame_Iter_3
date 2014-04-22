package controller.actions;

import controller.Facade;
import model.JavaPlayer;

/**
 * Created by Horacio on 4/22/14.
 */
public class TurnController {
    private Facade facade;
    private JavaPlayer currentPlayer;

    public TurnController(Facade facade){
        this.facade = facade;
    }

}
