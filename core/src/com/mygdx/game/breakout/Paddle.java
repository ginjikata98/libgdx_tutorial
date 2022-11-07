package com.mygdx.game.breakout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Paddle extends BaseActor {
  public Paddle(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("breakout/paddle.png");
  }

  public Paddle(Stage s) {
    this(0, 0, s);
  }
}
