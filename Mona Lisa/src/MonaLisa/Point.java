package MonaLisa;

import java.io.Serializable;
import static MonaLisa.Settings.*;
import static MonaLisa.Tools.*;

/** Point class represents a type itself
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
class Point implements Serializable {
    int x, y;

    /**
     * Instantiates a new point
     * @param X the x-coordinate
     * @param Y the y-coordinate
     */
    private Point(int X, int Y){
        x=X;
        y=Y;
    }
    /**
     * Instantiates a new point
     */
    Point(){ x = y = 0;}

    /**
     * Get the x axis coordinates
     * @return the int value of x coordinate
     */
    int getX(){ return x; }

    /**
     * Get the y axis coordinates
     * @return the int value of y coordinate
     */
    int getY(){ return y; }

    /**
     * Create a new point in a random position between 0 and width of the image
     */
    void Init(){
        x = GetRand(0, MaxW - 1);
        y = GetRand(0, MaxH - 1);
    }
    /**
     * Clone the current point
     * @return the Point
     */
    Point Clone(){
        return new Point(x, y);
    }

    /**
     * Calculate the chances that the mutation happens between 0 and the width/height of the image
     * @param drawing the Drawing
     */
    void Mutate(Drawing drawing){
        if (WillMutate(MovePointMaxMutationRate)){
            x = GetRand(0, MaxW);
            y = GetRand(0, MaxH);
            drawing.setDirty();
        }

        if (WillMutate(MovePointMidMutationRate)){
            x = Restrict(x + GetRand(-MovePointRangeMid, MovePointRangeMid),0, MaxW);
            y = Restrict(y + GetRand(-MovePointRangeMid, MovePointRangeMid),0, MaxH);
            drawing.setDirty();
        }

        if (WillMutate(MovePointMinMutationRate)){
            x = Restrict(x + GetRand(-MovePointRangeMin, MovePointRangeMin),0, MaxW);
            y = Restrict(y + GetRand(-MovePointRangeMin, MovePointRangeMin),0, MaxW);
            drawing.setDirty();
        }
    }
}
