package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private Paddle paddle;

  @Override
  public void initialize() {
    var background = new BaseActor(mainStage);
    background.loadTexture("breakout/space.png");
    BaseActor.setWorldBounds(background);
    paddle = new Paddle(320, 32, mainStage);
  }

  @Override
  public void update(float dt) {
    float mouseX = Gdx.input.getX();
    paddle.setX(mouseX - paddle.getWidth() / 2);
    paddle.boundToWorld();
  }
}
