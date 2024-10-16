package Model;

import java.util.Arrays;

/**
 * SkewTetronimoLeftLeaning.java
 * Skew tetronimo that leans to the left from a vertical perspective.
 * @author Kyle Collamer
 */
public class SkewTetronimoLeftLeaning extends Tetronimo {

    /**
     * Constructor that creates the skew tetronimo that leans to the left from a vertical perspective.
     */
    public SkewTetronimoLeftLeaning() {
        super.setType(new int[][] {{1, 0},{1, 1},{0, 1}});
    }

    /**
     * Overriding method that rotates the skew tetronimo that leans to the left from a vertical perspective.
     */
    @Override public void rotate(boolean collidesRight, boolean collidesLeft, int xCoordinate, int columns) {
        int[][] vertical = new int[][] {{1, 0}, {1, 1}, {0, 1}};
        int[][] horizontal = new int[][] {{0, 1, 1}, {1, 1, 0}};
        if (Arrays.deepEquals(getType(), vertical)) {
            if (collidesRight) {
                setXCoordinate(getXCoordinate() - 1);
            }
            setType(horizontal);
        } else {
            setType(vertical);
        }
    }

    /**
     * Returns the type in a string format
     * @return the type in a string format
     */
    @Override public String getTypeToString() {
        return "skew tetromino";
    }
}
