package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverTest {
  private Driver driver;
  private String[] args;

  @BeforeEach
  void setup() {
    driver = new Driver();
    args = new String[] {};
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void testDriver() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    String expectedOutput = "Welcome to my BattleSalvo game for OOD!\n"
        + "Please enter a row and a column value to represent your board size:\n"
        + "*-------------------------------------------------*\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 8 and there must be at least one ship\n"
        + "*-------------------------------------------------*\n";

    try {
      Driver.main(args);
    } catch (NullPointerException e) {
      assertEquals("Cannot invoke \"String.split(String)\" because \"shipsString\" is null",
          e.getMessage());
    }

    // Assert that the console output matches the expected output
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testRun() {
    String consoleInput = "10 10\n2 2 2 2";
    InputStream inputStream = new ByteArrayInputStream(consoleInput.getBytes());
    System.setIn(inputStream);

  }

}