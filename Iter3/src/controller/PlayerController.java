package controller;

import model.*;

import java.util.List;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class PlayerController {
    private JavaPlayer currentPlayer;
    private Facade facade;

    public PlayerController(Facade facade){

        this.currentPlayer = facade.getTurnController().getCurrentPlayer();
    }

    public void setCurrentPlayer(){
        this.currentPlayer = facade.getTurnController().getCurrentPlayer();
    }
    public void setItemCount(String item, Integer count){
        currentPlayer.setItemCount(item, count);
    }

    public Integer getItemCount(String item){
        return currentPlayer.getItemCount(item);
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
