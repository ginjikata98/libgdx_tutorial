package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;

public class Timer extends BaseActor {
  public Timer(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("jumpingjack/items/timer.png");
    var pulse = Actions.sequence(
        Actions.scaleTo(1.1f, 1.1f, 0.5f),
        Actions.scaleTo(1.0f, 1.0f, 0.5f));
    addAction(Actions.forever(pulse));
  }
}
