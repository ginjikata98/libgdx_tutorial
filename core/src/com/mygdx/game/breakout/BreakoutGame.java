package com.mygdx.game.breakout;

import com.mygdx.game.base.BaseGame;

public class BreakoutGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
