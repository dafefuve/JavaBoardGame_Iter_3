package model;

/**
 * Created by Horacio on 4/15/14.
 */
public abstract class Player {
    private String name;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public abstract boolean equals(Player otherPlayer);
}
