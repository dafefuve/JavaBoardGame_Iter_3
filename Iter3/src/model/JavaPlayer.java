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

    public void setItemCount(String item, Integer count){
        inventory.setItemCount(item, count);
    }

    public Integer getItemCount(String item){
        return inventory.getItemCount(item);
    }

    public void addDeveloper(Developer developer){
        developers.add(developer);
    }

    public Developer removeDeveloper(int index){
        return developers.remove(index);
    }

    public void removeDeveloper(Developer d){
         developers.remove(d);
    }

    public void addPalaceCard(PalaceCard card){
        inventory.addPalaceCard(card);
    }

    public PalaceCard removePalaceCard(PalaceCard card){
        return inventory.removePalaceCard(card);
    }

    public List<PalaceCard> getHand(){ return inventory.getHand();   }

    public Inventory getInventory(){
        return inventory;
    }

    public List<Developer> getDevelopers(){
        return developers;
    }

    public void addFamePoints(int amount){
        int oldAmount = getItemCount("famePoint");
        int total = oldAmount + amount;
        setItemCount("famePoint",total);
    }
}
