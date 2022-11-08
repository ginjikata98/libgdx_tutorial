package com.mygdx.game.jumpingjack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.TilemapActor;

import java.util.ArrayList;

public class LevelScreen extends BaseScreen {
  Koala jack;
  boolean gameOver;
  int coins;
  float time;
  Label coinLabel;
  Table keyTable;
  Label timeLabel;
  Label messageLabel;
  ArrayList<Color> keyList;

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

    keyList = new ArrayList<>();

    for (var obj : tma.getTileList("Key")) {
      var props = obj.getProperties();
      var key = new Key((float) props.get("x"), (float) props.get("y"), mainStage);
      var color = (String) props.get("color");
      if (color.equals("red"))
        key.setColor(Color.RED);
      else // default color
        key.setColor(Color.WHITE);
    }
    for (var obj : tma.getTileList("Lock")) {
      var props = obj.getProperties();
      var lock = new Lock((float) props.get("x"), (float) props.get("y"), mainStage);
      var color = (String) props.get("color");
      if (color.equals("red"))
        lock.setColor(Color.RED);
      else // default color
        lock.setColor(Color.WHITE);
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
    for (var flag : BaseActor.getList(mainStage, Flag.class.getCanonicalName())) {
      if (jack.overlaps(flag)) {
        messageLabel.setText("You Win!");
        messageLabel.setColor(Color.LIME);
        messageLabel.setVisible(true);
        jack.remove();
        gameOver = true;
      }
    }

    for (var coin : BaseActor.getList(mainStage, Coin.class.getCanonicalName())) {
      if (jack.overlaps(coin)) {
        coins++;
        coinLabel.setText("Coins: " + coins);
        coin.remove();
      }
    }

    time -= dt;
    timeLabel.setText("Time: " + (int) time);
    for (var timer : BaseActor.getList(mainStage, Timer.class.getCanonicalName())) {
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

    for (var springboard : BaseActor.getList(mainStage, Springboard.class.getCanonicalName())) {
      if (jack.belowOverlaps(springboard) && jack.isFalling()) {
        jack.spring();
      }
    }

    for (var key : BaseActor.getList(mainStage, Key.class.getCanonicalName())) {
      if (jack.overlaps(key)) {
        var keyColor = key.getColor();
        key.remove();
        var keyIcon = new BaseActor(0, 0, uiStage);
        keyIcon.loadTexture("jumpingjack/key-icon.png");
        keyIcon.setColor(keyColor);
        keyTable.add(keyIcon);
        keyList.add(keyColor);
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

      if (solid instanceof Lock && jack.overlaps(solid)) {
        var lockColor = solid.getColor();
        if (keyList.contains(lockColor)) {
          solid.setEnabled(false);
          solid.addAction(Actions.fadeOut(0.5f));
          solid.addAction(Actions.after(Actions.removeActor()));
        }
      }

      if (jack.overlaps(solid) && solid.isEnabled()) {
        var offset = jack.preventOverlap(solid);
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
    if (gameOver) return false;
    if (keyCode == Input.Keys.SPACE) {
      if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        for (var actor : BaseActor.getList(mainStage, Platform.class.getCanonicalName())) {
          var platform = (Platform) actor;
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
