package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.view.Board;
import cs3500.pa03.view.Coord;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the Board class
 */
public class BoardTest {
  private List<Ship> listOfShips;
  private Board board;
  private Ship ship1;
  private Ship ship2;
  private Ship ship3;
  private Ship ship4;

  @BeforeEach
  void setup() {
    listOfShips = new ArrayList<>();
    board = new Board(0, 0, listOfShips);
    ship1 = new Ship(ShipType.CARRIER);
    ship2 = new Ship(ShipType.BATTLESHIP);
    ship3 = new Ship(ShipType.DESTROYER);
    ship4 = new Ship(ShipType.SUBMARINE);
    listOfShips.add(ship1);
    listOfShips.add(ship2);
    listOfShips.add(ship3);
    listOfShips.add(ship4);
  }

  @Test
  void testPlaceShots() {
    board = new Board(6, 6, listOfShips);
    Coord coord1 = new Coord(1, 1);
    coord1.setStatus(ShipType.CARRIER);
    Coord coord2 = new Coord(2, 1);
    coord2.setStatus(ShipType.CARRIER);
    Coord coord3 = new Coord(1, 2);
    coord3.setStatus(ShipType.MISS);
    Coord coord4 = new Coord(2, 2);
    coord4.setStatus(ShipType.BATTLESHIP);
    Coord coord5 = new Coord(3, 3);
    coord5.setStatus(ShipType.DESTROYER);
    Coord coord6 = new Coord(2, 2);
    coord5.setStatus(ShipType.DESTROYER);

    Coord[] coordRow1 = new Coord[] {coord1, coord2};
    Coord[] coordRow2 = new Coord[] {coord3, coord4};
    Coord[][] coords = new Coord[][] {coordRow1, coordRow2};
    board.setCoords(coords);

    List<Coord> shotCoords = new ArrayList<>();
    shotCoords.add(coord1);
    shotCoords.add(coord6);
    shotCoords.add(coord5);
    board.placeShots(shotCoords);
    assertEquals(board.getCoords()[1][1].getStatus(), ShipType.BATTLESHIP);
  }

  @Test
  void testPlaceShips() {
    board = new Board(10, 10, listOfShips);
    board.placeShips();
    assertEquals(6, ship1.getLocation().size());
    assertEquals(5, ship2.getLocation().size());
    assertEquals(4, ship3.getLocation().size());
    assertEquals(3, ship4.getLocation().size());
  }

  @Test
  void testToString() {
    board = new Board(6, 6, listOfShips);
    String result = board.toString();
    assertEquals("______\n______\n______\n______\n______\n______\n", result);
  }

  @Test
  void testIsNotEmpty() {
    board = new Board(6, 6, listOfShips);
    Coord coord1 = new Coord(1, 1);
    coord1.setStatus(ShipType.CARRIER);
    Coord coord2 = new Coord(2, 1);
    coord2.setStatus(ShipType.CARRIER);
    Coord coord3 = new Coord(1, 2);
    coord3.setStatus(ShipType.BATTLESHIP);
    Coord coord4 = new Coord(2, 2);
    coord4.setStatus(ShipType.BATTLESHIP);

    Coord[] coordRow1 = new Coord[] {coord1, coord2};
    Coord[] coordRow2 = new Coord[] {coord3, coord4};
    Coord[][] coords = new Coord[][] {coordRow1, coordRow2};
    board.setCoords(coords);
    assertFalse(board.isEmpty());
  }

  @Test
  void testIsEmpty() {
    board = new Board(6, 6, listOfShips);
    Coord coord1 = new Coord(1, 1);
    coord1.setStatus(ShipType.HIT);
    Coord coord2 = new Coord(2, 1);
    coord2.setStatus(ShipType.EMPTY);
    Coord coord3 = new Coord(1, 2);
    coord3.setStatus(ShipType.SUNK);
    Coord coord4 = new Coord(2, 2);
    coord4.setStatus(ShipType.MISS);

    Coord[] coordRow1 = new Coord[] {coord1, coord2};
    Coord[] coordRow2 = new Coord[] {coord3, coord4};
    Coord[][] coords = new Coord[][] {coordRow1, coordRow2};
    board.setCoords(coords);
    assertTrue(board.isEmpty());
  }


}
