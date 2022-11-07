package com.mygdx.game.breakout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Ball extends BaseActor {
  public boolean paused;


  public Ball(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("breakout/ball.png");
    setSpeed(400);
    setMotionAngle(90);
    setBoundaryPolygon(12);
    setPaused(true);
  }

  public Ball(Stage s) {
    this(0, 0, s);
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean b) {
    paused = b;
  }

  public void act(float dt) {
    super.act(dt);
    if (!isPaused()) {
      // simulate gravity
      setAcceleration(10);
      accelerateAtAngle(270);
      applyPhysics(dt);
    }
  }

  public void bounceOff(BaseActor other) {
    var v = this.preventOverlap(other);
    if (Math.abs(v.x) >= Math.abs(v.y)) {
      this.velocityVec.x *= -1;
    } else {
      this.velocityVec.y *= -1;
    }
  }
}
