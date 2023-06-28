package cs3500.pa03.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {
  private ShipType name;
  private List<Coord> location;
  private Orientation orientation;

  public Ship(ShipType name) {
    this.name = name;
    this.orientation = randOrientation();
    this.location = new ArrayList<>();
  }

  public void addLocation(Coord newLocation) {
    this.location.add(newLocation);
  }

  public List<Coord> getLocation() {
    return location;
  }

  public void setLocation(List<Coord> location) {
    this.location = location;
  }

  public ShipType getName() {
    return name;
  }

  public void setName(ShipType name) {
    this.name = name;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  private Orientation randOrientation() {
    Random rand = new Random();
    int randInt = rand.nextInt(2);
    if (randInt == 0) {
      return Orientation.HORIZONTAL;
    } else {
      return Orientation.VERTICAL;
    }
  }
}