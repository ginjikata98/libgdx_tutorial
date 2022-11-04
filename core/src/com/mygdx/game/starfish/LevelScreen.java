package com.mygdx.game.starfish;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private Turtle turtle;
  private boolean win;
  private Label starfishLabel;

  @Override
  public void initialize() {
    var ocean = new BaseActor(mainStage);
    ocean.loadTexture("starfish/water-border.jpg");
    ocean.setSize(1200, 900);
    BaseActor.setWorldBounds(ocean);

    new Starfish(400, 400, mainStage);
    new Starfish(500, 100, mainStage);
    new Starfish(100, 450, mainStage);
    new Starfish(200, 250, mainStage);

    new Rock(200, 150, mainStage);
    new Rock(100, 300, mainStage);
    new Rock(300, 350, mainStage);
    new Rock(450, 200, mainStage);

    turtle = new Turtle(20, 20, mainStage);

    starfishLabel = new Label("Starfish left:", BaseGame.labelStyle);
    starfishLabel.setColor(Color.CYAN);
    starfishLabel.setPosition(20, 520);
    uiStage.addActor(starfishLabel);

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

    starfishLabel.setText("Starfish Left: " + BaseActor.count(mainStage, Starfish.class.getCanonicalName()));

    if (BaseActor.count(mainStage, Starfish.class.getCanonicalName()) == 0 && !win) {
      var youWinMessage = new BaseActor(uiStage);
      youWinMessage.loadTexture("starfish/you-win.png");
      youWinMessage.centerAtPosition(400, 300);
      youWinMessage.setOpacity(0);
      youWinMessage.addAction(Actions.delay(1));
      youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
    }
  }
}
