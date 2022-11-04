package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;

public class Starfish extends BaseActor {
  private boolean collected;

  public Starfish(float x, float y, Stage s) {
    super(x, y, s);

    collected = false;

    loadTexture("starfish/starfish.png");
    setBoundaryPolygon(8);

    var spin = Actions.rotateBy(30, 1);
    addAction(Actions.forever(spin));
  }

  public boolean isCollected() {
    return collected;
  }

  public void collect() {
    collected = true;
    clearActions();
    addAction(Actions.fadeOut(1));
    addAction(Actions.after(Actions.removeActor()));
  }
}
