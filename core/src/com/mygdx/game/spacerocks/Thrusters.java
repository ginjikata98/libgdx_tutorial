package com.mygdx.game.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Thrusters extends BaseActor {
  public Thrusters(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("spacerocks/fire.png");
  }

  public Thrusters(Stage s) {
    this(0, 0, s);
  }
}
