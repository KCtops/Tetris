import Controller.TetrisFrame;

import java.util.Scanner;

/**
 * Main.java
 * Main class to get the user-entered delay and start the game.
 * @author Kyle Collamer
 */
public class Main {
    /**
     * Main method to get the user-entered delay and start the game.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        double delay = 0;    // initalize delay to zero.
        do {
            Scanner scanner = new Scanner(System.in);    // initalize new scanner to get double.
            System.out.println("What do you want the delay to be in seconds?");    // prompts the user to enter the delay.
            System.out.print("ENTER DELAY : ");    // prompts the user to enter the delay.
            delay = scanner.nextDouble();    // get the double the user enters.
            if (delay < 0.125) {    // if the delay is too fast...
                System.out.println("ERROR : Delay is too fast.");    // ...notify the user that the delay is too fast.
                System.out.println();    // print a space.
            }
        } while (delay < 0.125);    // while the entered delay is invalid...
        new TetrisFrame(delay);    // create a new Tetris game.
    }
}
