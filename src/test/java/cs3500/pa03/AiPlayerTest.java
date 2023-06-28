package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.view.Coord;
import cs3500.pa03.view.GameResult;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for AiPlayer
 */
public class AiPlayerTest {
  private AiPlayer ap;
  private List<Ship> listOfShips;
  private Map<ShipType, Integer> specifications;
  private ByteArrayOutputStream outputStream;
  private PrintStream printStream;

  @BeforeEach
  void setup() {
    ap = new AiPlayer();
    listOfShips = new ArrayList<>();
    specifications = new HashMap<>();
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
    System.setOut(printStream);
  }

  @Test
  void testPlayerSetup() {
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 2);
    listOfShips = ap.setup(10, 10, specifications);
    assertEquals(8, listOfShips.size());
  }

  @Test
  void testName() {
    String name = ap.name();
    assertNull(name);
  }

  @Test
  void testTakeShots() {
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 2);
    listOfShips = ap.setup(10, 10, specifications);
    List<Coord> aiShotCoords = ap.takeShots();
    assertEquals(8, aiShotCoords.size());
  }

  @Test
  void testSuccessfulHitsNoHits() {
    List<Coord> shots = new ArrayList<>();
    ap.successfulHits(shots);
    assertEquals("\nYour opponent's shots all missed!\n", outputStream.toString());
  }

  @Test
  void testSuccessfulHitsWithHits() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(1, 2));
    shots.add(new Coord(3, 4));
    ap.successfulHits(shots);
    assertEquals("\nYour opponent hit you at: \n(1,2)\n(3,4)\n\n", outputStream.toString());
  }

  @Test
  void testReportDamage() {
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    listOfShips = ap.setup(6, 6, specifications);
    List<Coord> opponentShots = new ArrayList<>();
    List<Coord> reportedDamage = ap.reportDamage(opponentShots);
    assertEquals(0, reportedDamage.size());
  }

  @Test
  void testEndGame() {
    ap.endGame(GameResult.WIN, "cuz i said so");
  }
}
