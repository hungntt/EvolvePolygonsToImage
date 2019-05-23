package MonaLisa;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.Serializable;
/** Represents the Calculator for the Fitness rate.
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
public class Calculator implements Serializable {

    /** Calculate the color difference between the current drawing image and the source image
     * @param img represents the image of current drawing
     * @param color represents the color matrix of source image
     * @return the double value of pixeroor
     */
    public static double getDrawing(Image img, int[][] color) {
        double pixeroor = 0;
        PixelReader pxr = img.getPixelReader();
        for (int y = 0; y< Tools.MaxH; y++) //row
            for (int x = 0; x<Tools.MaxW; x++){ //column
                int c1 = pxr.getArgb(x,y);
                int c2 = color[x][y]; //sourceColor
                pixeroor += ColorFitness(c1,c2) / Tools.MaxW / Tools.MaxH;
            }
        return pixeroor;
    }

    /**
     * Color Fitness calculates the difference between 2 colors
     * @param c1    The 1st color
     * @param c2    The 2nd color
     * @return  the double value
     */
    private static double ColorFitness(int c1, int c2){
        double r = ((c1 >> 16) & 0xff) - ((c2 >> 16) & 0xff);
        double b = (c1 & 0xff) - (c2 & 0xff);
        double g = ((c1 >> 8) & 0xff) - ((c2 >> 8) & 0xff);
        return r*r + g*g + b*b;
    }


}
