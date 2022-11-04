package com.mygdx.game.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public abstract class BaseGame extends Game {
  private static BaseGame game;

  public BaseGame() {
    game = this;
  }

  public static void setActiveScreen(BaseScreen s) {
    game.setScreen(s);
  }

  @Override
  public void create() {
    var im = new InputMultiplexer();
    Gdx.input.setInputProcessor(im);
  }
}
