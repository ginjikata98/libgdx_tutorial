package com.mygdx.game.planedodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Enemy extends BaseActor {
  public Enemy(float x, float y, Stage s) {
    super(x, y, s);
    String[] filenames =
        {"planedodger/planeRed0.png", "planedodger/planeRed1.png",
            "planedodger/planeRed2.png", "planedodger/planeRed1.png"};
    loadAnimationFromFiles(filenames, 0.1f, true);
    setSpeed(100);
    setMotionAngle(180);
    setBoundaryPolygon(8);
  }

  public Enemy(Stage s) {
    this(0, 0, s);
  }

  public void act(float dt) {
    super.act(dt);
    applyPhysics(dt);
  }
}
