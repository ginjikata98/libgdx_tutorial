package com.mygdx.game.planedodger;

import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  @Override
  public void initialize() {
    new Sky(0,0, mainStage);
    new Sky(800,0, mainStage);
    new Ground(0,0, mainStage);
    new Ground(800,0, mainStage);
  }

  @Override
  public void update(float dt) {

  }
}
