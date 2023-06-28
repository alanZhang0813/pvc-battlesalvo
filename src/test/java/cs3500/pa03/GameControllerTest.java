package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.controller.GameController;
import cs3500.pa03.view.ShipType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import java.io.BufferedReader;
import org.junit.jupiter.api.Test;

/**
 * Test for the GameController class
 */
public class GameControllerTest {
  private BufferedReader inputReader;
  private ByteArrayOutputStream output;
  private Appendable appendable;
  private GameController gameController;

  @BeforeEach
  void setup() {
    inputReader = new BufferedReader(new InputStreamReader(System.in));
    output = new ByteArrayOutputStream();
    appendable = new PrintStream(output);
    gameController = new GameController(inputReader, appendable);
  }

  @Test
  void testRun() {
    String input = "10 10\n" + "2 1 1 1\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    try {
      gameController.run();
    } catch (NullPointerException e) {
      assertEquals("Cannot invoke \"String.split(String)\" because "
          + "\"shipsString\" is null", e.getMessage());
    }
    assertEquals("Please enter a row and a column value to represent your board size:\n"
        + "*-------------------------------------------------*\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 8 and there must be at least one ship\n"
        + "*-------------------------------------------------*\n", output.toString());
  }

  @Test
  void testRunInvalidShipDimensions() {
    String input = "10 10\n" + "2 1 1 0\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    try {
      gameController.run();
    } catch (NullPointerException e) {
      assertEquals("Cannot invoke \"String.split(String)\" because "
          + "\"shipsString\" is null", e.getMessage());
    }
    assertEquals("Please enter a row and a column value to represent your board size:\n"
        + "*-------------------------------------------------*\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 8 and there must be at least one ship\n"
        + "*-------------------------------------------------*\n", output.toString());
  }

  @Test
  void testRunInvalidDimensions() {
    String input = "10\n10";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    try {
      gameController.run();
    } catch (NullPointerException e) {
      assertEquals("Cannot invoke \"String.split(String)\" because "
          + "\"input\" is null", e.getMessage());
    }
    assertEquals("Please enter a row and a column value to represent your board size:\n"
        + "*-------------------------------------------------*\n"
        + "That is an invalid number of inputs!\n"
        + "*-------------------------------------------------*\n"
        + "That is an invalid number of inputs!\n"
        + "*-------------------------------------------------*\n", output.toString());
  }

  @Test
  void testGetDimensions() {
    String[] inputs = gameController.getDimensions(
        new BufferedReader(new StringReader("10 12")));
    assertArrayEquals(new String[]{"10", "12"}, inputs);
  }

  @Test
  void testIsAppropriate() {
    boolean result = gameController.isAppropriate(new String[]{"8", "10"});
    assertTrue(result);
    result = gameController.isAppropriate(new String[]{"5", "5"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"5", "10"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"10", "5"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"5", "10"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"16", "16"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"16", "8"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"8", "16"});
    assertFalse(result);
    result = gameController.isAppropriate(new String[]{"5", "10", "4"});
    assertFalse(result);
  }

  @Test
  void testGetShips() {
    String input = "2 1 3 2\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Map<ShipType, Integer> ships = gameController.getShips();
    assertEquals(2, ships.get(ShipType.CARRIER));
    assertEquals(1, ships.get(ShipType.BATTLESHIP));
    assertEquals(3, ships.get(ShipType.DESTROYER));
    assertEquals(2, ships.get(ShipType.SUBMARINE));
  }

  @Test
  void testIsValidFleetSizeValidInput() {
    String shipsString = "2 2 2 2";
    boolean isValidFleetSize = gameController.isValidFleetSize(shipsString);
    assertTrue(isValidFleetSize);
  }

  @Test
  void testIsValidFleetSizeInvalidInput() {
    String shipsString = "2 0 2 2";
    boolean isValidFleetSize = gameController.isValidFleetSize(shipsString);
    assertFalse(isValidFleetSize);
  }

  @Test
  void testIsValidFleetSizeInValidFormat() {
    String shipsString = "2 a 2 2";
    boolean isValidFleetSize = gameController.isValidFleetSize(shipsString);
    assertFalse(isValidFleetSize);
  }

  @Test
  void testIsValidFleetSizeInvalidTotalSize() {
    String shipsString = "4 4 1 1";
    boolean isValidFleetSize = gameController.isValidFleetSize(shipsString);
    assertFalse(isValidFleetSize);

    shipsString = "-1 -1 -1 -1";
    isValidFleetSize = gameController.isValidFleetSize(shipsString);
    assertFalse(isValidFleetSize);
  }

  @Test
  void testIsValidFleetSizeInvalidInputLength() {
    String shipsString = "1 2 3";
    boolean isValidFleetSize = gameController.isValidFleetSize(shipsString);
    assertFalse(isValidFleetSize);
  }
}