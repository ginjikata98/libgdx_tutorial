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

    return false;
  }
}
