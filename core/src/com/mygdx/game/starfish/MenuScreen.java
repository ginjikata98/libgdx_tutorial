package com.mygdx.game.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {
  @Override
  public void initialize() {
    var ocean = new BaseActor(mainStage);
    ocean.loadTexture("starfish/water.jpg");
    ocean.setSize(800, 600);

    var title = new BaseActor(mainStage);
    title.loadTexture("starfish/starfish-collector.png");
    title.centerAtPosition(400, 300);
    title.moveBy(0, 100);

    var startButton = new TextButton("Start", BaseGame.textButtonStyle);
    startButton.setPosition(150, 150);
    uiStage.addActor(startButton);
    startButton.addListener((e) -> {
      if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) return false;
      StarfishGame.setActiveScreen(new LevelScreen());
      return false;
    });

    var quitButton = new TextButton("Quit", BaseGame.textButtonStyle);
    quitButton.setPosition(500, 150);
    uiStage.addActor(quitButton);
    quitButton.addListener((e) -> {
      if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) return false;
      Gdx.app.exit();
      return false;
    });
  }

  @Override
  public void update(float dt) {

  }

  public boolean keyDown(int keyCode) {
    if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
      StarfishGame.setActiveScreen(new LevelScreen());
    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
      Gdx.app.exit();
    return false;
  }
}
