package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for GameResult
 */
public class GameResultTest {
  private GameResult grWin;
  private GameResult grLose;
  private GameResult grDraw;

  @BeforeEach
  void setup() {
    grWin = GameResult.WIN;
    grLose = GameResult.LOSE;
    grDraw = GameResult.DRAW;
  }

  @Test
  void testGetResult() {
    assertEquals(GameResult.WIN, grWin.getResult());
    assertEquals(GameResult.DRAW, grDraw.getResult());
    assertEquals(GameResult.LOSE, grLose.getResult());
  }
}
