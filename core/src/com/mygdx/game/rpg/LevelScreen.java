package com.mygdx.game.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.TilemapActor;

public class LevelScreen extends BaseScreen {
  Hero hero;

  @Override
  public void initialize() {
    var tma = new TilemapActor("rpg/map.tmx", mainStage);
    for (var obj : tma.getRectangleList("Solid")) {
      var props = obj.getProperties();
      new Solid((float) props.get("x"), (float) props.get("y"),
          (float) props.get("width"), (float) props.get("height"),
          mainStage);
    }

    var startPoint = tma.getRectangleList("start").get(0);
    var startProps = startPoint.getProperties();
    hero = new Hero((float) startProps.get("x"), (float) startProps.get("y"), mainStage);

  }

  @Override
  public void update(float dt) {
    // hero movement controls
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
      hero.accelerateAtAngle(180);
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
      hero.accelerateAtAngle(0);
    if (Gdx.input.isKeyPressed(Input.Keys.UP))
      hero.accelerateAtAngle(90);
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
      hero.accelerateAtAngle(270);
    for (BaseActor solid : BaseActor.getList(mainStage, Solid.class.getCanonicalName())) {
      hero.preventOverlap(solid);
    }

  }
}
