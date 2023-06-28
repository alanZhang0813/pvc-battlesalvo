package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.view.Coord;
import cs3500.pa03.view.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the Coord class
 */
public class CoordTest {
  Coord coord;

  @BeforeEach
  void setup() {
    coord = new Coord(0, 0);
  }

  @Test
  void testIsOccupied() {
    coord.setStatus(ShipType.HIT);
    assertTrue(coord.isOccupied());
    coord.setStatus(ShipType.EMPTY);
    assertTrue(coord.isOccupied());
    coord.setStatus(ShipType.SUNK);
    assertTrue(coord.isOccupied());
    coord.setStatus(ShipType.MISS);
    assertTrue(coord.isOccupied());

    coord.setStatus(ShipType.CARRIER);
    assertFalse(coord.isOccupied());
  }

  @Test
  void testToString() {
    assertEquals("(0,0)", coord.toString());
  }
}