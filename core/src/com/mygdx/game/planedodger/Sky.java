package com.mygdx.game.planedodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Sky extends BaseActor {
  public Sky(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("planedodger/sky.png");
    setSpeed(25);
    setMotionAngle(180);
  }

  public Sky(Stage s) {
    this(0, 0, s);
  }

  public void act(float dt) {
    super.act(dt);
    applyPhysics(dt);

    if (getX() + getWidth() < 0) {
      moveBy(2 * getWidth(), 0);
    }
  }
}
