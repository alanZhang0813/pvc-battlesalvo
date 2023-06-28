package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.ShipType;
import org.junit.jupiter.api.Test;

/**
 * Test for ShipType class
 */
public class ShipTypeTest {
  @Test
  void testToString() {
    ShipType shipType1 = ShipType.CARRIER;
    assertEquals("C", shipType1.toString());
    ShipType shipType2 = ShipType.BATTLESHIP;
    assertEquals("B", shipType2.toString());
    ShipType shipType3 = ShipType.DESTROYER;
    assertEquals("D", shipType3.toString());
    ShipType shipType4 = ShipType.SUBMARINE;
    assertEquals("S", shipType4.toString());
    ShipType shipType5 = ShipType.HIT;
    assertEquals("X", shipType5.toString());
    ShipType shipType6 = ShipType.MISS;
    assertEquals("O", shipType6.toString());
  }
}
