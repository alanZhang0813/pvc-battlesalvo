package cs3500.pa03;

import cs3500.pa03.controller.GameController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    System.out.println("Welcome to my BattleSalvo game for OOD!");
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    Appendable outputStream = new PrintStream(System.out);
    GameController controller = new GameController(inputReader, outputStream);
    controller.run();
  }
}