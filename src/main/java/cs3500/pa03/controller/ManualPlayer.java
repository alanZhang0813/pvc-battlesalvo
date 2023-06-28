package cs3500.pa03.controller;

import cs3500.pa03.model.AbstractPlayer;
import cs3500.pa03.view.Coord;
import cs3500.pa03.view.GameResult;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManualPlayer extends AbstractPlayer {
  private final BufferedReader inputReader;
  private List<Ship> listOfShips;

  public ManualPlayer(BufferedReader inputReader) {
    this.inputReader = inputReader;
  }

  @Override
  public String name() {
    return null;
  }

  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> resultShips = super.setup(height, width, specifications);
    listOfShips = resultShips;
    return resultShips;
  }

  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> shotCoords = new ArrayList<>();
    String shots = "";
    String[] shotsAtShips = shots.split(" ");
    try {
      while (shotsAtShips.length != listOfShips.size()) {
        System.out.println("Number of ships: " + listOfShips.size());
        shots = inputReader.readLine();
        try {
          shotsAtShips = shots.split(" ");
        } catch (NullPointerException e) {
          return shotCoords;
        }
      }
      for (String coord : shotsAtShips) {
        if (coord.contains("(") && coord.contains(",") && coord.contains(")")) {
          String xPosString = coord.substring(coord.indexOf("(") + 1, coord.indexOf(","));
          String yPosString = coord.substring(coord.indexOf(",") + 1, coord.indexOf(")"));
          int xPos = Integer.parseInt(xPosString.trim());
          int yPos = Integer.parseInt(yPosString.trim());
          Coord shotCoord = new Coord(xPos, yPos);
          if (!shotCoords.contains(shotCoord)) {
            shotCoords.add(shotCoord);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return shotCoords;
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
        output.append("Your shots all missed!");
      } else {
        output.append("You hit your opponent: \n");
        for (Coord coord : shotsThatHitOpponentShips) {
          output.append(coord.toString());
          output.append("\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void endGame(GameResult result, String reason) {

  }
}
