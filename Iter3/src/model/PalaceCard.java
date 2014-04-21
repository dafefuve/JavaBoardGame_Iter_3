package model;

import sun.net.www.content.text.plain;
import view.PalaceCardPanel;

/**
 * Created by Daniel on 4/14/2014.
 */
//todo

public class PalaceCard {
    private String symbol1;
    private String symbol2;
    private int ID;

    public PalaceCard(String symbol1, String symbol2, int ID){
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
        this.ID = ID;
    }

    public boolean equals(PalaceCard otherCard){

        if(ID==(otherCard.getID()))
            return true;
        return false;
    }
    public String getSymbol1(){
        return symbol1;
    }
    public String getSymbol2(){
        return symbol2;
    }
    public int getID(){
        return ID;
    }
}