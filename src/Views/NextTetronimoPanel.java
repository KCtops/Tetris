package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * NextTetronimoPanel.java
 * Panel to display the next tetronimo.
 * @author Kyle Collamer
 */
public class NextTetronimoPanel extends JPanel implements ActionListener {

    //Number of rows of the display.
    private final int NUM_ROWS;

    //Number of columns of the display.
    private final int NUM_COLUMNS;

    //Size of each square.
    private final int TETRONIMO_SIZE;

    //Grid to get the next tetronimo.
    private final Grid grid;

    //Timer to update the panel.
    Timer timer;

    /**
     * Constructor to create the panel and start the timer.
     * @param grid to get the next tetronimo.
     */
    public NextTetronimoPanel(Grid grid) {

        //Creates the panel.
        this.setBounds(500, 25, 150, 200);
        this.grid = grid;
        NUM_ROWS = 4;
        NUM_COLUMNS = 3;
        TETRONIMO_SIZE = 50;

        //Starts the timer.
        timer = new Timer(250, this);
        timer.start();
    }

    /**
     * Overriding method to paint the panel with the next tetronimo.
     * @param graphics  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(3));
        if (grid.getNextTetronimo() == null) {
            for (int rows = 0; rows < NUM_ROWS; rows++) {
                for (int columns = 0; columns < NUM_COLUMNS; columns++) {
                    graphics2D.setColor(Color.LIGHT_GRAY);
                    graphics2D.fillRect(columns * TETRONIMO_SIZE, rows * TETRONIMO_SIZE, TETRONIMO_SIZE, TETRONIMO_SIZE);
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(columns * TETRONIMO_SIZE, rows * TETRONIMO_SIZE, TETRONIMO_SIZE, TETRONIMO_SIZE);
                }
            }
        } else {
            for (int rows = 0; rows < grid.getNextTetronimo().getHeight(); rows++) {
                for (int columns = 0; columns < grid.getNextTetronimo().getWidth(); columns++) {
                    if (grid.getNextTetronimo().getType()[rows][columns] == 1) {
                        graphics2D.setColor(grid.getNextTetronimo().getColor());
                        graphics2D.fillRect(columns * TETRONIMO_SIZE, rows * TETRONIMO_SIZE, TETRONIMO_SIZE, TETRONIMO_SIZE);
                        graphics2D.setColor(Color.BLACK);
                        graphics2D.drawRect(columns * TETRONIMO_SIZE, rows * TETRONIMO_SIZE, TETRONIMO_SIZE, TETRONIMO_SIZE);
                    }
                }
            }
        }
    }

    /**
     * Repaints the panel upon every timer pulse.
     * @param actionEvent the timer pulse.
     */
    @Override public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
