public class Location {
    public double elevation;
    private TerrainType terrainType;
    public int x;
    public int y;



    public Location(double elevation, TerrainType terrainType, int x, int y){
        this.elevation = elevation;
        this.terrainType = terrainType;
        this.x = x;
        this.y = y;

    }

    public double getTerrainMod(){
        if (this.terrainType == TerrainType.OpenLand){
            return 0.0;
        }
        else if (this.terrainType == TerrainType.RoughMeadow){
            return -0.3;
        }
        else if (this.terrainType == TerrainType.EasyForest){
            return -0.2;
        }
        else if (this.terrainType == TerrainType.SlowForest){
            return -0.5;
        }
        else if(this.terrainType == TerrainType.WalkForest){
            return -0.4;
        }
        else if(this.terrainType == TerrainType.ImpassibleVeg){
            return -1.0;
        }
        else if(this.terrainType == TerrainType.Lake){
            return -0.95;
        }
        else if(this.terrainType == TerrainType.PavedRoad){
            return 0.0;
        }
        else if(this.terrainType == TerrainType.FootPath){
            return 0.0;
        }
        else if(this.terrainType == TerrainType.Outbound){
            return -1.0;
        }
        return 0;
    }

    public boolean equal(Location location){
        if(this.x == location.x && this.y == location.y){
            return true;
        }
        return false;
    }

}
