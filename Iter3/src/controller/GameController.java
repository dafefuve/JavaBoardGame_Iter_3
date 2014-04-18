package controller;

import model.*;

import java.util.List;

/**
 * Created by Horacio on 4/17/14.
 */
public class GameController {
    private Game game;

    public GameController(Game game){
        this.game = game;
    }

    public List<JavaPlayer> getPlayers(){
        return game.getPlayers();
    }

    public GameInventory getInventory(){
        return game.getInventory();
    }

    public JavaPlayer getWinner(){
        return game.getWinner();
    }

    public void setWinner(JavaPlayer winner){
        game.setWinner(winner);
    }

    //Todo
    public void endGame(){

    }
    //Todo
    public void calculateWinner(){

    }
}
