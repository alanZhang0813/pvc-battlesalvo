package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.Orientation;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the Ship class
 */
public class ShipTest {
  private Ship ship1;

  @BeforeEach
  void setup() {
    ship1 = new Ship(ShipType.EMPTY);
  }

  @Test
  void testSetLocation() {
    ship1.setLocation(new ArrayList<>());
    assertEquals(0, ship1.getLocation().size());
  }

  @Test
  void testSetName() {
    ship1.setName(ShipType.CARRIER);
    assertEquals(ShipType.CARRIER, ship1.getName());
  }

  @Test
  void testSetOrientation() {
    ship1.setOrientation(Orientation.HORIZONTAL);
    assertEquals(Orientation.HORIZONTAL, ship1.getOrientation());
  }
}
