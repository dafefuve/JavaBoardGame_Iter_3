package model;

/**
 * Created by Horacio on 4/15/14.
 */
public class LandType
{
    private String type;

    public LandType(String type)
    {
        this.type = type;
    }

    public boolean equals(LandType other){
        if(type.equals(other.getType())){
            return true;
        }
        return false;
    }
    public String getType(){
        return this.type;
    }
}