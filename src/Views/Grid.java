package Views;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Grid.java
 * JPanel for the actual Tetris grid.
 * @author Kyle Collamer
 */
public class Grid extends JPanel {

    //Number of rows of the grid.
    private final int NUM_ROWS;

    //Number of columns of the grid.
    private final int NUM_COLUMNS;

    //Size of each square.
    private final int TETRONIMO_SIZE;

    //Tetromino currently being interacted with.
    private Tetronimo tetronimo;

    //Tetronimo that is in queue.
    private Tetronimo nextTetronimo;

    //Array of color arrays depicting the settled tetronimos.
    private final Color[][] settledTetronimos;

    //Amount of rows deleted in a game.
    private int deletedRows;

    //Amount of points earned in the game.
    private int pointsEarned;

    //Last tetromino ID (used to remove repeating tetromino spawns)
    private int lastTetronimoID = 1;

    //Last color ID (used to remove repeating color spawns)
    private int lastColorID = 1;

    /**
     * Constructor to create the grid.
     */
    public Grid() {
        //Sets the appearance of the Grid.
        this.setBounds(25,25,400,800);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());

        //Setting the layout of the Grid.
        NUM_COLUMNS = 10;
        TETRONIMO_SIZE = this.getWidth() / NUM_COLUMNS;
        NUM_ROWS = this.getHeight() / TETRONIMO_SIZE;
        settledTetronimos = new Color[NUM_ROWS][NUM_COLUMNS];

        //Initializes the deleted rows (for testing) and points earned to zero.
        deletedRows = 0;
        pointsEarned = 0;
    }

    /**
     * Method that spawns a tetronimo.
     */
    public void spawnTetronimo() {
        tetronimo.createTetronimo(NUM_COLUMNS);
    }

    /**
     * Method that spawns a tetronimo.
     * @param nextTetromino tetronimo to spawn.
     */
    public void spawnTetronimo(Tetronimo nextTetromino) {
        tetronimo = nextTetromino;
        tetronimo.createTetronimo(NUM_COLUMNS);
    }

    /**
     * Method to see if the tetromino collides with the bottom of the grid or another tetromino.
     * @return whether the tetromino collides with the bottom of the grid or another tetromino.
     */
    private boolean collidesBottom() {
        if (tetronimo != null) {
            //If the tetronimo simply collides with the bottom of the grid.
            if (tetronimo.getYCoordinate() + tetronimo.getHeight() == NUM_ROWS) {
                return false;
            }

            //If the tetronimo collides with the settled tetronimos.
            for (int columns = 0; columns < tetronimo.getWidth(); columns++) {
                for (int rows = tetronimo.getHeight() - 1; rows >= 0; rows--) {
                    if (tetronimo.getType()[rows][columns] != 0) {
                        if (rows + tetronimo.getYCoordinate() + 1 <= 0) {
                            break;
                        }
                        if (settledTetronimos[rows + tetronimo.getYCoordinate() + 1][columns + tetronimo.getXCoordinate()] != null) {
                            return false;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks whether the tetronimo collides the left end of the playing area or the settled tetronimos.
     * @return whether the tetronimo collides the left end of the playing area or the settled tetronimos.
     */
    private boolean collidesLeft() {
        //Checks whether the tetronimo collides the left end of the grid.
        if (tetronimo.getXCoordinate() == 0) {
            return true;
        }

        //Checks whether the tetronimo collides with the right side of the settled tetronimos.
        for (int rows = tetronimo.getHeight() - 1; rows >= 0; rows--) {
            for (int columns = tetronimo.getWidth() - 1; columns >= 0; columns--) {
                if (tetronimo.getType()[rows][columns] != 0) {
                    if (rows + tetronimo.getYCoordinate() < 0) {
                        break;
                    }
                    if (settledTetronimos[rows + tetronimo.getYCoordinate()][columns + tetronimo.getXCoordinate() - 1] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the tetronimo collides the right end of the playing area or the settled tetronimos.
     * @return whether the tetronimo collides the right end of the playing area or the settled tetronimos.
     */
    private boolean collidesRight() {
        //Checks whether the tetronimo collides with the right side of the grid.
        if (tetronimo.getRightFilled() == NUM_COLUMNS || tetronimo.getRight() == NUM_COLUMNS) {
            return true;
        }

        //Checks whether the tetronimo collides with the left side of the settled tetronimos.
        for (int rows = tetronimo.getHeight() - 1; rows >= 0; rows--) {
            for (int columns = tetronimo.getWidth() - 1; columns >= 0; columns--) {
                if (tetronimo.getType()[rows][columns] != 0) {
                    if (rows + tetronimo.getYCoordinate() < 0) {
                        break;
                    }
                    if (settledTetronimos[rows + tetronimo.getYCoordinate()][columns + tetronimo.getXCoordinate() + 1] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Settles the tetromino down.
     */
    public void settleTetronimo() {
        if (tetronimo != null) {
            for (int rows = 0; rows < tetronimo.getHeight(); rows++) {
                for (int columns = 0; columns < tetronimo.getWidth(); columns++) {
                    if (tetronimo.getType()[rows][columns] == 1) {
                        settledTetronimos[tetronimo.getYCoordinate() + rows][tetronimo.getXCoordinate() + columns] = tetronimo.getColor();
                    }
                }
            }
        }
    }

    /**
     * Automatic method that transfers the tetromino down.
     * @return whether the tetromino can go down further.
     */
    public boolean transferTetronimoDown() {
        if (!collidesBottom()) {
            return false;
        }
        if (tetronimo != null) {
            tetronimo.transferDown();
        }
        repaint();
        return true;
    }

    /**
     * Transfers the tetromino right by using the right arrow key.
     */
    public void transferTetronimoRight() {
        if (tetronimo == null) {
            return;
        }
        if (collidesRight()) {
            System.out.println("ERROR : Collides with right side.");
            return;
        }
        tetronimo.transferRight();
        repaint();
    }

    /**
     * Transfers the tetromino left by using the left arrow key.
     */
    public void transferTetronimoLeft() {
        if (tetronimo == null) {
            return;
        }
        if (collidesLeft()) {
            System.out.println("ERROR : Collides with left side.");
            return;
        }
        tetronimo.transferLeft();
        repaint();
    }

    /**
     * Rotates the tetronimo by using the up arrow key by calling each individual overriding method after checking to see if it is possible.
     */
    public void rotateTetronimo() {
        tetronimo.rotate(collidesRight(), collidesLeft(), tetronimo.getXCoordinate(), NUM_COLUMNS);
        repaint();
    }

    /**
     * Forces the tetromino down by the down arrow key.
     */
    public void pushTetronimoDown() {
        while (collidesBottom()) {
            if (tetronimo != null) {
                tetronimo.transferDown();
            }
        }
        repaint();
    }

    /**
     * Draws the falling tetronimo.
     * @param graphics used to draw the falling tetronimo.
     */
    private void drawTetronimo(Graphics graphics) {
        if (tetronimo != null) {
            for (int rows = 0; rows < tetronimo.getHeight(); rows++) {
                for (int columns = 0; columns < tetronimo.getWidth(); columns++) {
                    if (tetronimo.getType()[rows][columns] == 1) {
                        graphics.setColor(tetronimo.getColor());
                        graphics.fillRect((tetronimo.getXCoordinate() + columns) * TETRONIMO_SIZE,
                                (tetronimo.getYCoordinate() + rows) * TETRONIMO_SIZE,
                                TETRONIMO_SIZE,
                                TETRONIMO_SIZE);
                        graphics.setColor(Color.BLACK);
                        graphics.drawRect((tetronimo.getXCoordinate() + columns) * TETRONIMO_SIZE,
                                (tetronimo.getYCoordinate() + rows) * TETRONIMO_SIZE,
                                TETRONIMO_SIZE,
                                TETRONIMO_SIZE);
                    }
                }
            }
        }
    }

    /**
     * Method that transforms the falling tetronimo into a part of the rest of the settled tetronimos.
     * @param graphics used to draw the settled tetronimos.
     */
    private void drawSettledTetronimos(Graphics graphics) {
        Color color;
        Graphics2D graphics2D = (Graphics2D) graphics;
        for (int rows = 0; rows < NUM_ROWS; rows++) {
            for (int columns = 0; columns < NUM_COLUMNS; columns++) {
                color = settledTetronimos[rows][columns];
                if (color != null) {
                    int xPosition = columns * TETRONIMO_SIZE;
                    int yPosition = rows * TETRONIMO_SIZE;
                    graphics2D.setColor(color);
                    graphics2D.fillRect(xPosition, yPosition, TETRONIMO_SIZE, TETRONIMO_SIZE);
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(xPosition, yPosition, TETRONIMO_SIZE, TETRONIMO_SIZE);
                }
            }
        }
    }

    /**
     * Method that checks a full row, if so, that full row is deleted.
     */
    public boolean deleteRow() {
        boolean fullRow;
        for (int rows = NUM_ROWS - 1; rows >= 0; rows--) {
            fullRow = true;
            for (int columns = 0; columns < NUM_COLUMNS; columns++) {
                if (settledTetronimos[rows][columns] == null) {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) {
                for (int i = 0; i < NUM_COLUMNS; i++) {
                    settledTetronimos[rows][i] = null;
                }
                for (int j = rows; j > 0; j--) {
                    if (NUM_COLUMNS >= 0) System.arraycopy(settledTetronimos[j - 1], 0, settledTetronimos[j], 0, NUM_COLUMNS);
                }
                for (int i = 0; i < NUM_COLUMNS; i++) {
                    settledTetronimos[0][i] = null;
                }
                repaint();
                deletedRows++;
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deletes up to four rows at a time by calling the above deleteRow() method.
     */
    public void deleteRows() {
        int pointsEarnedNow = 0;
        for (int i = 0; i <= 4; i++) {
            if (deleteRow()) {
                pointsEarnedNow += 100;
            }
         }
        if (pointsEarnedNow == 400) {
            pointsEarnedNow = 800;
        }
        pointsEarned += pointsEarnedNow;
        if (pointsEarnedNow > 0) {
            System.out.println("Total deleted rows  : " + deletedRows);
            System.out.println("Total points        : " + pointsEarned);
        }
    }

    /**
     * Method to see if the game is over.
     * @return if the game is over.
     */
    public boolean gameOver() {
        if (tetronimo != null) {
            if (tetronimo.getYCoordinate() < 0) {
                tetronimo = null;

                System.out.println();
                new GameOverFrame(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Generates the type of tetromino
     * @param lastNumber last tetromino ID (used to remove repeating tetromino spawns)
     * @return type of tetromino.
     */
    public Tetronimo generateType(int lastNumber) {
        Random random = new Random();
        int nextNumber;
        do {
            nextNumber = random.nextInt(8) + 1;
            setLastTetronimoID(nextNumber);
        } while(nextNumber == lastNumber);
        if (nextNumber == 1) {
            return new SquareTetronimo();
        } else if (nextNumber == 2) {
            return new StraightTetronimo();
        } else if (nextNumber == 3) {
            return new LTetronimoRightLeaning();
        } else if (nextNumber == 4) {
            return new LTetronimoLeftLeaning();
        } else if (nextNumber == 5) {
            return new TTetronimo();
        } else if (nextNumber == 6) {
            return new SkewTetronimoRightLeaning();
        } else if (nextNumber == 7) {
            return new SkewTetronimoLeftLeaning();
        }
        return null;
    }

    /**
     * Generates the color of the tetromino.
     * @param lastNumber the last color ID in the game (used to remove repeating color spawns).
     * @return the color of the tetromino.
     */
    public Color generateColor(int lastNumber) {
        Random random = new Random();
        int nextNumber;
        do {
            nextNumber = random.nextInt(6) + 1;
            setLastColorID(nextNumber);
        } while(nextNumber == lastNumber);
        if (random.nextInt(6) + 1 == 1) {
            return new Color(255,255,0);
        } else if (random.nextInt(6) + 1 == 2) {
            return new Color(255,0,255);
        } else if (random.nextInt(6) + 1 == 3) {
            return new Color(255,0,0);
        } else if (random.nextInt(6) + 1 == 4) {
            return new Color(0,255,255);
        } else if (random.nextInt(6) + 1 == 5) {
            return new Color(0,255,0);
        } else {
            return new Color(0,0,255);
        }
    }

    /**
     * Method to generate a tetronimo for initial launch.
     */
    public void generateTetronimo() {
        //Assigns a random type.
        do {
            tetronimo = generateType(lastTetronimoID);
        } while (tetronimo == null);

        //Assigns a random color.
        tetronimo.setColor(generateColor(lastColorID));
    }

    /**
     * Method to generate the next tetronimo.
     */
    public void generateNextTetronimo() {
        //Assigns a random type.
        do {
            nextTetronimo = generateType(lastTetronimoID);
        } while (nextTetronimo == null);

        //Assigns a random color.
        nextTetronimo.setColor(generateColor(lastColorID));
    }

    /**
     * Sets the last tetromino ID in the game (used to remove repeating tetromino spawns).
     * @param lastTetronimoID last tetromino ID in the game
     */
    public void setLastTetronimoID(int lastTetronimoID) {
        this.lastTetronimoID = lastTetronimoID;
    }

    /**
     * Sets the last color ID in the game (used to remove repeating color spawns)
     * @param lastColorID the last color ID in the game
     */
    public void setLastColorID(int lastColorID) {
        this.lastColorID = lastColorID;
    }

    /**
     * Method that returns the points earned.
     * @return points earned.
     */
    public int getPointsEarned() {
        return pointsEarned;
    }

    /**
     * Gets the next tetronimo.
     * @return the next tetronimo.
     */
    public Tetronimo getNextTetronimo() {
        return nextTetronimo;
    }

    /**
     * Overriding method to paint the grid.
     * @param graphics the <code>Graphics</code> object to protect
     */
    @Override protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(3));
        for (int rows = 0; rows < NUM_ROWS; rows++) {
            for (int columns = 0; columns < NUM_COLUMNS; columns++) {
                graphics2D.drawRect(columns * TETRONIMO_SIZE, rows * TETRONIMO_SIZE, TETRONIMO_SIZE, TETRONIMO_SIZE);
            }
        }
        drawTetronimo(graphics);
        drawSettledTetronimos(graphics);
    }
}
