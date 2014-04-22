package controller.actions;

import controller.Facade;
import model.JavaPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horacio on 4/22/14.
 */
public class TurnController {
    private Facade facade;
    private JavaPlayer currentPlayer;
    private int turnFamePoints;

    public TurnController(Facade facade){
        this.facade = facade;
        turnFamePoints = 0;
    }

    public void changeTurn(){
        List<JavaPlayer> players = facade.getGameController().getPlayers();
        currentPlayer = players.remove(0);
        players.add(currentPlayer);
        facade.getGameController().setPlayers(players);

    }



}
