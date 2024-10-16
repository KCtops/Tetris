package Controller;

import Views.Grid;

/**
 * Controller.TetrisThread.java
 * Thread that runs through the game and ends when the game is over.
 * @author Kyle Collamer
 */
public class TetrisThread extends Thread{

    //Variable that stores the grid.
    private final Grid grid;

    //Variable that stores the delay of Tetronimos dropping.
    private final double delay;

    /**
     * Constructor that takes in the Tetris grid and delay.
     * @param grid Tetris grid.
     * @param delay delay of dropping Tetronimos.
     */
    public TetrisThread(Grid grid, double delay) {

        //Sets up the thread.
        this.grid = grid;
        this.setName("Tetris");
        this.delay = delay * 1000;

        //Generates the initial tetronimo.
        grid.generateTetronimo();
        grid.spawnTetronimo();
        grid.generateNextTetronimo();
    }

    /**
     * Override method to run the thread.
     */
    @Override public void run(){
        while (true) {

            //Deletes any full rows.
            grid.deleteRows();

            //Spawns the next tetronimo created last iteration.
            grid.spawnTetronimo(grid.getNextTetronimo());

            //Creates the next tetronimo for the next iteration.
            grid.generateNextTetronimo();

            //Transfers the tetronimo down.
            while (grid.transferTetronimoDown()) {
                try {
                    sleep((long)delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //Breaks the loop if the game is over.
            if (grid.gameOver()) {
                break;
            }

            //Settles the fallen tetronimo.
            grid.settleTetronimo();
        }
    }
}
