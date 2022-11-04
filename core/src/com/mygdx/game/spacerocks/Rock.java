package com.mygdx.game.spacerocks;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;

public class Rock extends BaseActor {
  public Rock(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture("spacerocks/rock.png");

    float random = MathUtils.random(30);

    addAction(Actions.forever(Actions.rotateBy(30 + random, 1)));

    setSpeed(50 + random);
    setAcceleration(50 + random);
    setDeceleration(0);
  }

  public Rock(Stage s) {
    this(0, 0, s);
  }

  @Override
  public void act(float dt) {
    super.act(dt);
    applyPhysics(dt);
    wrapAroundWorld();
  }
}
