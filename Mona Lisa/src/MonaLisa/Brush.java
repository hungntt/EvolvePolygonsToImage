package MonaLisa;

import static MonaLisa.Settings.*;
import static MonaLisa.Tools.*;

/** Represents the brush.
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
public class Brush {
    public int red;
    public int green;
    public int blue;
    public int alpha;
    /**
     * Represents the Initialization
     */
    public void init(){
        red = GetRand(0,255);
        green = GetRand(0,255);
        blue = GetRand(0,255);
        alpha = GetRand(0,255);
    }

    /** Create a brush's clone
     * @return A new Brush
     */
    public Brush Clone(){
        Brush a = new Brush();
        a.red = red;
        a.blue = blue;
        a.green = green;
        a.alpha = alpha;
        return a;
    }

    /** Calculate the chance that the mutation would happen
     * @param drawing A drawing to set the dirty function
     */
    public void Mutate (Drawing drawing){
        /*
          Random color mutation
         */
        if (WillMutate(RedMutationRate)){
            red = GetRand(0, 255);
            drawing.setDirty();
        }
        if (WillMutate(GreenMutationRate)){
            green = GetRand(0, 255);
            drawing.setDirty();
        }
        if (WillMutate(BlueMutationRate)){
            blue = GetRand(0, 255);
            drawing.setDirty();
        }
        if (WillMutate(AlphaMutationRate)){
            alpha = GetRand(1, 255);
            drawing.setDirty();
        }
        /*
        * Shift Mutation
        */
        if (WillMutate(RedShiftMutationRate)){
            red += GetRand(-3, 3);
            red = Restrict(red,0,255);
        }
        if (WillMutate(BlueShiftMutationRate)){
            blue += GetRand(-3, 3);
            blue = Restrict(blue,0,255);
        }
        if (WillMutate(GreenShiftMutationRate)){
            green += GetRand(-3, 3);
            green = Restrict(green,0,255);
        }
        if (WillMutate(AlphaShiftMutationRate)){
            alpha += GetRand(-3, 3);
            alpha = Restrict(alpha,1,255);
        }
    }
}
