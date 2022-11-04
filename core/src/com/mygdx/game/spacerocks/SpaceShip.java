package com.mygdx.game.spacerocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class SpaceShip extends BaseActor {
  public SpaceShip(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture("spacerocks/spaceship.png");
    setBoundaryPolygon(8);

    setAcceleration(200);
    setMaxSpeed(100);
    setDeceleration(10);
  }

  public SpaceShip(Stage s) {
    this(0, 0, s);
  }

  public void act(float dt) {
    super.act(dt);

    float degreesPerSecond = 120; // rotation speed

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
      rotateBy(degreesPerSecond * dt);
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
      rotateBy(-degreesPerSecond * dt);
    if (Gdx.input.isKeyPressed(Input.Keys.UP))
      accelerateAtAngle( getRotation() );

    applyPhysics(dt);
    wrapAroundWorld();
  }

}
