package com.mygdx.game.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;

public class Laser extends BaseActor {
  public Laser(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture("spacerocks/laser.png");

    addAction(Actions.sequence(
        Actions.delay(1),
        Actions.fadeOut(0.5f),
        Actions.removeActor()
    ));

    setSpeed(400);
    setMaxSpeed(400);
    setDeceleration(0);
  }

  public Laser(Stage s) {
    this(0, 0, s);
  }

  @Override
  public void act(float dt) {
    super.act(dt);
    applyPhysics(dt);
    wrapAroundWorld();
  }
}
