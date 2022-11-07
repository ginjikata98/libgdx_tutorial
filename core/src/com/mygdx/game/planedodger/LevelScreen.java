package com.mygdx.game.planedodger;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private Plane plane;
  float starTimer;
  float starSpawnInterval;
  int score;
  Label scoreLabel;

  @Override
  public void initialize() {
    new Sky(0, 0, mainStage);
    new Sky(800, 0, mainStage);
    new Ground(0, 0, mainStage);
    new Ground(800, 0, mainStage);

    plane = new Plane(100, 500, mainStage);
    BaseActor.setWorldBounds(800, 600);

    starTimer = 0;
    starSpawnInterval = 4;
    score = 0;
    scoreLabel = new Label(Integer.toString(score), BaseGame.labelStyle);
    uiTable.pad(10);
    uiTable.add(scoreLabel);
    uiTable.row();
    uiTable.add().expandY();
  }

  @Override
  public void update(float dt) {
    starTimer += dt;
    if (starTimer > starSpawnInterval) {
      new Star(800, MathUtils.random(100, 500), mainStage);
      starTimer = 0;
    }
    for (BaseActor star : BaseActor.getList(mainStage, Star.class.getCanonicalName())) {
      if (plane.overlaps(star)) {
        star.remove();
        score++;
        scoreLabel.setText(Integer.toString(score));
      }
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.SPACE) {
      plane.boost();
    }
    return true;
  }
}
