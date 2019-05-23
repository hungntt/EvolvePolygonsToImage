package MonaLisa;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;

/** Polygon class represents a type itself
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
class Polygon implements Serializable {

    private ArrayList<Point> Points;
    private Brush Brush;
    private int Points_count;


    /**
     *  Create a new Polygon
     */
    void init(){
        Points = new ArrayList<Point>();
        Points_count = Settings.PointsPerPolygonMin;
        Point origin = new Point();
        origin.Init();
        for (int i = 0; i < Points_count; i++){
            Point point = new Point();
            point.x = Math.min(Math.max(0, origin.x + Tools.GetRand(- Tools.MaxW / 100, Tools.MaxW / 100)), Tools.MaxW - 1);
            point.y = Math.min(Math.max(0, origin.y + Tools.GetRand(- Tools.MaxH / 100, Tools.MaxH / 100)), Tools.MaxH - 1);
            Points.add(point);
        }
        Brush = new Brush();
        Brush.init();
    }

    /**
     * Copy the current Polygon
     * @return the Polygon
     */
    Polygon Clone(){
        Polygon newPolygon = new Polygon();
        newPolygon.Points = new ArrayList<Point>();
        newPolygon.Points_count = Points_count;
        newPolygon.Brush = Brush.Clone();
        for (Point point:Points
             ) {newPolygon.Points.add(point.Clone());
        }
        return newPolygon;
    }

    /**
     * Calculate the rate that the mutation happens
     * @param drawing the Drawing
     */
    void Mutate(Drawing drawing){
        if (Tools.WillMutate(Settings.AddPointMutationRate)){
            AddP(drawing);
        }

        if (Tools.WillMutate(Settings.RemovePointMutationRate)){
            RevP(drawing);
        }

        if (Tools.WillMutate(Settings.SwapPointMutationRate)){
            SwaP(drawing);
        }

        Brush.Mutate(drawing);
        Points.forEach(point -> point.Mutate(drawing));
    }

    /**
     * Add Point
     * Add a new Point to the Polygon
     * @param drawing the drawing
     */
    private void AddP(Drawing drawing){
        if (Points_count < Settings.PointsPerPolygonMax && Points_count >= 2){
            Point newP = new Point();
            int idx = Tools.GetRand(0, Points_count - 1);

            Point prev = Points.get((Points_count + idx - 1) % Points_count);
            Point next = Points.get(idx);

            newP.x = (prev.x + next.x)/2;
            newP.y = (prev.y + next.y)/2;

            Points.add(idx, newP);
            drawing.setDirty();
        }
    }

    /**
     * Remove Point
     * Remove 1 Point in the Polygon
     * @param drawing the drawing
     */
    private void RevP(Drawing drawing){
        if (Points_count > Settings.PointsPerPolygonMin){
            int idx = Tools.GetRand(0, Points_count-1);
            Points.remove(idx);
            drawing.setDirty();

        }
    }

    /**
     * Swap Point
     * Swap the position of 2 random Point
     * @param drawing
     */
    private void SwaP(Drawing drawing){
        int idx1 = Tools.GetRand(0, Points_count - 1);
        int idx2 = Tools.GetRand(0, Points_count - 1);
        if (idx1 == idx2) return;
        else{
            Points.set(idx1, Points.set(idx2, Points.get(idx1)));
            drawing.setDirty();
        }
    }

    /**
     * Drawing the Polygon
     * @param g the Graphic Context
     */
    void draw(GraphicsContext g){
        double[] point_x = new double[Points_count];
        double[] point_y = new double[Points_count];
        for (int index = 0; index < Points_count; index++){
            point_x[index] = Points.get(index).getX();
            point_y[index] = Points.get(index).getY();
        }
        g.setFill(Color.color(Brush.red/256.0, Brush.green/256.0, Brush.blue/256.0, Brush.alpha/256.0));
        g.fillPolygon(point_x, point_y, Points_count);
    }
}

