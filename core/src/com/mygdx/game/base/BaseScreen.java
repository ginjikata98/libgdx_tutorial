package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class BaseScreen implements Screen, InputProcessor {

  protected Stage mainStage;
  protected Stage uiStage;
  protected Array<Disposable> disposeList;
  protected Table uiTable;

  public BaseScreen() {
    mainStage = new Stage();
    uiStage = new Stage();
    disposeList = new Array<>();
    disposeList.add(mainStage, uiStage);
    uiTable = new Table();
    uiTable.setFillParent(true);
    uiStage.addActor(uiTable);
    initialize();
  }

  public void render(float dt) {
    mainStage.act(dt);
    uiStage.act(dt);

    update(dt);

    ScreenUtils.clear(0, 0, 0, 1);

    mainStage.draw();
    uiStage.draw();
  }

  public void show() {
    var im = (InputMultiplexer) Gdx.input.getInputProcessor();
    if (im == null) return;
    im.addProcessor(this);
    im.addProcessor(uiStage);
    im.addProcessor(mainStage);
  }

  public void hide() {
    var im = (InputMultiplexer) Gdx.input.getInputProcessor();
    if (im == null) return;
    im.removeProcessor(this);
    im.removeProcessor(uiStage);
    im.removeProcessor(mainStage);
  }

  public boolean isTouchDownEvent(Event e) {
    return (e instanceof InputEvent) && ((InputEvent) e).getType().equals(InputEvent.Type.touchDown);
  }


  public abstract void initialize();

  public abstract void update(float dt);


  public void resize(int width, int height) {
  }

  public void pause() {
  }

  public void resume() {
  }

  public void dispose() {
    disposeList.forEach(Disposable::dispose);
  }


  // methods required by InputProcessor interface
  public boolean keyDown(int keycode) {
    return false;
  }

  public boolean keyUp(int keycode) {
    return false;
  }

  public boolean keyTyped(char c) {
    return false;
  }

  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  public boolean scrolled(float amountX, float amountY) {
    return false;
  }

  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

}
