package com.mygdx.game.starfish;

import com.mygdx.game.GameBeta;

public class StarfishCollectorBeta extends GameBeta {
  private Turtle turtle;
  private ActorBeta starfish;
  private ActorBeta ocean;
  private ActorBeta winMessage;

  @Override
  public void initialize() {
    ocean = ActorBeta.fromTexture("starfish/water.jpg");
    mainStage.addActor(ocean);

    starfish = ActorBeta.fromTexture("starfish/starfish.png");
    starfish.setPosition(380, 380);
    mainStage.addActor(starfish);

    turtle = new Turtle("starfish/turtle-1.png");
    turtle.setPosition(200, 200);
    mainStage.addActor(turtle);

    winMessage = ActorBeta.fromTexture("starfish/you-win.png");
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
