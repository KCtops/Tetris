package Controller;

import Views.Grid;
import Views.NextTetronimoPanel;
import Views.PointsEarnedLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * TetrisFrame.java
 * Main window that encompasses the Tetris game.
 * @author Kyle Collamer
 */
public class TetrisFrame extends JFrame {

    //Final variable to track the grid.
    private final Grid grid;

    //Final variable to track the user-entered delay from Main.java
    private final double delay;

    /**
     * Constructor to set up the appearance of the game, add the grid, add the next tetronimo display, and the score board.
     * @param delay user-entered delay.
     */
    public TetrisFrame(double delay) {

        //Set up the frame.
        this.setTitle("Tetris");
        this.setSize(700,900);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.delay = delay;

        //Adds the grid.
        grid = new Grid();
        this.add(grid);

        //Adds the points earned label.
        this.add(new PointsEarnedLabel(grid));

        //Adds the next tetronimo panel.
        this.add(new NextTetronimoPanel(grid));

        //Adds the controls.
        controls();

        //Starts the game.
        startGame();
    }

    /**
     * Method to start the TetrisThread.
     */
    public void startGame() {
        new TetrisThread(grid, delay).start();
    }

    /**
     * Method to add the controls to the game.
     */
    private void controls() {
        InputMap inputMap = this.getRootPane().getInputMap();
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");

        class Up extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.rotateTetronimo();
            }
        }
        class Down extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.pushTetronimoDown();
            }
        }
        class Right extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.transferTetronimoRight();
            }
        }
        class Left extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.transferTetronimoLeft();
            }
        }
        actionMap.put("up", new Up());
        actionMap.put("down", new Down());
        actionMap.put("right", new Right());
        actionMap.put("left", new Left());
    }
}
