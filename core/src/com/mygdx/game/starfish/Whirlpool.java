package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActorV2;

public class Whirlpool extends BaseActorV2 {
  public Whirlpool(float x, float y, Stage s) {
    super(x, y, s);

    loadAnimationFromSheet("starfishv2/whirlpool.png", 2, 5, 0.1f, false);
  }

  public void act(float dt) {
    super.act(dt);

    if (isAnimationFinished()) {
      remove();
    }
  }
}
