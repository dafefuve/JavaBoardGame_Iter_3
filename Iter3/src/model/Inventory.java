package model;

/**
 * Created by Daniel on 4/14/2014.
 */
public abstract class Inventory   {

    public abstract void setItemCount(String item, Integer count);
    public abstract Integer getItemCount(String item);


}
