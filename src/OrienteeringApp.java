import java.io.*;
import java.math.*;
import java.util.ArrayList;

public class OrienteeringApp {

    private static String terrain_image;
    private static String elevation_file;
    private static String path_file;
    private static String season;
    private static String output_image_filename;

    public static void main(String[] argv){
        terrain_image = argv[0];
        elevation_file = argv[1];
        path_file = argv[2];
        season = argv[3];
        output_image_filename = argv[4];

        ElevationMap em = new ElevationMap(elevation_file);
        //em.print();
        GraphicMap gm = new GraphicMap(terrain_image);
        Map map = new Map(em, gm);
        System.out.println("Map generated");

        ArrayList<int[]> destinations = readPathfile(path_file);
        Walker walker = new Walker(destinations.get(0)[0],destinations.get(0)[1],destinations, map);
        Location destloc = map.getLocation(338, 288);

        System.out.println("Walker started");
        ArrayList<PathPoint> app = walker.walkround();
        System.out.println("Walker reached end");
        //System.out.println("Walker time used: " + pe.g + "second");

        gm.drawPath(app);

    }

    public static ArrayList<int[]> readPathfile(String filename){
        File pathfile = new File(filename);
        ArrayList<int[]> lists = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathfile));
            String line;
            while((line = br.readLine()) != null ){
                String[] point_str = line.trim().split(" ");
                int[] point = new int[2];
                point[0] = Integer.valueOf(point_str[0]);
                point[1] = Integer.valueOf(point_str[1]);
                lists.add(point);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

}
