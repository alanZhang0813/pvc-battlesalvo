package cs3500.pa03.view;

import java.util.List;
import java.util.Random;

public class Board {
  private final int rowSize;
  private final int colSize;
  private Coord[][] coords;
  private final List<Ship> listOfShips;

  public Board(int rowSize, int colSize, List<Ship> listOfShips) {
    this.rowSize = rowSize;
    this.colSize = colSize;
    this.listOfShips = listOfShips;
    this.initBoard();
  }

  /**
   * Makes every spot in the board a new Coord with its associated r and c values,
   * as well as with the EMPTY status, so it prints out as a "_"
   */
  private void initBoard() {
    coords = new Coord[rowSize][colSize];
    for (int r = 0; r < rowSize; r++) {
      for (int c = 0; c < colSize; c++) {
        Coord currCoord = new Coord(r, c);
        currCoord.setStatus(ShipType.EMPTY);
        coords[r][c] = currCoord;
      }
    }
  }

  public void placeShips() {
    for (Ship ship : listOfShips) {
      boolean emptyCoord = false;
      while (!emptyCoord) {
        Random rand = new Random();
        int randRow;
        int randCol;

        if (ship.getOrientation().equals(Orientation.HORIZONTAL)) {
          //checks if it is horizontal
          randRow = rand.nextInt(rowSize);
          randCol = rand.nextInt(colSize - ship.getName().getSize() + 1);

          if (isCoordEmpty(randRow, randCol, ship)) {
            emptyCoord = true;
            for (int i = 0; i < ship.getName().getSize(); i++) {
              coords[randRow][randCol + i].setStatus(ship.getName());
              ship.addLocation(coords[randRow][randCol + i]);
            }
          }
        }
        else if (ship.getOrientation().equals(Orientation.VERTICAL)) {
          //since not horizontal, must be vertical
          randRow = rand.nextInt(rowSize - ship.getName().getSize() + 1);
          randCol = rand.nextInt(colSize);

          if (isCoordEmpty(randRow, randCol, ship)) {
            emptyCoord = true;
            for (int i = 0; i < ship.getName().getSize(); i++) {
              coords[randRow + i][randCol].setStatus(ship.getName());
              ship.addLocation(coords[randRow + i][randCol]);
            }
          }
        }
      }
    }
  }

  private boolean isCoordEmpty(int rowNum, int colNum, Ship ship) {
    if (ship.getOrientation().equals(Orientation.VERTICAL)) {
      for (int r = 0; r < ship.getName().getSize(); r++) {
        if (!coords[rowNum + r][colNum].getStatus().equals(ShipType.EMPTY)) {
          return false;
        }
      }
    } else {
      for (int c = 0; c < ship.getName().getSize(); c++) {
        if (!coords[rowNum][colNum + c].getStatus().equals(ShipType.EMPTY)) {
          return false;
        }
      }
    }
    return coords[rowNum][colNum].getStatus().equals(ShipType.EMPTY);
  }

  public String toString() {
    String result = "";
    for (int r = 0; r < rowSize; r++) {
      for (int c = 0; c < colSize; c++) {
        result = result.concat(coords[r][c].getStatus().toString());
      }
      result = result.concat("\n");
    }
    return result;
  }

  public boolean isEmpty() {
    for (int r = 0; r < coords.length; r++) {
      for (int c = 0; c < coords[r].length; c++) {
        Coord currCoord = coords[r][c];
        boolean isAnything =
            currCoord.getStatus().equals(ShipType.HIT) ||
            currCoord.getStatus().equals(ShipType.EMPTY) ||
            currCoord.getStatus().equals(ShipType.SUNK) ||
            currCoord.getStatus().equals(ShipType.MISS);
        if (!isAnything) {
          return false;
        }
      }
    }
    return true;
  }

  public void placeShots(List<Coord> shotCoords) {
    for (int r = 0; r < coords.length; r++) {
      for (int c = 0; c < coords[r].length; c++) {
        Coord currCoord = coords[r][c];
        if (shotCoords.contains(currCoord)) {
          if (currCoord.isOccupied()) {
            currCoord.setStatus(ShipType.HIT);
          } else {
            currCoord.setStatus(ShipType.MISS);
          }
        }
      }
    }
  }

  public Coord[][] getCoords() {
    return this.coords;
  }

  public void setCoords(Coord[][] coords) {
    this.coords = coords;
  }
}