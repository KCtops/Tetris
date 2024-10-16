package Model;

import java.util.Arrays;

/**
 * TTetronimo.java
 * T-Shaped tetronimo
 * @author Kyle Collamer
 */
public class TTetronimo extends Tetronimo {

    /**
     * Constructor to create a T-shaped tetronimo and generate a random color.
     */
    public TTetronimo() {
        super.setType(new int[][] {{0, 1}, {1, 1}, {0, 1}});
    }

    /**
     * Overriding method to rotate the T-shaped tetronimo.
     */
    @Override public void rotate(boolean collidesRight, boolean collidesLeft, int xCoordinate, int columns) {
        int[][] up = new int[][] {{0, 1}, {1, 1}, {0, 1}};
        int[][] left = new int[][]{{1, 1, 1}, {0, 1, 0}};
        int[][] down = new int[][] {{1, 0}, {1, 1}, {1, 0}};
        int[][] right = new int[][] {{0, 1, 0}, {1, 1, 1}};
        if (Arrays.deepEquals(getType(), up)) {
            if (collidesRight) {
                setXCoordinate(getXCoordinate() - 1);
            }
            setType(left);
        } else if (Arrays.deepEquals(getType(), left)){
            setType(down);
        } else if (Arrays.deepEquals(getType(), down)) {
            if (collidesRight) {
                setXCoordinate(getXCoordinate() - 1);
            }
            setType(right);
        } else {
            setType(up);
        }
    }

    /**
     * Returns the type in a string format
     * @return the type in a string format
     */
    @Override public String getTypeToString() {
        return "t-shaped tetromino";
    }
}
