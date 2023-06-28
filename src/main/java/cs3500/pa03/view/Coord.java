package cs3500.pa03.view;

public class Coord {
  private final int x;
  private final int y;
  private ShipType status;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean isOccupied() {
    return
        status.equals(ShipType.HIT) ||
        status.equals(ShipType.EMPTY) ||
        status.equals(ShipType.SUNK) ||
        status.equals(ShipType.MISS);
  }
  
  public ShipType getStatus() {
    return status;
  }

  public void setStatus(ShipType status) {
    this.status = status;
  }

  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }
}
