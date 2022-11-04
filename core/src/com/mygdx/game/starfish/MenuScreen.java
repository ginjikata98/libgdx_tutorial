package com.mygdx.game.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.base.BaseActor;
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

    var start = new BaseActor(mainStage);
    start.loadTexture("starfish/message-start.png");
    start.centerAtPosition(400, 300);
    start.moveBy(0, -100);
  }

  @Override
  public void update(float dt) {
    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      StarfishGame.setActiveScreen(new LevelScreen());
    }
  }
}
