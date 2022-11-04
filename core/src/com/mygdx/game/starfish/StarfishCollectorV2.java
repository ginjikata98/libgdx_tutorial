package com.mygdx.game.starfish;

import com.mygdx.game.base.BaseActorV2;
import com.mygdx.game.base.BaseGame;

public class StarfishCollectorV2 extends BaseGame {
  private TurtleV2 turtle;
  private Starfish starfish;
  private BaseActorV2 ocean;

  @Override
  public void initialize() {
    ocean = new BaseActorV2(0, 0, mainStage);
    ocean.loadTexture("starfishv2/water.jpg");
    ocean.setSize(800, 600);

    starfish = new Starfish(380, 380, mainStage);

    turtle = new TurtleV2(20, 20, mainStage);
  }

  @Override
  public void update(float dt) {

  }
}
