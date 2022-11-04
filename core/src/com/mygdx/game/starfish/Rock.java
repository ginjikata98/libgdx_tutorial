package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Rock extends BaseActor {
  public Rock(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("starfish/rock.png");
    setBoundaryPolygon(8);
  }

  public Rock(Stage s) {
    super(s);
  }
}
