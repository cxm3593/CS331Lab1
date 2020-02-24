import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Walker {
    public static final double normalspeed = 1; // 3.2-4.6 km/h hiking speed normally; take 3.6km/h for estimation means 1m/s
    private Map map;

    private int x; // J
    private int y; // I

    private ArrayList<int[]> destinations; // 0: x, 1: y;

    private ArrayList<Location> path;

    public Walker(int x, int y, ArrayList<int[]> destinations, Map map){
        this.x = x;
        this.y = y;
        this.destinations = destinations;
        this.map = map;
    }

    /**
     * Walker generate a path to a single destination.
     * @param destination
     * @return pathpoint which can trace back to the starting position.
     */
    public PathPoint walk(Location destination){
        ArrayList<PathPoint> openList = new ArrayList<>();
        ArrayList<PathPoint> closeList = new ArrayList<>();
        Location startloc = map.getLocation(y,x);
        PathPoint start = new PathPoint(startloc);
        openList.add(start);

        while(!openList.isEmpty()){
            PathPoint smallf = getSmallestPathPoint(openList);
            openList.remove(smallf);
            ArrayList<PathPoint> temp = this.generateNeighbors(smallf); // generate neighbor
            for(PathPoint pp : temp){
                estimateh(pp, destination); // give every generated one an estimation h, so that every one will have f;
                if (pp.location.equals(destination)){ // when the we found the destination
                    System.out.println("One path has been found");
                    return pp;
                }
                else if(!hasSmallerInList(pp, openList)){
                    if(!hasSmallerInList(pp, closeList)){
                        openList.add(pp);
                    }
                }
            }
            closeList.add(smallf);
        }
        System.out.println("No path can be found");
        return null;
    }

    /**
     * get a traceback path from destination to source
     * @param pp
     * @return
     */
    public ArrayList<PathPoint> traceback(PathPoint pp){
        ArrayList<PathPoint> path = new ArrayList<>();
        path.add(pp);
        PathPoint parent = pp.getParent();
        while(parent != null){
            path.add(parent);
            parent = parent.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * get smallest f Pathpoint from a list
     * @param list
     * @return
     */
    private static PathPoint getSmallestPathPoint(ArrayList<PathPoint> list){
        PathPoint smallest = list.get(0);
        for (PathPoint loc : list){
            if (loc.getf() < smallest.getf()){
                smallest = loc;
            }
        }
        return smallest;
    }

    /**
     * Generate four neighbors from a given path point, return as a collection
     * @param pathPoint
     * @return
     */
    private ArrayList<PathPoint> generateNeighbors(PathPoint pathPoint){
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

        return temp;

    }

    /**
     * Estimate h for pp to destination, necessary.
     * @param pp
     * @param destination
     * @return
     */
    public double estimateh(PathPoint pp, Location destination){
        int startX = pp.location.x;
        int startY = pp.location.y;
        double distance = (Math.abs(destination.x - startX) * 10.29) + (Math.abs(destination.y - startY) * 7.55);
        double h = distance / Walker.normalspeed;
        pp.h = h;
        return h;
    }

    /**
     * Check if the there is a smaller f pp in list already.
     * @param pp
     * @param pl
     * @return
     */
    public boolean hasSmallerInList(PathPoint pp, ArrayList<PathPoint> pl){
        for(PathPoint p: pl){
            if(pp.location.equal(p.location) && (pp.getf() > p.getf())){
                return true;
            }
        }
        return false;
    }

    /**
     * Finally make the walk through all destinations!
     * @return
     */
    public ArrayList<PathPoint> walkround(){
        ArrayList<PathPoint> path = new ArrayList<>();
        double totalcost = 0;
        while(!this.destinations.isEmpty()){
            int[] destination = this.destinations.get(0);
            Location destLoc = this.map.getLocation(destination[1], destination[0]);
            PathPoint step_point = this.walk(destLoc);
            totalcost += step_point.g;
            ArrayList<PathPoint> step_path = traceback(step_point);
            path.addAll(step_path);
            this.x = destLoc.x;
            this.y = destLoc.y;
            destinations.remove(0);
        }
        System.out.println("Walker total walk time: " + totalcost + " seconds.");
        return path;
    }
}
