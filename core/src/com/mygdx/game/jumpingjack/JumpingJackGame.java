package com.mygdx.game.jumpingjack;

import com.mygdx.game.base.BaseGame;

public class JumpingJackGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
