package com.mygdx.game.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Explosion extends BaseActor {
  public Explosion(float x, float y, Stage s) {
    super(x, y, s);

    loadAnimationFromSheet("spacerocks/explosion.png", 6, 6, 0.03f, false);
  }

  public Explosion(Stage s) {
    this(0, 0, s);
  }

  @Override
  public void act(float dt) {
    super.act(dt);

    if (isAnimationFinished()) {
      remove();
    }
  }
}
