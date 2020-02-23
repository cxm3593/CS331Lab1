import java.math.*;

public class PathPoint {
    private PathPoint parent;
    public Location location;
    public double g = 0; // time cost
    public double h = 0; // remain time estimation (heuristics)

    public PathPoint(Location location){
        this.location = location;
    }


    /**
     * f = g + h
     * @return
     */
    public double getf(){
        return this.g + this.h;
    }

    /**
     * Generate g (time cost) from this to parent
     */
    public void generateG(){
        int parentX = parent.location.x;
        int parentY = parent.location.y;
        double distance = 0;

        if(Math.abs(parentY - this.location.y) == 1){
            distance = 7.55;
        }
        else if(Math.abs(parentX - this.location.x) == 1){
            distance = 10.29;
        }

        this.g = parent.g + ((distance / (Walker.normalspeed + this.location.getTerrainMod())) ); // WIP need to add elevation modifier.

    }

    public PathPoint getParent() {
        return parent;
    }

    public void setParent(PathPoint parent) {
        this.parent = parent;
    }
}
