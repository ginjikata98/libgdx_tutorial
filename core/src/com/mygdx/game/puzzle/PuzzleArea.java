package com.mygdx.game.puzzle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.DropTargetActor;

public class PuzzleArea extends DropTargetActor {
  private int row;
  private int col;

  public PuzzleArea(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("puzzle/border.jpg");
  }

  public PuzzleArea(Stage s) {
    this(0, 0, s);
  }

  public void setRow(int r) {
    row = r;
  }

  public void setCol(int c) {
    col = c;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }
}
