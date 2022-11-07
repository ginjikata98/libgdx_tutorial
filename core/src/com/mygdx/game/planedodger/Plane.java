package com.mygdx.game.planedodger;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Plane extends BaseActor {
  public Plane(float x, float y, Stage s) {
    super(x, y, s);
    String[] filenames =
        {"planedodger/planeGreen0.png", "planedodger/planeGreen1.png",
            "planedodger/planeGreen2.png", "planedodger/planeGreen1.png"};
    loadAnimationFromFiles(filenames, 0.1f, true);
    setMaxSpeed(800);
    setBoundaryPolygon(8);
  }

  public Plane(Stage s) {
    this(0, 0, s);
  }

  @Override
  public void act(float dt) {
    super.act(dt);

    setAcceleration(800);
    accelerateAtAngle(270);
    applyPhysics(dt);

    // stop plane from passing through the ground
    for (BaseActor ground : BaseActor.getList(this.getStage(), Ground.class.getCanonicalName())) {
      if (this.overlaps(ground)) {
        setSpeed(0);
        preventOverlap(ground);
      }
    }

    // stop plane from moving past top of screen
    if (getY() + getHeight() > getWorldBounds().height) {
      setSpeed(0);
      boundToWorld();
    }
  }

  public void boost() {
    setSpeed(300);
    setMotionAngle(90);
  }
}