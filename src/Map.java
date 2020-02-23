import java.awt.*;

public class Map {
    private static final int map_height = 500; // Since all map that are going to be used are in same size;
    private static final int map_wide = 395;

    private ElevationMap em;
    private GraphicMap gm;

    private Location[][] map;

    public Map(ElevationMap em, GraphicMap gm){
        this.em = em;
        this.gm = gm;
        this.GenerateMap();
    }

    public void GenerateMap(){
        map = new Location[map_height][map_wide];
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){
                double elevation = em.getElevation(i, j);
                Color c = gm.getPixel(j,i); // i = y; j = x
                TerrainType tt = this.checkTerrainType(c);
                if(tt == null){
                    System.out.println("Invalid tt found");
                    System.exit(0);
                }
                map[i][j] = new Location(elevation, tt, j, i);
            }
        }
    }

    private TerrainType checkTerrainType(Color c){
        if(c.getRed() == 248){
            return TerrainType.OpenLand;
        }
        else if(c.getRed() == 255){
            if(c.getGreen() == 192){
                return TerrainType.RoughMeadow;
            }
            else if(c.getGreen() == 255) return TerrainType.EasyForest;
        }
        else if(c.getRed()==2){
            if(c.getGreen()==208) return TerrainType.SlowForest;
            else if(c.getGreen() == 136) return TerrainType.WalkForest;
        }
        else if(c.getRed()==5) return TerrainType.ImpassibleVeg;
        else if(c.getRed()==0) {
            if(c.getBlue()==255) return TerrainType.Lake;
            else if(c.getBlue() == 0) return TerrainType.FootPath;
        }
        else if(c.getRed()==71) return TerrainType.PavedRoad;
        else if(c.getRed()==205) return TerrainType.Outbound;
        return null;
    }

    public Location getLocation(int i, int j){
        if(j<0 || j >= 394 || i<0 || i > 499){
            return null;
        }
        return map[i][j];
    }
}
