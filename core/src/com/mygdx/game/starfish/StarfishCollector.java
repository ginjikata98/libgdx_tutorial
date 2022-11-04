package com.mygdx.game.starfish;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;

public class StarfishCollector extends BaseGame {
  private Turtle turtle;
  private boolean win;

  @Override
  public void initialize() {
    var ocean = new BaseActor(mainStage);
    ocean.loadTexture("starfish/water.jpg");
    ocean.setSize(800, 600);

    new Starfish(400, 400, mainStage);
    new Starfish(500, 100, mainStage);
    new Starfish(100, 450, mainStage);
    new Starfish(200, 250, mainStage);

    new Rock(200, 150, mainStage);
    new Rock(100, 300, mainStage);
    new Rock(300, 350, mainStage);
    new Rock(450, 200, mainStage);

    turtle = new Turtle(20, 20, mainStage);

    win = false;
  }

  @Override
  public void update(float dt) {
    for (var rock : BaseActor.getList(mainStage, Rock.class.getCanonicalName())) {
      turtle.preventOverlap(rock);
    }

    for (var starfishActor : BaseActor.getList(mainStage, Starfish.class.getCanonicalName())) {
      var starfish = (Starfish) starfishActor;

      if (turtle.overlaps(starfish) && !starfish.isCollected()) {
        starfish.collect();

        var whirl = new Whirlpool(0, 0, mainStage);
        whirl.centerAtActor(starfish);
        whirl.setOpacity(0.25f);
      }

    }

    if (BaseActor.count(mainStage, Starfish.class.getCanonicalName()) == 0 && !win) {
      var youWinMessage = new BaseActor(mainStage);
      youWinMessage.loadTexture("starfish/you-win.png");
      youWinMessage.centerAtPosition(400, 300);
      youWinMessage.setOpacity(0);
      youWinMessage.addAction(Actions.delay(1));
      youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
    }
  }
}
