package controller;

import model.*;

import java.util.List;

/**
 * Created by Horacio on 4/17/14.
 */
public class GameController {
    private Game game;

    public GameController(){

    }

    public List<JavaPlayer> getPlayers(){
        return game.getPlayers();
    }

    public void setItem(String item, Integer count){
        game.setItem(item,count);
    }

    public Integer getItemCount(String item){
        return game.getItem(item);
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

    public void setGame(Game game){
        this.game = game;
    }

    public void setPlayers(List<JavaPlayer> players){
        game.setPlayers(players);
    }

    //Todo
    public void endGame(){

    }
    //Todo
    public void calculateWinner(){

    }
}
