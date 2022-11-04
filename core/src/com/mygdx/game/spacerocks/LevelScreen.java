package com.mygdx.game.spacerocks;

import com.badlogic.gdx.Input;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private SpaceShip spaceShip;

  @Override
  public void initialize() {
    var space = new BaseActor(mainStage);
    space.loadTexture("spacerocks/space.png");
    space.setSize(800, 600);
    BaseActor.setWorldBounds(space);

    new Rock(600,500, mainStage);
    new Rock(600,300, mainStage);
    new Rock(600,100, mainStage);
    new Rock(400,100, mainStage);
    new Rock(200,100, mainStage);
    new Rock(200,300, mainStage);
    new Rock(200,500, mainStage);
    new Rock(400,500, mainStage);

    spaceShip = new SpaceShip(400, 300, mainStage);
  }

  @Override
  public void update(float dt) {

  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.X) {
      spaceShip.wrap();
    }

    if (keycode == Input.Keys.SPACE) {
      spaceShip.shoot();
    }

    return false;
  }
}
