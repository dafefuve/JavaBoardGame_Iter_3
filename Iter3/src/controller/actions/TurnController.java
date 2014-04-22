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
        currentPlayer = null;
        turnFamePoints = 0;
    }

    public void changeTurn(){
        currentPlayer.addFamePoints(turnFamePoints);
        currentPlayer.setItemCount("actionPoints",6);
        List<JavaPlayer> players = facade.getGameController().getPlayers();
        players.add(players.remove(0));
        facade.getGameController().setPlayers(players);
        currentPlayer = players.get(0);
        turnFamePoints = 0;
    }

    public void addFamePoints(int famePoints){
        turnFamePoints += famePoints;
    }

    public void setCurrentPlayer(JavaPlayer player){
        currentPlayer = player;
    }

    public JavaPlayer getCurrentPlayer(){
        return currentPlayer;
    }



}
