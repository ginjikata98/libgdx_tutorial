package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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

    turtle = new TurtleV2(200, 200, mainStage);
  }

  @Override
  public void update(float dt) {

    if (turtle.overlaps(starfish) && !starfish.isCollected()) {
      starfish.collect();

      var whirl = new Whirlpool(0, 0, mainStage);
      whirl.centerAtActor(starfish);
      whirl.setOpacity(0.25f);

      var youWinMessage = new BaseActorV2(0, 0, mainStage);
      youWinMessage.loadTexture("starfishv2/you-win.png");
      youWinMessage.centerAtPosition(400, 300);
      youWinMessage.setOpacity(0);
      youWinMessage.addAction(Actions.delay(1));
      youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));

    }

  }
}
