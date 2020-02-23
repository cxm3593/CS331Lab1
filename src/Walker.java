import java.nio.file.Path;
import java.util.ArrayList;

public class Walker {
    public static final double normalspeed = 1; // 3.2-4.6 km/h hiking speed normally; take 3.6km/h for estimation means 1m/s
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

    public void walk(Location destination){
        ArrayList<PathPoint> openList = new ArrayList<>();
        ArrayList<PathPoint> closeList = new ArrayList<>();
        Location startloc = map.getLocation(y,x);
        PathPoint start = new PathPoint(startloc);
        openList.add(start);

        while(!openList.isEmpty()){
            PathPoint smallf = getSmallestPathPoint(openList);
            openList.remove(smallf);


        }
    }

    private static PathPoint getSmallestPathPoint(ArrayList<PathPoint> list){
        PathPoint smallest = list.get(0);
        for (PathPoint loc : list){
            if (loc.getf() < smallest.getf()){
                smallest = loc;
            }
        }
        return smallest;
    }

    private void generateNeighbors(PathPoint pathPoint, ArrayList<PathPoint> openList){
        ArrayList<PathPoint> temp = new ArrayList<>();

        // generate north
        Location northLoc = map.getLocation(pathPoint.location.y - 1, pathPoint.location.x);
        if(northLoc != null){
            PathPoint north = new PathPoint(northLoc);
            north.setParent(pathPoint);
            temp.add(north);
        }

        // generate south
        Location sourthLoc = map.getLocation(pathPoint.location.y + 1, pathPoint.location.x);
        if(sourthLoc != null){
            PathPoint south = new PathPoint(sourthLoc);
            south.setParent(pathPoint);
            temp.add(south);
        }

        // generate east
        Location eastLoc = map.getLocation(pathPoint.location.y, pathPoint.location.x + 1);
        if(eastLoc != null){
            PathPoint east = new PathPoint(eastLoc);
            east.setParent(pathPoint);
            temp.add(east);
        }

        // generate west
        Location westLoc = map.getLocation(pathPoint.location.y, pathPoint.location.x - 1);
        if(westLoc != null){
            PathPoint west = new PathPoint(westLoc);
            west.setParent(pathPoint);
            temp.add(west);
        }

    }
}
