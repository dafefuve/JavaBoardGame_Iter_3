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
    public void setItem(String item, Integer count){
        items.put(item,count);
    }

    @Override
    public Integer getItem(String item){
        return items.get(item);
    }

    public void addPalaceCard(PalaceCard card){
        hand.add(card);
    }

    public PalaceCard removePalaceCard(PalaceCard card){
        hand.remove(card);
        return card;
    }

    public List<PalaceCard> getHand(){
        return hand;
    }



}
