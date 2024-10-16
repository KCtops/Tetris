package Model;

import java.util.Arrays;

/**
 * StraightTetronimo.java
 * A straight line tetronimo
 * @author Kyle Collamer
 */
public class StraightTetronimo extends Tetronimo {

    /**
     * Constructor to produce a straight line tetronimo with a random color.
     */
    public StraightTetronimo() {
        super.setType(new int[][] {{1}, {1}, {1}, {1}});
    }

    /**
     * Overriding constructor that rotates the straight line tetronimo.
     */
    @Override public void rotate(boolean collidesRight, boolean collidesLeft, int xCoordinate, int columns) {
        int[][] vertical = new int[][] {{1}, {1}, {1}, {1}};
        int[][] horizontal = new int[][] {{1, 1, 1, 1}};
        if (Arrays.deepEquals(getType(), vertical)) {
            if (xCoordinate == columns - 1) {
                setXCoordinate(getXCoordinate() - 3);
            } else if (xCoordinate == columns - 2) {
                setXCoordinate(getXCoordinate() - 2);
            } else if (xCoordinate == columns - 3) {
                setXCoordinate(xCoordinate - 1);
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
        return "straight tetromino";
    }
}
