package model;

import java.util.List;
import java.util.UUID;

/**
 * Created by Daniel on 4/14/2014.
 */
public class Tile {
    //private int tileID;         //check the todo file
    private UUID id;

    public Tile(){
        this.id = UUID.randomUUID();
    }

    public boolean equals(Tile other){
        return this.id == other.id;
    }

    public UUID getId(){
        return id;
    }


}
