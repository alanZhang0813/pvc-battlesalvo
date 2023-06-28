package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa03.controller.ManualPlayer;
import cs3500.pa03.view.Coord;
import cs3500.pa03.view.GameResult;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for ManualPlayer
 */
public class ManualPlayerTest {
  private BufferedReader bf;
  private ManualPlayer mp;
  private List<Ship> listOfShips;
  private Map<ShipType, Integer> specifications;
  private ByteArrayOutputStream outputStream;
  private PrintStream printStream;

  @BeforeEach
  void setup() {
    bf = new BufferedReader(new StringReader("1 1 1 1"));
    mp = new ManualPlayer(bf);
    listOfShips = new ArrayList<>();
    specifications = new HashMap<>();
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
    System.setOut(printStream);
  }

  @Test
  void testPlayerSetup() {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 2);
    listOfShips = mp.setup(10, 10, specifications);
    assertEquals(8, listOfShips.size());
  }

  @Test
  void testName() {
    String name = mp.name();
    assertNull(name);
  }

  @Test
  void testTakeShots() {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 2);
    List<Coord> expectedCoords = new ArrayList<>();
    expectedCoords.add(new Coord(1, 2));
    expectedCoords.add(new Coord(3, 4));
    mp.setup(6, 6, specifications);

    List<Coord> shotCoords = mp.takeShots();

    assertEquals(0, shotCoords.size());
  }

  @Test
  void testSuccessfulHitsNoHits() {
    List<Coord> shots = new ArrayList<>();
    mp.successfulHits(shots);
    assertEquals("\nYour shots all missed!", outputStream.toString());
  }

  @Test
  void testSuccessfulHitsWithHits() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(1, 2));
    shots.add(new Coord(3, 4));
    mp.successfulHits(shots);
    assertEquals("\nYou hit your opponent: \n(1,2)\n(3,4)\n", outputStream.toString());
  }

  @Test
  void testReportDamage() {
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    listOfShips = mp.setup(6, 6, specifications);
    List<Coord> opponentShots = new ArrayList<>();
    List<Coord> reportedDamage = mp.reportDamage(opponentShots);
    assertEquals(0, reportedDamage.size());
  }

  @Test
  void testEndGame() {
    mp.endGame(GameResult.WIN, "cuz i said so");
  }
}
