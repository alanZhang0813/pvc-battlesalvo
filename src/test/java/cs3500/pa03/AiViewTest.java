package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.AiView;
import cs3500.pa03.view.Board;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the AiView class
 */
public class AiViewTest {
  StringBuilder sb;
  AiView av;

  @BeforeEach
  void setup() {
    sb = new StringBuilder();
    av = new AiView(sb);
  }

  @Test
  void testView() {
    List<Ship> listOfShips = new ArrayList<>();
    Ship ship1 = new Ship(ShipType.CARRIER);
    Ship ship2 = new Ship(ShipType.BATTLESHIP);
    Ship ship3 = new Ship(ShipType.DESTROYER);
    Ship ship4 = new Ship(ShipType.SUBMARINE);
    listOfShips.add(ship1);
    listOfShips.add(ship2);
    listOfShips.add(ship3);
    listOfShips.add(ship4);
    Board board = new Board(4, 4, listOfShips);
    av.view(board);
    assertEquals("____\n____\n____\n____\n", sb.toString());
  }
}
