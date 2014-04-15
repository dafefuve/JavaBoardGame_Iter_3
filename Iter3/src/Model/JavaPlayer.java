package model;

import java.util.List;

/**
 * Created by Horacio on 4/15/14.
 */
public class JavaPlayer extends Player{

    private PlayerInventory inventory;
    private List<Developer> developers;

    public JavaPlayer(String name, PlayerInventory inventory){
        super(name);
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Player otherPlayer) {
        if(this.getName().equals(otherPlayer.getName()))
            return true;
        return false;
    }

    public void setItem(String item, Integer count){
        inventory.setItem(item,count);
    }

    public Integer getItem(String item){
        return inventory.getItem(item);
    }

    public void addDeveloper(Developer developer){
        developers.add(developer);
    }

    public void removeDeveloper(Developer developer){
        developers.remove(developer);
    }

    public void addPalaceCard(PalaceCard card){
        inventory.addPalaceCard(card);
    }

    public PalaceCard removePalaceCard(PalaceCard card){
        return inventory.removePalaceCard(card);
    }

    public List<PalaceCard> getHand(){
        return inventory.getHand();
    }

    public Inventory getInventory(){
        return inventory;
    }

    public List<Developer> getDevelopers(){
        return developers;
    }
}
