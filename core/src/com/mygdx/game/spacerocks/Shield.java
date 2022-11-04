package com.mygdx.game.spacerocks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;

public class Shield extends BaseActor {
  public Shield(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture("spacerocks/shields.png");

    var pulse = Actions.sequence(
        Actions.scaleTo(1.05f, 1.05f, 1),
        Actions.scaleTo(0.95f, 0.95f, 1)
    );

    addAction(Actions.forever(pulse));
  }

  public Shield(Stage s) {
    this(0, 0, s);
  }
}
