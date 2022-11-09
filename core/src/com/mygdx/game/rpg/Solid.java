package com.mygdx.game.rpg;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Solid extends BaseActor {
  public Solid(float x, float y, float width, float height, Stage s) {
    super(x, y, s);
    setSize(width, height);
    setBoundaryRectangle();
  }
}
