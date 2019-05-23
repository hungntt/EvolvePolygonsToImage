package MonaLisa;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/** Renderer represents a type itself
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
public class Renderer {
    private Image img;
    private Canvas canvas;
    private GraphicsContext g;
    private WritableImage display_image, hidden_image;
    public Drawing currentDraw;
    public double ErrorGap;


    /**
     * Instantiates a new Renderer
     * @param img               the source image
     * @param canvas            the canvas
     * @param g                 the graphic context
     * @param display_image     the display image
     */
    public Renderer(Image img, Canvas canvas, GraphicsContext g, WritableImage display_image){
        this.img = img;
        this.canvas = canvas;
        this.g = g;
        this.display_image = display_image;
        hidden_image = new WritableImage(Tools.MaxW, Tools.MaxH);
    }

    /**
     * Initialize
     * @param sourceColor The color matrix of source image
     */
    public void initialize(int[][] sourceColor){
        currentDraw = new Drawing();
        currentDraw.init();
        g.clearRect(0,0, Tools.MaxW, Tools.MaxH);
        for (Polygon polygon: currentDraw.Polygons) {
            polygon.draw(g);
        }
        canvas.snapshot(new SnapshotParameters(), hidden_image);
        ErrorGap = Calculator.getDrawing(hidden_image, sourceColor);
    }

    /**
     * Render a drawing
     * Generate a new drawing that look more similar to the source image
     * @param sourceColor       The color matrix of source image
     */
    void Render(int[][] sourceColor){
        Drawing newDraw = currentDraw.clone();
        do {
            newDraw.Mutate();
        } while (!newDraw.IsDirty);

        g.clearRect(0,0, Tools.MaxW, Tools.MaxH);
        for (Polygon polygon: newDraw.Polygons) {
            polygon.draw(g);
        }
        canvas.snapshot(new SnapshotParameters(), hidden_image);

        double newErrorGap = Calculator.getDrawing(hidden_image, sourceColor);

        if (newErrorGap < ErrorGap){
            currentDraw = newDraw;
            ErrorGap = newErrorGap;

            // newDraw.Polygons_count
        }
        //else, discard new drawing
    }

    /**
     * Render the best generation
     * Update the new suitable drawing
     */
    void render_best_generation(){
        g.clearRect(0,0, Tools.MaxW, Tools.MaxH);
        for (Polygon polygon: currentDraw.Polygons) {
            polygon.draw(g);
        }
        canvas.snapshot(new SnapshotParameters(), display_image);
    }

    /**
     * Reset
     * Clear the current drawing and set everything back to default
     * @param sourceColor       The color matrix of source image
     */
    void reset(int[][] sourceColor){
        currentDraw.init();
        g.clearRect(0,0, Tools.MaxW, Tools.MaxH);
        for (Polygon polygon: currentDraw.Polygons) {
            polygon.draw(g);
        }
        canvas.snapshot(new SnapshotParameters(), hidden_image);
        ErrorGap = Calculator.getDrawing(hidden_image, sourceColor);
        g.clearRect(0,0, Tools.MaxW, Tools.MaxH);
        canvas.snapshot(new SnapshotParameters(), display_image);
    }

}
