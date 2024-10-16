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
        double delay = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What do you want the delay to be in seconds?");
            System.out.print("ENTER DELAY : ");
            delay = scanner.nextDouble();
            if (delay < 0.125) {
                System.out.println("ERROR : Delay is too fast.");
                System.out.println();
            }
        } while (delay < 0.125);
        new TetrisFrame(delay);
    }
}
