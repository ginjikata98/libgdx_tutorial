package com.mygdx.game.spacerocks;

import com.mygdx.game.base.BaseGame;

public class SpaceGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
