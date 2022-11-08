package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Flag extends BaseActor {
  public Flag(float x, float y, Stage s) {
    super(x, y, s);
    loadAnimationFromSheet("jumpingjack/items/flag.png", 1, 2, 0.2f, true);
  }
}
