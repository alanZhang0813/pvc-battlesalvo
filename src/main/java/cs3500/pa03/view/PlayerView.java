package cs3500.pa03.view;

import java.io.IOException;

public class PlayerView implements View {
  private final Appendable appendable;

  public PlayerView(Appendable appendable) {
    this.appendable = appendable;
  }
  @Override
  public void view(Board board) {
    try {
      appendable.append(board.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
