package model;

import java.util.Stack;

/**
 * Created by Daniel on 4/14/2014.
 */
public class Space {
    public Developer developer;
    public Palace palace;
    public Stack<TileComponent> land;

    public Space(){
        developer = null;
        palace = null;
        land = null;
    }

    public void addTileComponent(TileComponent tileComponent){
        land.push(tileComponent);
    }

    public Integer getLevel(){
        if(!land.empty())
            return land.size();
        return 0;
    }

    public void setDeveloper(Developer developer){
        this.developer = developer;
    }

    public void setPalace(Palace palace){
        this.palace = palace;
    }


}

