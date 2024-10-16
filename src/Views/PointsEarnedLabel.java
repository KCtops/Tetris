package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PointsEarnedLabel.java
 * JLabel to show the player the points earned.
 * @author Kyle Collamer
 */
public class PointsEarnedLabel extends JLabel implements ActionListener {

    //Grid to get the points earned.
    Grid grid;

    //Timer to continuously update the points earned.
    Timer timer;

    /**
     * Constructor that sets up the PointsEarnedLabel.
     * @param grid for the score.
     */
    public PointsEarnedLabel(Grid grid) {

        //Sets up the grid.
        this.grid = grid;
        this.setBounds(450,620,200,50);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.setLayout(new BorderLayout());

        //Starts the timer.
        timer = new Timer(250, this);
        timer.start();

    }

    /**
     * Invoked (Updating the score) when an action occurs.
     * @param e ActionEvent
     */
    @Override public void actionPerformed(ActionEvent e) {
        setText("Score : " + grid.getPointsEarned());
    }
}
