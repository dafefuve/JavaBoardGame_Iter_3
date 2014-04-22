package model;

import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 4/14/2014.
 */
public class PlayerInventory extends Inventory{

    private List<PalaceCard> hand;
    private Map<String,Integer> items;

    public PlayerInventory(List<PalaceCard> initHand, Map<String,Integer> initItems){
        hand = initHand;
        items = initItems;
    }

    @Override
    public void setItemCount(String item, Integer count){
        items.put(item,count);
    }

    @Override
    public Integer getItemCount(String item){
        return items.get(item);
    }

    public void addPalaceCard(PalaceCard card){
        hand.add(card);
    }

    public PalaceCard removePalaceCard(PalaceCard card){
        if(!hand.isEmpty()){
            hand.remove(card);
            return card;
        }
        return null;
    }

    public List<PalaceCard> getHand(){
        return hand;
    }

    //this is necessary to set a hand
    public void setHand(List<PalaceCard> hand){
        this.hand = hand;
    }




}
