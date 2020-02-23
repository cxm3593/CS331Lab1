import java.io.*;
import java.util.ArrayList;

public class ElevationMap {

    private static final int map_height = 500; // Since all map that are going to be used are in same size;
    private static final int map_wide = 395;
    private Double[][] map_values;

    public ElevationMap(String filename){
        map_values = new Double[map_height][map_wide];
        ArrayList<String> lines = readElevationMap(filename);
        for(int i=0; i<map_values.length; i++){
            String[] line_value = lines.get(i).trim().split("   ");
            for(int j=0; j<map_values[i].length; j++){
                map_values[i][j] = Double.valueOf(line_value[j]);
            }
        }
    }

    /**
     * This function read a elevation text file and return a 2d array
     * @return Arraylist of string of lines.
     * @param filename
     */
    public static ArrayList<String> readElevationMap(String filename){
        File eleFile = new File(filename); // file name
        ArrayList<String> lists = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(eleFile));
            String line;
            while((line = br.readLine()) != null ){
                lists.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lists;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<map_values.length; i++){
            for(int j=0; j<map_values[i].length; j++){
                sb.append(this.map_values[i][j] + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void print(){
        for(int i=0; i<map_values.length; i++){
            for(int j=0; j<map_values[i].length; j++){
                System.out.print(this.map_values[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    /**
     * get the location elevation
     * @param i
     * @param j
     * @return
     */
    public double getElevation(int i, int j){
        if(j<0 || j >= 394 || i<0 || i > 499){
            return -1.0;
        }
        return this.map_values[i][j];
    }

}
