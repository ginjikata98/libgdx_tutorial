package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActorV2;

public class Starfish extends BaseActorV2 {
  public Starfish(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture("starfishv2/starfish.png");

    var spin = Actions.rotateBy(30, 1);
    addAction(Actions.forever(spin));
  }
}
