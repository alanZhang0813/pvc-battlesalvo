package cs3500.pa03.model;

import cs3500.pa03.view.Coord;
import cs3500.pa03.view.GameResult;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AiPlayer extends AbstractPlayer {
  private List<Ship> listOfShips;
  private int rowSize;
  private int colSize;

  @Override
  public String name() {
    return null;
  }

  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> resultShips = super.setup(height, width, specifications);
    listOfShips = resultShips;
    rowSize = height;
    colSize = width;
    return resultShips;
  }

  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> aiShotCoords = new ArrayList<>();
    while (aiShotCoords.size() < listOfShips.size()) {
      Random rand = new Random();
      int randRow = rand.nextInt(rowSize);
      int randCol = rand.nextInt(colSize);
      Coord newCoord = new Coord(randRow, randCol);
      if (!aiShotCoords.contains(newCoord)) {
        aiShotCoords.add(newCoord);
      }
    }
    return aiShotCoords;
  }

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hitShips = new ArrayList<>();
    for (Ship ship : listOfShips) {
      for (Coord coord : ship.getLocation()) {
        if (opponentShotsOnBoard.contains(coord)) {
          hitShips.add(coord);
        }
      }
    }
    return hitShips;
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    Appendable output = new PrintStream(System.out);
    try {
      output.append("\n");
      if (shotsThatHitOpponentShips.isEmpty()) {
        output.append("Your opponent's shots all missed!");
      } else {
        output.append("Your opponent hit you at: \n");
        for (Coord coord : shotsThatHitOpponentShips) {
          output.append(coord.toString());
          output.append("\n");
        }
      }
      output.append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void endGame(GameResult result, String reason) {

  }
}
