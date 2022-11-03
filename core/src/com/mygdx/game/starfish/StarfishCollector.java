package com.mygdx.game.starfish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarfishCollector extends ApplicationAdapter {
  private SpriteBatch batch;

  private Texture turtleTexture;
  private float turtleX;
  private float turtleY;
  private Rectangle turtleRectangle;


  private Texture starfishTexture;
  private float starfishX;
  private float starfishY;
  private Rectangle starfishRectangle;

  private Texture oceanTexture;
  private Texture winMessageTexture;

  private boolean isWon;


  @Override
  public void create() {
    batch = new SpriteBatch();

    turtleTexture = new Texture(Gdx.files.internal("starfish/turtle-1.png"));
    turtleX = 20;
    turtleY = 20;
    turtleRectangle = new Rectangle(turtleX, turtleY, turtleTexture.getWidth(), turtleTexture.getHeight());

    starfishTexture = new Texture(Gdx.files.internal("starfish/starfish.png"));
    turtleX = 380;
    turtleY = 380;
    starfishRectangle = new Rectangle(starfishX, starfishY, starfishTexture.getWidth(), starfishTexture.getHeight());

    oceanTexture = new Texture(Gdx.files.internal("starfish/water.jpg"));
    winMessageTexture = new Texture(Gdx.files.internal("starfish/you-win.png"));

    isWon = false;
  }

  @Override
  public void render() {
    handleInput();
    handleCollisions();
    updateScreen();
  }

  private void handleInput() {
    if (isWon) return;
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      turtleX--;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      turtleX++;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      turtleY++;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      turtleY--;
    }
    turtleRectangle.setPosition(turtleX, turtleY);
  }

  private void handleCollisions() {
    if (turtleRectangle.overlaps(starfishRectangle)) {
      isWon = true;
    }
  }

  private void updateScreen() {
    ScreenUtils.clear(0, 0, 0, 1);
    batch.begin();
    batch.draw(oceanTexture, 0, 0);
    if (!isWon) {
      batch.draw(starfishTexture, starfishX, starfishY);
    }
    batch.draw(turtleTexture, turtleX, turtleY);
    if (isWon) {
      batch.draw(winMessageTexture, 180, 180);
    }
    batch.end();
  }


  @Override
  public void dispose() {
  }
}
