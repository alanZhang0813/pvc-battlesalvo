package cs3500.pa03.model;

import cs3500.pa03.view.Coord;
import cs3500.pa03.view.GameResult;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractPlayer implements Player {
  public abstract String name();

  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> resultShips = new ArrayList<>();
    for (int i = 0; i < specifications.get(ShipType.CARRIER); i++) {
      resultShips.add(new Ship(ShipType.CARRIER));
    }
    for (int i = 0; i < specifications.get(ShipType.BATTLESHIP); i++) {
      resultShips.add(new Ship(ShipType.BATTLESHIP));
    }
    for (int i = 0; i < specifications.get(ShipType.DESTROYER); i++) {
      resultShips.add(new Ship(ShipType.DESTROYER));
    }
    for (int i = 0; i < specifications.get(ShipType.SUBMARINE); i++) {
      resultShips.add(new Ship(ShipType.SUBMARINE));
    }
    return resultShips;
  }

  public abstract List<Coord> takeShots();

  public abstract List<Coord> reportDamage(List<Coord> opponentShotsOnBoard);

  public abstract void successfulHits(List<Coord> shotsThatHitOpponentShips);

  public abstract void endGame(GameResult result, String reason);
}
