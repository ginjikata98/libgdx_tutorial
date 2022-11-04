package com.mygdx.game.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public abstract class BaseGame extends Game {
  private static BaseGame game;
  public static Label.LabelStyle labelStyle;

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

    var fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("starfish/OpenSans.ttf"));
    var fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
    fontParameters.size = 48;
    fontParameters.color = Color.WHITE;
    fontParameters.borderWidth = 2;
    fontParameters.borderColor = Color.BLACK;
    fontParameters.borderStraight = true;
    fontParameters.minFilter = Texture.TextureFilter.Linear;
    fontParameters.magFilter = Texture.TextureFilter.Linear;

    labelStyle = new Label.LabelStyle();
    labelStyle.font = fontGenerator.generateFont(fontParameters);
  }
}
