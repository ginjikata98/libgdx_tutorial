package com.mygdx.game.starfish;

import com.mygdx.game.base.BaseGame;

public class StarfishGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new MenuScreen());
  }
}
