package com.mygdx.game.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Wall extends BaseActor {
  public Wall(float x, float y, float width, float height, Stage s) {
    super(x, y, s);
    loadTexture("breakout/white-square.png");
    setSize(width, height);
    setColor(Color.GRAY);
    setBoundaryRectangle();
  }
}
