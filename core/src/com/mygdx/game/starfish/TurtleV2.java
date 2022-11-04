package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActorV2;

public class TurtleV2 extends BaseActorV2 {
  public TurtleV2(float x, float y, Stage s) {
    super(x, y, s);

    String[] filenames = {
        "starfishv2/turtle-1.png", "starfishv2/turtle-2.png", "starfishv2/turtle-3.png",
        "starfishv2/turtle-4.png", "starfishv2/turtle-5.png", "starfishv2/turtle-6.png"
    };

    loadAnimationFromFiles(filenames, 0.3f, true);
  }
}
