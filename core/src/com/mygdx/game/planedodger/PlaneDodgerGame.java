package com.mygdx.game.planedodger;

import com.mygdx.game.base.BaseGame;

public class PlaneDodgerGame extends BaseGame {
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
