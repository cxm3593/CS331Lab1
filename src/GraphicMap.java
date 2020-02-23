import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GraphicMap {
    private static final int map_height = 500; // Since all map that are going to be used are in same size;
    private static final int map_wide = 395;
    private BufferedImage bi;

    public GraphicMap(String filename){
        File img = new File(filename);
        try {
            bi = ImageIO.read(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a pixel's information at given location
     * @param x
     * @param y
     * @return Color at given location
     */
    public Color getPixel(int x, int y){
        Color color = new Color(this.bi.getRGB(x,y));
        return color;
    }
}
