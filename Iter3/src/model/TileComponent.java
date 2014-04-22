package model;

/**
 * Created by Daniel on 4/14/2014.
 */
public class TileComponent {
    private Tile tile;
    private LandType landType;

    public TileComponent(LandType landType, Tile tile) {
        this.tile = tile;
        this.landType = landType;
    }

    public boolean sameTile(TileComponent other){
       return tile.equals(other.getTile());
    }
    public boolean sameType(TileComponent other){
        return landType.equals(other.getLandType());
    }
    public Tile getTile() {
        return tile;
    }

    public String getLandType() {
        return landType.getType();
    }

}
