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
        double parentElv = parent.location.elevation;
        double distance = 0;

        if(Math.abs(parentY - this.location.y) == 1){
            distance = 7.55;
        }
        else if(Math.abs(parentX - this.location.x) == 1){
            distance = 10.29;
        }
        double elevMod = 1;

        if(this.location.elevation > parentElv){
            double angle = Math.atan((this.location.elevation - parentElv) / 10);
            angle = Math.toDegrees(angle);
            if(angle > 0){
                elevMod = Math.cos(angle); // Elevation modifier
            }
            elevMod = Math.abs(elevMod);
        }


        this.g = parent.g + ((distance / (Walker.normalspeed + this.location.getTerrainMod())) * elevMod); // WIP need to add elevation modifier.

    }

    public PathPoint getParent() {
        return parent;
    }

    public void setParent(PathPoint parent) {
        this.parent = parent;
        this.generateG();
    }

    public boolean atLocation(Location loc){
        if(this.location.equal(loc)){
            return true;
        }
        return false;
    }
}
