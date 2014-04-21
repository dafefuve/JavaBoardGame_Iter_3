package model;

import model.Developer;
import model.Palace;

import java.util.Stack;

/**
 * Created by Daniel on 4/14/2014.
 */
public class Space
{
    public Developer developer;
    public Palace palace;
    public Stack<TileComponent> land;
    public boolean hasDeveloper;

    public int row;
    public int column;
    public int id;

    public Space()
    {
        developer = null;
        palace = null;
        land = null;
        hasDeveloper = false;

        row = 0;
        column = 0;
        id = row*19 + column;   //assuming that there are 19 columns
    }

    public Space(int r, int c)
    {
        developer = null;
        palace = null;
        land = null;
        hasDeveloper = false;

        row = r;
        column = c;
        id = row*19 + column;
    }

    public void addTileComponent(TileComponent tileComponent){
        land.push(tileComponent);
    }

    public void removeTopTileComponent()
    {
        land.pop();
    }

    public TileComponent getTopTileComponent()
    {
        return this.land.get(this.land.size());
    }

    public Integer getLevel(){
        if(!land.empty())
            return land.size();
        return 0;
    }

    public void setDeveloper(Developer developer)
    {
        this.developer = developer;
        this.setHasDeveloper(true);
    }

    public Developer getDeveloper()
    {
        return this.developer;
    }

    public void setPalace(Palace palace){
        this.palace = palace;
    }

    public boolean getHasDeveloper()
    {
        return this.hasDeveloper;
    }

    public void setHasDeveloper(boolean tf)
    {
        this.hasDeveloper = tf;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getColumn()
    {
        return this.column;
    }

    public int getId()
    {
        return this.id;
    }
}

