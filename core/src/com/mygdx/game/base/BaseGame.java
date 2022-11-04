package com.mygdx.game.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class BaseGame extends Game {
  protected Stage mainStage;
  protected Stage uiStage;
  protected Array<Disposable> disposeList;


  @Override
  public void create() {
    mainStage = new Stage();
    uiStage = new Stage();
    disposeList = new Array<>();
    disposeList.add(mainStage, uiStage);
    initialize();
  }

  public abstract void initialize();

  public void render() {
    float dt = Gdx.graphics.getDeltaTime();

    mainStage.act(dt);
    uiStage.act(dt);

    update(dt);

    ScreenUtils.clear(0, 0, 0, 1);

    mainStage.draw();
    uiStage.draw();
  }

  public abstract void update(float dt);


  @Override
  public void dispose() {
    disposeList.forEach(Disposable::dispose);
  }

}
