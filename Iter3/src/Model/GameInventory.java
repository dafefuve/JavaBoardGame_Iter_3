package model;

import java.util.*;

/**
 * Created by Daniel on 4/14/2014.
 */
public class GameInventory extends Inventory{
    private PalaceCard faceUpCard;
    private Stack<PalaceCard> deck;
    private Stack<PalaceCard> discardDeck;
    private Map<String,Integer> items;

    public GameInventory(Stack<PalaceCard> initDeck, Map<String,Integer> initItems) {
        deck = initDeck;
        discardDeck = null;
        items = initItems;
        faceUpCard = drawCardFromDeck();
    }

    @Override
    public void setItem(String item, Integer count) {
        items.put(item,count);
    }

    @Override
    public Integer getItem(String item) {
        return items.get(item);
    }

    public PalaceCard drawCardFromDeck(){
        //If the last card is drawn then the deck is replaced by the discardDeck after it is shuffled
        if(deck.size()<=1){
            PalaceCard lastCard = deck.pop();
            List<PalaceCard> shufList = new ArrayList<PalaceCard>();
            for(int i=0; i <= discardDeck.size(); i++){
                shufList.add(discardDeck.pop());
            }
            Collections.shuffle(shufList);
            for(int i=0; i<=shufList.size(); i++){
                deck.push(shufList.remove(0));
            }
            discardDeck.clear();
            return lastCard;
        }
        return deck.pop();
    }

    public PalaceCard drawFaceUpCard(){
        PalaceCard oldFaceUpCard = faceUpCard;
        faceUpCard = drawCardFromDeck();
        return oldFaceUpCard;
    }

    public PalaceCard getFaceUpCard(){
        return faceUpCard;
    }

    public void discardCard(PalaceCard card){
        discardDeck.push(card);
    }
}
