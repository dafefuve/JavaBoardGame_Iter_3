package controller;

import model.*;

import java.util.List;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class PlayerController {
    JavaPlayer currentPlayer;

    public PlayerController(){
        //todo find out how to give individual currentPlayers
        this.currentPlayer = null;
    }

    public void setCurrentPlayer(JavaPlayer player){
        this.currentPlayer = player;
    }
    public void setItem(String item, Integer count){
        currentPlayer.setItem(item, count);
    }

    public Integer getItem(String item){
        return currentPlayer.getItem(item);
    }

    public void addDeveloper(Developer developer){
        currentPlayer.addDeveloper(developer);
    }

    public void removeDeveloper(Developer developer){
        currentPlayer.removeDeveloper(developer);
    }

    public void addPalaceCard(PalaceCard card){
        currentPlayer.addPalaceCard(card);
    }

    public PalaceCard removePalaceCard(PalaceCard card){
        return currentPlayer.removePalaceCard(card);
    }

    public List<PalaceCard> getHand(){
        return currentPlayer.getHand();
    }

    public Inventory getInventory(){
        return currentPlayer.getInventory();
    }

    public List<Developer> getDevelopers(){
        return currentPlayer.getDevelopers();
    }
}
