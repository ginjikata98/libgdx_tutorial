package com.mygdx.game.puzzle;

import com.mygdx.game.base.BaseGame;

public class PuzzleGame extends BaseGame {
  @Override
  public void create() {
    super.create();
    setActiveScreen(new LevelScreen());
  }
}
