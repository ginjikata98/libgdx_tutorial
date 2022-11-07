package com.mygdx.game.base;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class DropTargetActor extends BaseActor {
  private boolean targetable;

  public DropTargetActor(float x, float y, Stage s) {
    super(x, y, s);
    targetable = true;
  }

  public DropTargetActor(Stage s) {
    this(0, 0, s);
  }

  public void setTargetable(boolean t) {
    targetable = t;
  }

  public boolean isTargetable() {
    return targetable;
  }
}
