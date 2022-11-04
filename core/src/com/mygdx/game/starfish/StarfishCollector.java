package com.mygdx.game.starfish;

import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;

public class StarfishCollector extends BaseGame {
  private Turtle turtle;
  private BaseActor starfish;
  private BaseActor ocean;
  private BaseActor winMessage;

  @Override
  public void initialize() {
    ocean = BaseActor.fromTexture("starfish/water.jpg");
    mainStage.addActor(ocean);

    starfish = BaseActor.fromTexture("starfish/starfish.png");
    starfish.setPosition(380, 380);
    mainStage.addActor(starfish);

    turtle = new Turtle("starfish/turtle-1.png");
    turtle.setPosition(200, 200);
    mainStage.addActor(turtle);

    winMessage = BaseActor.fromTexture("starfish/you-win.png");
    winMessage.setPosition(180, 180);
    winMessage.setVisible(false);
    mainStage.addActor(winMessage);
  }

  @Override
  public void update(float dt) {
    if (turtle.overlaps(starfish)) {
      starfish.remove();
      winMessage.setVisible(true);
    }
  }
}
