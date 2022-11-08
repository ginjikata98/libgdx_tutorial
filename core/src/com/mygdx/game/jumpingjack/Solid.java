package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Solid extends BaseActor {
  private boolean enabled;

  public Solid(float x, float y, float width, float height, Stage s) {
    super(x, y, s);
    setSize(width, height);
    setBoundaryRectangle();
    enabled = true;
  }

  public void setEnabled(boolean b) {
    enabled = b;
  }

  public boolean isEnabled() {
    return enabled;
  }
}
