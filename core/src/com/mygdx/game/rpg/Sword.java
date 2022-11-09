package com.mygdx.game.rpg;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.BaseActor;

public class Sword extends BaseActor {
  public Sword(float x, float y, Stage s) {
    super(x, y, s);
    loadTexture("rpg/sword.png");
  }
}