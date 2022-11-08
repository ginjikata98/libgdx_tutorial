package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.TilemapActor;

public class LevelScreen extends BaseScreen {
  Koala jack;

  @Override
  public void initialize() {
    var tma = new TilemapActor("jumpingjack/map.tmx", mainStage);
    for (var obj : tma.getRectangleList("Solid")) {
      var props = obj.getProperties();
      new Solid((float) props.get("x"), (float) props.get("y"),
          (float) props.get("width"), (float) props.get("height"),
          mainStage);
    }

    var startPoint = tma.getRectangleList("start").get(0);
    var startProps = startPoint.getProperties();
    jack = new Koala((float) startProps.get("x"), (float) startProps.get("y"), mainStage);
  }

  @Override
  public void update(float dt) {
    for (var actor : BaseActor.getList(mainStage, Solid.class.getCanonicalName())) {
      Solid solid = (Solid) actor;
      if (jack.overlaps(solid) && solid.isEnabled()) {
        Vector2 offset = jack.preventOverlap(solid);
        if (offset != null) {
          // collided in X direction
          if (Math.abs(offset.x) > Math.abs(offset.y))
            jack.velocityVec.x = 0;
          else // collided in Y direction
            jack.velocityVec.y = 0;
        }
      }
    }
  }

  public boolean keyDown(int keyCode) {
    if (keyCode == Input.Keys.SPACE) {
      if (jack.isOnSolid()) {
        jack.jump();
      }
    }
    return false;
  }
}
