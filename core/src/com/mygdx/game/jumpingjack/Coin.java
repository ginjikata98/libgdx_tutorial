package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Coin extends BaseActor {
  public Coin(float x, float y, Stage s) {
    super(x, y, s);
    loadAnimationFromSheet("jumpingjack/items/coin.png", 1, 6, 0.1f, true);
  }
}
