package Model;

import java.awt.*;

/**
 * Tetronimo.java
 * Abstract class that encompasses the general behavior of a tetromino.
 * @author Kyle Collamer
 */
public abstract class Tetronimo {

    //Array of integer arrays that represents the type of tetronimo.
    private int[][] type;

    //Color of the tetromino
    private Color color;

    //Integer value that stores the x coordinate (top left corner of the top left square).
    private int xCoordinate;

    //Integer value that stores the y coordinate (top left corner of the top left square).
    private int yCoordinate;

    /**
     * Method to create the tetronimo at the middle of the grid
     * @param columns amount of columns so the tetromino is spawned in the middle.
     */
    public void createTetronimo(int columns) {
        setXCoordinate((int)((columns - getWidth()) * 0.5));
        setYCoordinate(-getHeight());
    }

    /**
     * Sets the type of the tetronimo to the given type.
     * @param type array of integer arrays that represents the type of tetronimo.
     */
    public void setType(int[][] type) {
        this.type = type;
    }

    /**
     * Returns the type of the tetronimo.
     * @return the type of the tetronimo.
     */
    public int[][] getType() {
        return type;
    }

    /**
     * Gets the type of tetronimo in a string format.
     * @return the type of tetronimo in a string format.
     */
    public String getTypeToString() {
        return null;
    }

    /**
     * Sets the color of the tetromino to the given color.
     * @param color of the tetromino.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the tetromino.
     * @return the color of the tetromino.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the height (number of squares from top to bottom).
     * @return the height.
     */
    public int getHeight() {
        return type.length;
    }

    /**
     * Returns the width (number of squares from left to right).
     * @return the width.
     */
    public int getWidth() {
        return type[0].length;
    }

    /**
     * Sets the x-Coordinate (top left corner of top left square).
     * @param xCoordinate the entered x-Coordinate of the tetromino.
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Sets the y-Coordinate (top left corner of top left square).
     * @param yCoordinate the entered y-Coordinate of the tetromino.
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Returns the x-Coordinate (top left corner of top left square) of the tetromino.
     * @return the x-Coordinate of the tetromino.
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Returns the y-Coordinate (top left corner of top left square) of the tetromino.
     * @return the y-Coordinate of the tetromino.
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Transfers the tetromino down by increasing its y-Coordinate.
     */
    public void transferDown() {
        yCoordinate++;
    }

    /**
     * Transfers the tetromino down by increasing its x-Coordinate.
     */
    public void transferRight() {
        xCoordinate++;
    }

    /**
     * Transfers the tetromino down by decreasing its x-Coordinate.
     */
    public void transferLeft() {
        xCoordinate--;
    }

    /**
     * Rotates the tetromino depending on its placement in comparison with the right side of the grid.
     * Does nothing on its own, but its overriding methods do the work.
     * @param collidesRight if the tetromino collides with the right.
     */
    public void rotate(boolean collidesRight, boolean collidesLeft, int xCoordinate, int NUM_COLUMNS) {
        //Does nothing on its own.
    }

    /**
     * Gets the right side of the tetromino based upon its xCoordinate and width.
     * @return right side x-Coordinate of the tetromino.
     */
    public int getRight() {
        return xCoordinate + getWidth();
    }

    /**
     * Gets the right side of the tetronimo (that has a square) based upon its x-Coordinate and additional logic.
     * @return right side x-Coordinate of the tetromino.
     */
    public int getRightFilled() {
        int index = xCoordinate + getWidth();
        for (int columns = getWidth() - 1; columns >= 0; columns--) {
            for (int rows = 0; rows < getHeight() - 1; rows++) {
                if (getType()[rows][columns] == 1) {
                    return index;
                }
            }
            index--;
        }
        return index;
    }
}
