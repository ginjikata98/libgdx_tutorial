package com.mygdx.game.cardpickup;

import com.mygdx.game.base.BaseGame;

public class CardPickupGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
