package model;

import java.util.List;

/**
 * Created by Daniel on 4/14/2014.
 */
public class Game {
    private List<JavaPlayer> players;
    private GameInventory inventory;
    private JavaPlayer winner;
    private Board board;

    public Game(List<JavaPlayer> initPlayers, GameInventory initInventory, Board initBoard){
        players = initPlayers;
        inventory = initInventory;
        board = initBoard;
    }

    public void setItem(String item, Integer count){
        inventory.setItemCount(item, count);
    }

    public Integer getItem(String item){
        return inventory.getItemCount(item);
    }

    public List<JavaPlayer> getPlayers(){
        return players;
    }

    public GameInventory getInventory(){
        return inventory;
    }

    public JavaPlayer getWinner(){
        return winner;
    }

    public void setWinner(JavaPlayer winner){
        this.winner = winner;
    }

}
