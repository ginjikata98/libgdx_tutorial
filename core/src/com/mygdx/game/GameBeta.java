package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class GameBeta extends Game {
  protected Stage mainStage;
  protected Array<Disposable> disposeList;


  @Override
  public void create() {
    mainStage = new Stage();
    disposeList = new Array<>();
    disposeList.add(mainStage);
    initialize();
  }

  public abstract void initialize();

  public void render() {
    float dt = Gdx.graphics.getDeltaTime();

    mainStage.act(dt);

    update(dt);

    ScreenUtils.clear(0, 0, 0, 1);

    mainStage.draw();
  }

  public abstract void update(float dt);


  @Override
  public void dispose() {
    disposeList.forEach(Disposable::dispose);
  }

}
