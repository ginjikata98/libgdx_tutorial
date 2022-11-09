package com.mygdx.game.rpg;

import com.mygdx.game.base.BaseGame;

public class TreasureQuestGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
