package MonaLisa;
/** Settings represents a type itself
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
class Settings{

    static int AddPolygonMutationRate = 70;
    static int MovePolygonMutationRate = 70;
    static int RemovePolygonMutationRate = 70;
    static int ReplacePolygonMutationRate = 140;
    static int SwapPolygonMutationRate = 70;

    static int PointsPerPolygonMax = 15;
    static int PointsPerPolygonMin = 3;
    
    static int PolygonsMax = 250;
    static int PolygonsMin = 10;
    
    static int AddPointMutationRate = 140;
    static int RemovePointMutationRate = 140;
    static int SwapPointMutationRate = 140;

    static int MovePointMaxMutationRate = 140;
    static int MovePointMidMutationRate = 100;
    static int MovePointMinMutationRate = 70;


    static int MovePointRangeMid = 20;
    static int MovePointRangeMin = 5;
    
    static int AlphaMutationRate = 440;
    static int RedMutationRate = 440;
    static int BlueMutationRate = 440;
    static int GreenMutationRate = 440;

    static int AlphaShiftMutationRate = 440;
    static int RedShiftMutationRate = 440;
    static int BlueShiftMutationRate = 440;
    static int GreenShiftMutationRate = 440;
}
