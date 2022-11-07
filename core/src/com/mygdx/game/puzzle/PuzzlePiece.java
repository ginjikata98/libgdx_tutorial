package com.mygdx.game.puzzle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.DragAndDropActor;

public class PuzzlePiece extends DragAndDropActor {
  private int row;
  private int col;
  private PuzzleArea puzzleArea;

  public PuzzlePiece(float x, float y, Stage s) {
    super(x, y, s);
  }

  public PuzzlePiece(Stage s) {
    this(0, 0, s);
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

  public void setPuzzleArea(PuzzleArea pa) {
    puzzleArea = pa;
  }

  public PuzzleArea getPuzzleArea() {
    return puzzleArea;
  }

  public void clearPuzzleArea() {
    puzzleArea = null;
  }

  public void setRow(int r) {
    row = r;
  }

  public boolean hasPuzzleArea() {
    return puzzleArea != null;
  }

  public boolean isCorrectlyPlaced() {
    return hasPuzzleArea()
        && this.getRow() == puzzleArea.getRow()
        && this.getCol() == puzzleArea.getCol();
  }

  public void onDragStart() {
    if (hasPuzzleArea()) {
      PuzzleArea pa = getPuzzleArea();
      pa.setTargetable(true);
      clearPuzzleArea();
    }
  }

  public void onDrop() {
    if (hasDropTarget()) {
      PuzzleArea pa = (PuzzleArea) getDropTarget();
      moveToActor(pa);
      setPuzzleArea(pa);
      pa.setTargetable(false);
    }
  }
}
