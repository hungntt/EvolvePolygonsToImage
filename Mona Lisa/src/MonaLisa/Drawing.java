package MonaLisa;

import java.io.Serializable;
import java.util.ArrayList;
import static MonaLisa.Settings.*;

/** Drawing represents a type itself
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
public class Drawing implements Serializable {
    ArrayList<Polygon> Polygons;
    int Polygons_count = 0;

    /**
     * If the drawing is mutated, IsDirty is set as true, otherwise it is set as false
     */
    boolean IsDirty;

    /**
     * Set Dirty
     * Set true to IsDirty
     */
    void setDirty(){
        IsDirty = true;
    }

    /**
     * Initialize a drawing
     */
    void init(){
        Polygons = new ArrayList<Polygon>();
        Polygons_count = 0;
        for (int i = 0; i < PolygonsMin; i++){
             AddPol();
        }
        IsDirty = false;
    }

    /**
     * Copy the current Drawing
     * @return the Drawing
     */
    public Drawing clone(){
        Drawing drawing = new Drawing();
        drawing.Polygons = new ArrayList<Polygon>();
        drawing.Polygons_count = Polygons_count;
        for (Polygon polygon: Polygons){
            drawing.Polygons.add(polygon.Clone());
        }
        drawing.IsDirty = false;
        return drawing;
    }

    /**
     * Calculate the rate that the mutate happens
     */
    void Mutate(){
        this.IsDirty = false;
        if (Tools.WillMutate(AddPolygonMutationRate))
            AddPol();

        if (Tools.WillMutate(RemovePolygonMutationRate))
            RevPol();

        if (Tools.WillMutate(MovePolygonMutationRate))
            MovePol();

        if (Tools.WillMutate(ReplacePolygonMutationRate)){
            ReplacePol();
        }

        if (Tools.WillMutate(SwapPolygonMutationRate)){
            SwapPol();
        }

        for (Polygon polygon:Polygons
             ) {
            polygon.Mutate(this);
        }
    }

    /**
     * Move a Polygon by delete it and add a new one at other position
     */
    private void MovePol(){
        if (Polygons_count < 1){
            return;
        }
        int idx = Tools.GetRand(0, Polygons_count-1);
        Polygon polygon = Polygons.get(idx);
        Polygons.remove(idx);
        idx = (idx + Tools.GetRand(1, Polygons_count - 1)) % Polygons_count;
        if (idx == Polygons_count - 1) Polygons.add(polygon);
        else Polygons.add(idx, polygon);
        setDirty();
    }
    /**
     * Delete a Polygon
     */
    private void RevPol(){
        if (Polygons_count > PolygonsMin){
            int idx = Tools.GetRand(0, Polygons.size()-1);
            Polygons.remove(idx);
            Polygons_count--;
            setDirty();
        }
    }
    /**
     * Add(draw) a new Polygon
     */
    private void AddPol(){
        if (Polygons_count < PolygonsMax){
            Polygon newPol = new Polygon();
            newPol.init();
            int pos = Tools.GetRand(0, Polygons_count);
            Polygons.add(pos, newPol);
            Polygons_count++;
            setDirty();
        }
    }
    /**
     * Replace a Polygon at a random position by a new one
     */
    private void ReplacePol(){
        if (Polygons_count != 0){
            int idx = Tools.GetRand(0, Polygons_count - 1);
            Polygons.get(idx).init();
            setDirty();
        }
    }
    /**
     * Swap 2 random position Polygon
     */
    private void SwapPol(){
        if (Polygons_count >= 2){
            int idx1 = Tools.GetRand(0, Polygons_count - 1);
            int idx2;
            do{
                idx2 = Tools.GetRand(0, Polygons_count - 1);
            }while(idx1 != idx2);
            Polygons.set(idx1, Polygons.set(idx2, Polygons.get(idx1)));
            setDirty();
        }
    }
}
