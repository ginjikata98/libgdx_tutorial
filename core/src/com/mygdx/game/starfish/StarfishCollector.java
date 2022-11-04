package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;

public class StarfishCollector extends BaseGame {
  private Turtle turtle;
  private Starfish starfish;
  private BaseActor ocean;

  @Override
  public void initialize() {
    ocean = new BaseActor(mainStage);
    ocean.loadTexture("starfish/water.jpg");
    ocean.setSize(800, 600);

    starfish = new Starfish(380, 380, mainStage);

    turtle = new Turtle(200, 200, mainStage);
  }

  @Override
  public void update(float dt) {

    if (turtle.overlaps(starfish) && !starfish.isCollected()) {
      starfish.collect();

      var whirl = new Whirlpool(0, 0, mainStage);
      whirl.centerAtActor(starfish);
      whirl.setOpacity(0.25f);

      var youWinMessage = new BaseActor(mainStage);
      youWinMessage.loadTexture("starfish/you-win.png");
      youWinMessage.centerAtPosition(400, 300);
      youWinMessage.setOpacity(0);
      youWinMessage.addAction(Actions.delay(1));
      youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
    }
  }
}
