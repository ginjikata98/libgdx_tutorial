package com.mygdx.game.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Turtle extends BaseActor {
  public Turtle(float x, float y, Stage s) {
    super(x, y, s);

    String[] filenames = {
        "starfish/turtle-1.png", "starfish/turtle-2.png", "starfish/turtle-3.png",
        "starfish/turtle-4.png", "starfish/turtle-5.png", "starfish/turtle-6.png"
    };

    loadAnimationFromFiles(filenames, 0.3f, true);

    setBoundaryPolygon(8);


    setAcceleration(400);
    setMaxSpeed(100);
    setDeceleration(400);
  }

  public void act(float dt) {
    super.act(dt);

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
      accelerateAtAngle(180);
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
      accelerateAtAngle(0);
    if (Gdx.input.isKeyPressed(Input.Keys.UP))
      accelerateAtAngle(90);
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
      accelerateAtAngle(270);

    applyPhysics(dt);

    setAnimationPaused(!isMoving());

    if (getSpeed() > 0) {
      setRotation(getMotionAngle());
    }
  }

}
