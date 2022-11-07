package com.mygdx.game.breakout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Brick extends BaseActor {
  public Brick(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("breakout/brick-gray.png");
  }
}