package cs3500.pa03.controller;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.view.Board;
import cs3500.pa03.view.Coord;
import cs3500.pa03.model.Player;
import cs3500.pa03.view.AiView;
import cs3500.pa03.view.PlayerView;
import cs3500.pa03.view.Ship;
import cs3500.pa03.view.ShipType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {
  private BufferedReader reader;
  private final Appendable output;

  public GameController(BufferedReader reader, Appendable output) {
    this.reader = reader;
    this.output = output;
  }

  public void run() {
    try {
      output.append("Please enter a row and a column value to represent your board size:\n");
      output.append("*-------------------------------------------------*\n");
      reader = new BufferedReader(new InputStreamReader(System.in));
      String[] inputs = getDimensions(reader);
      while (inputs.length != 2) {
        output.append("That is an invalid number of inputs!\n");
        output.append("*-------------------------------------------------*\n");
        inputs = getDimensions(reader);
      }

      while (!isAppropriate(inputs)) {
        inputs = getDimensions(reader);
      }

      int rowSize = Integer.parseInt(inputs[0]);
      int colSize = Integer.parseInt(inputs[1]);
      Map<ShipType, Integer> shipsMap = this.getShips();

      //add all the player's ships and place them randomly
      Player consolePlayer = new ManualPlayer(reader);
      List<Ship> playerShips = consolePlayer.setup(rowSize, colSize, shipsMap);
      Board playerBoard = new Board(rowSize, colSize, playerShips);
      playerBoard.placeShips();

      //add all the AI's ships and place them randomly
      Player aiPlayer = new AiPlayer();
      List<Ship> aiShips = aiPlayer.setup(rowSize, colSize, shipsMap);
      Board aiBoard = new Board(rowSize, colSize, aiShips);
      aiBoard.placeShips();

      boolean isGameOver = false;
      while (!isGameOver) {
        //outputting the view
        PlayerView playerView = new PlayerView(output);
        AiView aiView = new AiView(output);

        output.append("Your BattleSalvo board:\n");
        playerView.view(playerBoard);
        output.append("\n");
        output.append("AI BattleSalvo board:\n");
        aiView.view(aiBoard);

        output.append("\n");
        output.append("Enter your shots in the form of (x,y), up to the number of ships you have");
        output.append("\n");

        //shoot each other
        List<Coord> playerShotCoords = consolePlayer.takeShots();
        List<Coord> aiShotCoords = aiPlayer.takeShots();

        //report damage
        List<Coord> consoleHits = consolePlayer.reportDamage(aiShotCoords);
        List<Coord> aiHits = aiPlayer.reportDamage(playerShotCoords);
        output.append(consoleHits.size() + "");
        output.append("\n");
        output.append(aiHits.size() + "");

        //successful hits
        //displays the shots (from the console) that hit the AI ships
        consolePlayer.successfulHits(playerShotCoords);
        //displays which shots hit the player's ships
        aiPlayer.successfulHits(aiShotCoords);

        //modify the board according to the shots
        playerBoard.placeShots(aiShotCoords);
        aiBoard.placeShots(playerShotCoords);

        //repeat?
        if (playerBoard.isEmpty() || aiBoard.isEmpty()) {
          isGameOver = true;
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String[] getDimensions(BufferedReader inputReader) {
    try {
      String input = inputReader.readLine();
      return input.split(" ");
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public boolean isAppropriate(String[] inputs) {
    if (inputs.length != 2) {
      return false;
    }
    String input1 = inputs[0];
    String input2 = inputs[1];
    int input1Int = Integer.parseInt(input1);
    int input2Int = Integer.parseInt(input2);

    if (input1Int < 6 || input1Int > 15 || input2Int < 6 || input2Int > 15) {
      try {
        output.append("That is an invalid value for your board size! Please enter between"
            + " 6 and 15 inclusive for row and column size\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
      return false;
    }

    return true;
  }

  public Map<ShipType, Integer> getShips() {
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    Map<ShipType, Integer> shipValues = new HashMap<>();
    String shipsInput = "";
    boolean isValid = false;

    while (!isValid) {
      try {
        output.append("Please enter your fleet in the order [Carrier, Battleship, Destroyer, "
            + "Submarine].\nRemember, your fleet may not exceed size 8 "
            + "and there must be at least one ship\n");
        output.append("*-------------------------------------------------*\n");
        shipsInput = inputReader.readLine();
        if (isValidFleetSize(shipsInput)) {
          isValid = true;
        } else {
          output.append("Uh Oh! You've entered invalid fleet sizes.\n");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String[] ships = shipsInput.split(" ");
    int carriers = Integer.parseInt(ships[0]);
    int battleships = Integer.parseInt(ships[1]);
    int destroyers = Integer.parseInt(ships[2]);
    int submarines = Integer.parseInt(ships[3]);

    shipValues.put(ShipType.CARRIER, carriers);
    shipValues.put(ShipType.BATTLESHIP, battleships);
    shipValues.put(ShipType.DESTROYER, destroyers);
    shipValues.put(ShipType.SUBMARINE, submarines);

    return shipValues;
  }

  public boolean isValidFleetSize(String shipsString) {
    String[] ships = shipsString.split(" ");
    if (ships.length != 4) {
      return false;
    }
    int total = 0;
    for (String ship : ships) {
      try {
        int numShips = Integer.parseInt(ship);
        if (numShips == 0) {
          return false;
        }
        total += numShips;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return !(total > 8 || total <= 0);
  }
}
