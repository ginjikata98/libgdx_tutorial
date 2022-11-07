package com.mygdx.game.breakout;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;

public class Item extends BaseActor {
  public enum Type {
    PADDLE_EXPAND, PADDLE_SHRINK,
    BALL_SPEED_UP, BALL_SPEED_DOWN
  }

  private Type type;

  public Item(float x, float y, Stage s) {
    super(x, y, s);
    setRandomType();
    setSpeed(100);
    setMotionAngle(270);
    setSize(50, 50);
    setOrigin(25, 25);
    setBoundaryRectangle();
    setScale(0, 0);
    addAction(Actions.scaleTo(1, 1, 0.25f));
  }

  public Item(Stage s) {
    this(0, 0, s);
  }

  public void setType(Type t) {
    type = t;
    switch (t) {
      case PADDLE_EXPAND -> loadTexture("breakout/items/paddle-expand.png");
      case PADDLE_SHRINK -> loadTexture("breakout/items/paddle-shrink.png");
      case BALL_SPEED_UP -> loadTexture("breakout/items/ball-speed-up.png");
      case BALL_SPEED_DOWN -> loadTexture("breakout/items/ball-speed-down.png");
      default -> loadTexture("breakout/items/item-blank.png");
    }
  }

  public void setRandomType() {
    int randomIndex = MathUtils.random(0, Type.values().length - 1);
    Type randomType = Type.values()[randomIndex];
    setType(randomType);
  }

  public Type getType() {
    return type;
  }

  public void act(float dt) {
    super.act(dt);
    applyPhysics(dt);
    if (getY() < -50) {
      remove();
    }
  }
}
