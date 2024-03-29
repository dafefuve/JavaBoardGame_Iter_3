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
        land = new Stack<TileComponent>();
        hasDeveloper = false;

        row = -1;
        column = 1;
        id = row*19 + column;   //assuming that there are 19 columns
    }

    public Space(int r, int c)
    {
        developer = null;
        palace = null;
        land = new Stack<TileComponent>();
        hasDeveloper = false;

        row = r;
        column = c;
        id = row*19 + column;
    }

    public void addTileComponent(TileComponent tileComponent){
        land.push(tileComponent);
    }

    public TileComponent removeTopTileComponent()
    {
        return land.pop();
    }

    public TileComponent getTopTileComponent()
    {
        return this.land.peek();
    }

    public Integer getLevel(){
        if(!land.empty())
            return land.size() - 1;
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

    public Palace getPalace(){
        return palace;
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

    public Stack<TileComponent> getLand()
    {
        return this.land;
    }
}

