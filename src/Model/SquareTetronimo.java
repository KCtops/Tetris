package Model;

/**
 * SquareTetronimo.java
 * A square tetronimo
 * @author Kyle Collamer
 */
public class SquareTetronimo extends Tetronimo {

    /**
     * Constructor to create a square tetronimo.
     */
    public SquareTetronimo() {
        super.setType(new int[][] {{1, 1}, {1, 1}});
    }

    /**
     * Overriding method that does nothing as a square can not be rotated.
     */
    @Override public void rotate(boolean collidesRight, boolean collidesLeft, int xCoordinate, int columns) {
        //Does nothing because it is a square.
    }

    /**
     * Returns the type in a string format
     * @return the type in a string format
     */
    @Override public String getTypeToString() {
        return "square tetromino";
    }
}
