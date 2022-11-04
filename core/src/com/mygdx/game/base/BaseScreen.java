package com.mygdx.game.base;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class BaseScreen implements Screen {

  protected Stage mainStage;
  protected Stage uiStage;
  protected Array<Disposable> disposeList;

  public BaseScreen() {
    mainStage = new Stage();
    uiStage = new Stage();
    disposeList = new Array<>();
    disposeList.add(mainStage, uiStage);
    initialize();
  }

  public abstract void initialize();

  public abstract void update(float dt);


  public void render(float dt) {
    mainStage.act(dt);
    uiStage.act(dt);

    update(dt);

    ScreenUtils.clear(0, 0, 0, 1);

    mainStage.draw();
    uiStage.draw();
  }

  public void resize(int width, int height) {
  }

  public void pause() {
  }

  public void resume() {
  }

  public void show() {
  }

  public void hide() {
  }

  public void dispose() {
    disposeList.forEach(Disposable::dispose);
  }

}
