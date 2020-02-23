public class OrienteeringApp {

    private static String terrain_image;
    private static String elevation_file;
    private static String path_file;
    private static String season;
    private static String output_image_filename;

    public static void main(String[] argv){
//        terrain_image = argv[0];
//        elevation_file = argv[1];
//        path_file = argv[2];
//        season = argv[3];
//        output_image_filename = argv[4];

        ElevationMap em = new ElevationMap("mpp.txt");
        //em.print();
        GraphicMap gm = new GraphicMap("terrain.png");
        Map map = new Map(em, gm);
        System.out.println("Map generated");

    }

}
