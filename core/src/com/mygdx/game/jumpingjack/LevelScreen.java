package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.TilemapActor;

public class LevelScreen extends BaseScreen {
  Koala jack;
  boolean gameOver;
  int coins;
  float time;
  Label coinLabel;
  Table keyTable;
  Label timeLabel;
  Label messageLabel;

  @Override
  public void initialize() {
    var tma = new TilemapActor("jumpingjack/map.tmx", mainStage);
    for (var obj : tma.getRectangleList("Solid")) {
      var props = obj.getProperties();
      new Solid((float) props.get("x"), (float) props.get("y"),
          (float) props.get("width"), (float) props.get("height"),
          mainStage);
    }

    var startPoint = tma.getRectangleList("start").get(0);
    var startProps = startPoint.getProperties();
    jack = new Koala((float) startProps.get("x"), (float) startProps.get("y"), mainStage);

    for (var obj : tma.getTileList("Flag")) {
      var props = obj.getProperties();
      new Flag((float) props.get("x"), (float) props.get("y"), mainStage);
    }

    for (var obj : tma.getTileList("Coin")) {
      var props = obj.getProperties();
      new Coin((float) props.get("x"), (float) props.get("y"), mainStage);
    }

    for (var obj : tma.getTileList("Timer")) {
      var props = obj.getProperties();
      new Timer((float) props.get("x"), (float) props.get("y"), mainStage);
    }

    for (var obj : tma.getTileList("Springboard")) {
      var props = obj.getProperties();
      new Springboard((float) props.get("x"), (float) props.get("y"), mainStage);
    }
    jack.toFront();

    for (var obj : tma.getTileList("Platform")) {
      var props = obj.getProperties();
      new Platform((float) props.get("x"), (float) props.get("y"), mainStage);
    }


    gameOver = false;
    coins = 0;
    time = 60;
    coinLabel = new Label("Coins: " + coins, BaseGame.labelStyle);
    coinLabel.setColor(Color.GOLD);
    keyTable = new Table();
    timeLabel = new Label("Time: " + (int) time, BaseGame.labelStyle);
    timeLabel.setColor(Color.LIGHT_GRAY);
    messageLabel = new Label("Message", BaseGame.labelStyle);
    messageLabel.setVisible(false);

    uiTable.pad(20);
    uiTable.add(coinLabel);
    uiTable.add(keyTable).expandX();
    uiTable.add(timeLabel);
    uiTable.row();
    uiTable.add(messageLabel).colspan(3).expandY();

  }

  @Override
  public void update(float dt) {
    if (gameOver)
      return;
    for (BaseActor flag : BaseActor.getList(mainStage, Flag.class.getCanonicalName())) {
      if (jack.overlaps(flag)) {
        messageLabel.setText("You Win!");
        messageLabel.setColor(Color.LIME);
        messageLabel.setVisible(true);
        jack.remove();
        gameOver = true;
      }
    }

    for (BaseActor coin : BaseActor.getList(mainStage, Coin.class.getCanonicalName())) {
      if (jack.overlaps(coin)) {
        coins++;
        coinLabel.setText("Coins: " + coins);
        coin.remove();
      }
    }

    time -= dt;
    timeLabel.setText("Time: " + (int) time);
    for (BaseActor timer : BaseActor.getList(mainStage, Timer.class.getCanonicalName())) {
      if (jack.overlaps(timer)) {
        time += 20;
        timer.remove();
      }
    }

    if (time <= 0) {
      messageLabel.setText("Time Up - Game Over");
      messageLabel.setColor(Color.RED);
      messageLabel.setVisible(true);
      jack.remove();
      gameOver = true;
    }

    for (BaseActor springboard : BaseActor.getList(mainStage, Springboard.class.getCanonicalName())) {
      if (jack.belowOverlaps(springboard) && jack.isFalling()) {
        jack.spring();
      }
    }

    for (var actor : BaseActor.getList(mainStage, Solid.class.getCanonicalName())) {
      Solid solid = (Solid) actor;
      if (solid instanceof Platform) {
        if (jack.isJumping() && jack.overlaps(solid))
          solid.setEnabled(false);
        if (jack.isJumping() && !jack.overlaps(solid))
          solid.setEnabled(true);
        if (jack.isFalling() && !jack.overlaps(solid) && !jack.belowOverlaps(solid))
          solid.setEnabled(true);
      }
      if (jack.overlaps(solid) && solid.isEnabled()) {
        Vector2 offset = jack.preventOverlap(solid);
        if (offset != null) {
          // collided in X direction
          if (Math.abs(offset.x) > Math.abs(offset.y))
            jack.velocityVec.x = 0;
          else // collided in Y direction
            jack.velocityVec.y = 0;
        }
      }
    }
  }

  public boolean keyDown(int keyCode) {
    if (gameOver)
      return false;
    if (keyCode == Input.Keys.SPACE) {
      if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        for (BaseActor actor : BaseActor.getList(mainStage, Platform.class.getCanonicalName())) {
          Platform platform = (Platform) actor;
          if (jack.belowOverlaps(platform)) {
            platform.setEnabled(false);
          }
        }
      } else if (jack.isOnSolid()) {
        jack.jump();
      }
    }
    return false;
  }
}
