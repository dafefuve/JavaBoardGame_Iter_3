package controller;

import model.*;

import java.util.List;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class PlayerController {
    JavaPlayer player;

    public PlayerController(JavaPlayer player){
        this.player = player;
    }

    public void setItem(String item, Integer count){
        player.setItem(item, count);
    }

    public Integer getItem(String item){
        return player.getItem(item);
    }

    public void addDeveloper(Developer developer){
        player.addDeveloper(developer);
    }

    public void removeDeveloper(Developer developer){
        player.removeDeveloper(developer);
    }

    public void addPalaceCard(PalaceCard card){
        player.addPalaceCard(card);
    }

    public PalaceCard removePalaceCard(PalaceCard card){
        return player.removePalaceCard(card);
    }

    public List<PalaceCard> getHand(){
        return player.getHand();
    }

    public Inventory getInventory(){
        return player.getInventory();
    }

    public List<Developer> getDevelopers(){
        return player.getDevelopers();
    }
}
