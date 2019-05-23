package MonaLisa;

import java.util.Random;

/**
 * The type Tools
 */
class Tools {

    private static Random rand = new Random();

    /**
     * The constrain Height.
     */
    static int MaxH = 600;
    /**
     * The constrain Width.
     */
    static int MaxW = 900;

    /**
     * GetRand int
     * Return the random integer value between 2 integers
     * @param min   the minimum
     * @param max   the maximum
     * @return int
     */
    static int GetRand(int min, int max){
        return (min == max) ? min : rand.nextInt(max-min) + min;
    }

    /**
     * Will mutate
     * Return true if the random number from 0 to rate*10 equal to 69
     * @param rate the rate
     * @return the boolean
     */
    static boolean WillMutate(int rate){
        return (GetRand(0,rate*10) == 1);
    }

    /**
     * Restrict
     * Return the value if it is in range from min to max, otherwise return min if it is smaller and max if it is bigger
     * @param value the value
     * @param min   the minimum
     * @param max   the maximum
     * @return the Integer
     */
    static int Restrict(int value, int min, int max){
        return value < min ? min : value > max ? max : value;
    }
}