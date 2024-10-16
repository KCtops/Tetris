package Views;

import javax.swing.*;
import java.awt.*;

/**
 * GameOverFrame.java
 * Frame to notify the player the game is over and displays the final points.
 * @author Kyle Collamer
 */
public class GameOverFrame extends JFrame {

    //Grid to get the final points.
    Grid grid;

    /**
     * Constructor to set up the frame and display the final points.
     * @param grid to get the final points.
     */
    public GameOverFrame(Grid grid) {

        //Setting up the frame.
        this.grid = grid;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,200);
        this.setTitle("Game Over");
        this.setVisible(true);

        //Setting up the label to notify the player the game is over.
        JLabel gameOverLabel = new JLabel("GAME OVER!");
        gameOverLabel.setSize(500,100);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.TOP);
        gameOverLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        this.add(gameOverLabel);

        //Setting up the label for the final points.
        JLabel finalPointsLabel = new JLabel("Final points : " + grid.getPointsEarned());
        finalPointsLabel.setSize(500,100);
        finalPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        finalPointsLabel.setVerticalAlignment(SwingConstants.CENTER);
        finalPointsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        this.add(finalPointsLabel);
    }
}
