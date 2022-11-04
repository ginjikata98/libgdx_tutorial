package com.mygdx.game.spacerocks;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private SpaceShip spaceShip;
  private boolean gameOver;

  @Override
  public void initialize() {
    var space = new BaseActor(mainStage);
    space.loadTexture("spacerocks/space.png");
    space.setSize(800, 600);
    BaseActor.setWorldBounds(space);

    new Rock(600, 500, mainStage);
    new Rock(600, 300, mainStage);
    new Rock(600, 100, mainStage);
    new Rock(400, 100, mainStage);
    new Rock(200, 100, mainStage);
    new Rock(200, 300, mainStage);
    new Rock(200, 500, mainStage);
    new Rock(400, 500, mainStage);

    spaceShip = new SpaceShip(400, 300, mainStage);

    gameOver = false;
  }

  @Override
  public void update(float dt) {

    for (var rockActor : BaseActor.getList(mainStage, Rock.class.getCanonicalName())) {
      if (rockActor.overlaps(spaceShip)) {
        if (spaceShip.shieldPower <= 0) {
          var boom = new Explosion(mainStage);
          boom.centerAtActor(spaceShip);
          spaceShip.remove();
          spaceShip.setPosition(-1000, -1000);

          var messageLose = new BaseActor(0, 0, uiStage);
          messageLose.loadTexture("spacerocks/message-lose.png");
          messageLose.centerAtPosition(400, 300);
          messageLose.setOpacity(0);
          messageLose.addAction(Actions.fadeIn(1));
          gameOver = true;

        } else {
          spaceShip.shieldPower -= 34;
          var boom = new Explosion(mainStage);
          boom.centerAtActor(spaceShip);
          rockActor.remove();
        }
      }

      for (var laserActor : BaseActor.getList(mainStage, Laser.class.getCanonicalName())) {
        if (laserActor.overlaps(rockActor)) {
          var boom = new Explosion(mainStage);
          boom.centerAtActor(rockActor);
          laserActor.remove();
          rockActor.remove();
        }
      }
    }

    if (!gameOver && BaseActor.count(mainStage, Rock.class.getCanonicalName()) == 0) {
      var messageWin = new BaseActor(uiStage);
      messageWin.loadTexture("spacerocks/message-win.png");
      messageWin.centerAtPosition(400, 300);
      messageWin.setOpacity(0);
      messageWin.addAction(Actions.fadeIn(1));
      gameOver = true;
    }

  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.X) {
      spaceShip.wrap();
    }

    if (keycode == Input.Keys.SPACE) {
      spaceShip.shoot();
    }

    return false;
  }
}
