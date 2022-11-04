package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {
  private TextureRegion textureRegion;
  private Rectangle rectangle;

  public BaseActor() {
    super();
    textureRegion = new TextureRegion();
    rectangle = new Rectangle();
  }

  public void setTexture(Texture t) {
    textureRegion.setRegion(t);
    setSize(t.getWidth(), t.getHeight());
    rectangle.setSize(t.getWidth(), t.getHeight());
  }

  public Rectangle getRectangle() {
    rectangle.setPosition(getX(), getY());
    return rectangle;
  }

  public boolean overlaps(BaseActor other) {
    return this.getRectangle().overlaps(other.getRectangle());
  }

  public void act(float dt) {
    super.act(dt);
  }

  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    var color = getColor();
    batch.setColor(color.r, color.g, color.b, color.a);

    if (isVisible()) {
      batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
          getScaleY(), getRotation());
    }
  }

  public void setTexture(String assetUrl) {
    setTexture(new Texture(Gdx.files.internal(assetUrl)));
  }

  public static BaseActor fromTexture(Texture texture) {
    var actor = new BaseActor();
    actor.setTexture(texture);
    return actor;
  }

  public static BaseActor fromTexture(String assetUrl) {
    return fromTexture(new Texture(Gdx.files.internal(assetUrl)));
  }

}
