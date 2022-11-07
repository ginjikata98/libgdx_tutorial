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
    new Wall(0, 0, 20, 600, mainStage); // left wall
    new Wall(780, 0, 20, 600, mainStage); // right wall
    new Wall(0, 550, 800, 50, mainStage); // top wall

    var tempBrick = new Brick(0, 0, mainStage);
    float brickWidth = tempBrick.getWidth();
    float brickHeight = tempBrick.getHeight();
    tempBrick.remove();
    int totalRows = 10;
    int totalCols = 10;
    float marginX = (800 - totalCols * brickWidth) / 2;
    float marginY = (600 - totalRows * brickHeight) - 120;
    for (int rowNum = 0; rowNum < totalRows; rowNum++) {
      for (int colNum = 0; colNum < totalCols; colNum++) {
        float x = marginX + brickWidth * colNum;
        float y = marginY + brickHeight * rowNum;
        new Brick(x, y, mainStage);
      }
    }
  }

  @Override
  public void update(float dt) {
    float mouseX = Gdx.input.getX();
    paddle.setX(mouseX - paddle.getWidth() / 2);
    paddle.boundToWorld();
  }
}
