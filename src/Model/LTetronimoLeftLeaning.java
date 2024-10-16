package Model;

import java.util.Arrays;

/**
 * LTetronimoLeftLeaning.java
 * L-shaped tetronimo that leans to the left from a vertical perspective.
 * @author Kyle Collamer
 */
public class LTetronimoLeftLeaning extends Tetronimo{

    /**
     * Constructor that creates the L-shaped tetronimo that leans to the left from a vertical perspective.
     */
    public LTetronimoLeftLeaning() {
        super.setType(new int[][] {{1, 0}, {1, 0}, {1, 1}});
    }

    /**
     * Overriding method that rotates the L-shaped tetronimo that leans to the left from a vertical perspective.
     */
    @Override public void rotate(boolean collidesRight, boolean collidesLeft, int xCoordinate, int columns) {
        int[][] up = new int[][] {{1, 0}, {1, 0}, {1, 1}};
        int[][] left = new int[][] {{0, 0, 1}, {1, 1, 1}};
        int[][] down = new int[][] {{1, 1}, {0, 1}, {0, 1}};
        int[][] right = new int[][] {{1, 1, 1}, {1, 0, 0}};
        if (Arrays.deepEquals(getType(), up)) {
            if (collidesRight) {
                setXCoordinate(getXCoordinate() - 1);
            }
            setType(left);
        } else if (Arrays.deepEquals(getType(), left)){
            if (collidesRight) {
                setXCoordinate(getXCoordinate() + 1);
            }
            setType(down);
        } else if (Arrays.deepEquals(getType(), down)) {
            if (collidesRight) {
                setXCoordinate(getXCoordinate() - 1);
            }
            setType(right);
        } else {
            if (collidesRight) {
                setXCoordinate(getXCoordinate() + 1);
            }
            setType(up);
        }
    }

    /**
     * Gets the type in a string format.
     * @return type in a string format.
     */
    @Override public String getTypeToString() {
        return "l-shaped tetromino";
    }
}
