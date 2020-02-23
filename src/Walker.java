import java.util.ArrayList;

public class Walker {
    private static final double normalspeed = 1; // 3.2-4.6 km/h hiking speed normally; take 3.6km/h for estimation means 1m/s
    private Map map;

    private int x; // J
    private int y; // I

    private ArrayList<double[]> destinations;

    private ArrayList<Location> path;

    public Walker(int x, int y, ArrayList<double[]> destinations, Map map){
        this.x = x;
        this.y = y;
        this.destinations = destinations;
        this.map = map;
    }

    public void walk(){
        ArrayList<Location> openList = new ArrayList<>();
        ArrayList<Location> closeList = new ArrayList<>();
        Location start = map.getLocation(y,x);

    }
}
