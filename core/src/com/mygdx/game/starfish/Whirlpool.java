package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Whirlpool extends BaseActor {
  public Whirlpool(float x, float y, Stage s) {
    super(x, y, s);

    loadAnimationFromSheet("starfish/whirlpool.png", 2, 5, 0.1f, false);
  }

  public Whirlpool(Stage s) {
    this(0, 0, s);
  }

  public void act(float dt) {
    super.act(dt);

    if (isAnimationFinished()) {
      remove();
    }
  }
}
