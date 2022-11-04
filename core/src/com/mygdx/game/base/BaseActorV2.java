package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;


public class BaseActorV2 extends Actor {
  private Animation<TextureRegion> animation;
  private float elapsedTime;
  private boolean animationPaused;
  private Vector2 velocityVec;
  private Vector2 accelerationVec;
  private float acceleration;
  private float maxSpeed;
  private float deceleration;
  private Polygon boundaryPolygon;

  public BaseActorV2(float x, float y, Stage s) {
    super();
    setPosition(x, y);
    s.addActor(this);
    animation = null;
    elapsedTime = 0;
    animationPaused = false;
    velocityVec = new Vector2(0, 0);
    accelerationVec = new Vector2(0, 0);
    acceleration = 0;
    maxSpeed = 1000;
    deceleration = 0;
  }

  public void act(float dt) {
    super.act(dt);

    if (!animationPaused) {
      elapsedTime += dt;
    }
  }

  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    var color = getColor();
    batch.setColor(color.r, color.g, color.b, color.a);

    if (animation != null && isVisible()) {
      batch.draw(animation.getKeyFrame(elapsedTime),
          getX(), getY(), getOriginX(), getOriginY(),
          getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
  }

  public void setAnimation(Animation<TextureRegion> anim) {
    animation = anim;
    var tr = animation.getKeyFrame(0);
    float w = tr.getRegionWidth();
    float h = tr.getRegionHeight();
    setSize(w, h);
    setOrigin(w / 2, h / 2);

    if (boundaryPolygon == null) {
      setBoundaryRectangle();
    }
  }

  public void setAnimationPaused(boolean animationPaused) {
    this.animationPaused = animationPaused;
  }

  public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop) {
    int fileCount = fileNames.length;
    Array<TextureRegion> textureRegionArray = new Array<>();

    for (int i = 0; i < fileCount; i++) {
      var fileName = fileNames[i];
      var texture = new Texture(Gdx.files.internal(fileName));
      texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      textureRegionArray.add(new TextureRegion(texture));
    }

    Animation<TextureRegion> anim = new Animation<>(frameDuration, textureRegionArray);

    anim.setPlayMode(loop ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);

    if (animation == null) {
      setAnimation(anim);
    }

    return anim;
  }

  public Animation<TextureRegion> loadAnimationFromSheet(String fileName, int rows, int cols,
                                                         float frameDuration, boolean loop) {

    var texture = new Texture(Gdx.files.internal(fileName), true);
    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    int frameWidth = texture.getWidth() / cols;
    int frameHeight = texture.getHeight() / rows;

    var temp = TextureRegion.split(texture, frameWidth, frameHeight);

    Array<TextureRegion> textureArray = new Array<>();

    for (int r = 0; r < rows; r++)
      for (int c = 0; c < cols; c++)
        textureArray.add(temp[r][c]);

    Animation<TextureRegion> anim = new Animation<>(frameDuration, textureArray);

    anim.setPlayMode(loop ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);

    if (animation == null) {
      setAnimation(anim);
    }

    return anim;
  }

  public Animation<TextureRegion> loadTexture(String fileName) {
    String[] fileNames = new String[1];
    fileNames[0] = fileName;
    return loadAnimationFromFiles(fileNames, 1, true);
  }

  public boolean isAnimationFinished() {
    return animation.isAnimationFinished(elapsedTime);
  }

  public void setSpeed(float speed) {
    if (velocityVec.len() == 0) {
      velocityVec.set(speed, 0);
    } else {
      velocityVec.setLength(speed);
    }
  }

  public float getSpeed() {
    return velocityVec.len();
  }

  public void setMotionAngle(float angle) {
    velocityVec.setAngle(angle);
  }


  public float getMotionAngle() {
    return velocityVec.angle();
  }

  public boolean isMoving() {
    return getSpeed() > 0;
  }

  public void setAcceleration(float acc) {
    acceleration = acc;
  }

  public void accelerateAtAngle(float angle) {
    accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle));
  }

  public void accelerateForward() {
    accelerateAtAngle(getRotation());
  }

  public void setMaxSpeed(float ms) {
    maxSpeed = ms;
  }

  public void setDeceleration(float dec) {
    deceleration = dec;
  }

  public void applyPhysics(float dt) {
    velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);

    float speed = getSpeed();

    if (accelerationVec.len() == 0) {
      speed -= deceleration * dt;
    }

    speed = MathUtils.clamp(speed, 0, maxSpeed);

    setSpeed(speed);

    moveBy(velocityVec.x * dt, velocityVec.y * dt);

    accelerationVec.set(0, 0);
  }

  public void setBoundaryRectangle() {
    float w = getWidth();
    float h = getHeight();
    float[] vertices = {0, 0, w, 0, w, h, 0, h};
    boundaryPolygon = new Polygon(vertices);
  }

  public Polygon getBoundaryPolygon() {
    boundaryPolygon.setPosition(getX(), getY());
    boundaryPolygon.setOrigin(getOriginX(), getOriginY());
    boundaryPolygon.setRotation(getRotation());
    boundaryPolygon.setScale(getScaleX(), getScaleY());
    return boundaryPolygon;
  }

  public void setBoundaryPolygon(int numSides) {
    float w = getWidth();
    float h = getHeight();
    float[] vertices = new float[2 * numSides];
    for (int i = 0; i < numSides; i++) {
      float angle = i * 6.28f / numSides;
      // x-coordinate
      vertices[2 * i] = w / 2 * MathUtils.cos(angle) + w / 2;
      // y-coordinate
      vertices[2 * i + 1] = h / 2 * MathUtils.sin(angle) + h / 2;
    }
    boundaryPolygon = new Polygon(vertices);
  }

  public boolean overlaps(BaseActorV2 other) {
    var poly1 = this.getBoundaryPolygon();
    var poly2 = other.getBoundaryPolygon();

    if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
      return false;
    }

    return Intersector.overlapConvexPolygons(poly1, poly2);
  }

  public void centerAtPosition(float x, float y) {
    setPosition(x - getWidth() / 2, y - getHeight() / 2);
  }

  public void centerAtActor(BaseActorV2 other) {
    centerAtPosition(other.getX() + other.getWidth() / 2, other.getY() + other.getHeight() / 2);
  }

  public void setOpacity(float opacity) {
    getColor().a = opacity;
  }

}
