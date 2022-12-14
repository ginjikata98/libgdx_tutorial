package com.mygdx.game.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.TilemapActor;
import com.mygdx.game.starfish.DialogBox;

public class LevelScreen extends BaseScreen {
  Hero hero;
  Sword sword;
  int health;
  int coins;
  int arrows;
  boolean gameOver;
  Label healthLabel;
  Label coinLabel;
  Label arrowLabel;
  Label messageLabel;
  DialogBox dialogBox;

  @Override
  public void initialize() {
    var tma = new TilemapActor("rpg/map.tmx", mainStage);
    for (var obj : tma.getRectangleList("Solid")) {
      var props = obj.getProperties();
      new Solid((float) props.get("x"), (float) props.get("y"),
          (float) props.get("width"), (float) props.get("height"),
          mainStage);
    }

    var startPoint = tma.getRectangleList("start").get(0);
    var startProps = startPoint.getProperties();
    hero = new Hero((float) startProps.get("x"), (float) startProps.get("y"), mainStage);

    sword = new Sword(0, 0, mainStage);
    sword.setVisible(false);

    for (var obj : tma.getTileList("Bush")) {
      var props = obj.getProperties();
      new Bush((float) props.get("x"), (float) props.get("y"), mainStage);
    }
    for (var obj : tma.getTileList("Rock")) {
      var props = obj.getProperties();
      new Rock((float) props.get("x"), (float) props.get("y"), mainStage);
    }

    health = 3;
    coins = 5;
    arrows = 3;
    gameOver = false;

    healthLabel = new Label(" x " + health, BaseGame.labelStyle);
    healthLabel.setColor(Color.PINK);
    coinLabel = new Label(" x " + coins, BaseGame.labelStyle);
    coinLabel.setColor(Color.GOLD);
    arrowLabel = new Label(" x " + arrows, BaseGame.labelStyle);
    arrowLabel.setColor(Color.TAN);
    messageLabel = new Label("...", BaseGame.labelStyle);
    messageLabel.setVisible(false);
    dialogBox = new DialogBox(0, 0, uiStage);
    dialogBox.setBackgroundColor(Color.TAN);
    dialogBox.setFontColor(Color.BROWN);
    dialogBox.setDialogSize(600, 100);
    dialogBox.setFontScale(0.80f);
    dialogBox.alignCenter();
    dialogBox.setVisible(false);

    BaseActor healthIcon = new BaseActor(0, 0, uiStage);
    healthIcon.loadTexture("rpg/heart-icon.png");
    BaseActor coinIcon = new BaseActor(0, 0, uiStage);
    coinIcon.loadTexture("rpg/coin-icon.png");
    BaseActor arrowIcon = new BaseActor(0, 0, uiStage);
    arrowIcon.loadTexture("rpg/arrow-icon.png");


    uiTable.pad(20);
    uiTable.add(healthIcon);
    uiTable.add(healthLabel);
    uiTable.add().expandX();
    uiTable.add(coinIcon);
    uiTable.add(coinLabel);
    uiTable.add().expandX();
    uiTable.add(arrowIcon);
    uiTable.add(arrowLabel);
    uiTable.row();
    uiTable.add(messageLabel).colspan(8).expandX().expandY();
    uiTable.row();
    uiTable.add(dialogBox).colspan(8);
  }

  @Override
  public void update(float dt) {

    if ( gameOver )
      return;

    if (!sword.isVisible()) {
      // hero movement controls
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        hero.accelerateAtAngle(180);
      if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        hero.accelerateAtAngle(0);
      if (Gdx.input.isKeyPressed(Input.Keys.UP))
        hero.accelerateAtAngle(90);
      if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        hero.accelerateAtAngle(270);
    }


    if (sword.isVisible()) {
      for (BaseActor bush : BaseActor.getList(mainStage, Bush.class.getCanonicalName())) {
        if (sword.overlaps(bush))
          bush.remove();
      }
    }


    for (BaseActor solid : BaseActor.getList(mainStage, Solid.class.getCanonicalName())) {
      hero.preventOverlap(solid);
    }

    healthLabel.setText(" x " + health);
    coinLabel.setText(" x " + coins);
    arrowLabel.setText(" x " + arrows);

  }


  public void swingSword() {
    // visibility determines if sword is currently swinging
    if (sword.isVisible())
      return;
    hero.setSpeed(0);
    float facingAngle = hero.getFacingAngle();
    var offset = new Vector2();
    if (facingAngle == 0)
      offset.set(0.50f, 0.20f);
    else if (facingAngle == 90)
      offset.set(0.65f, 0.50f);
    else if (facingAngle == 180)
      offset.set(0.40f, 0.20f);
    else // facingAngle == 270
      offset.set(0.25f, 0.20f);
    sword.setPosition(hero.getX(), hero.getY());
    sword.moveBy(offset.x * hero.getWidth(), offset.y * hero.getHeight());
    float swordArc = 90;
    sword.setRotation(facingAngle - swordArc / 2);
    sword.setOriginX(0);
    sword.setVisible(true);
    sword.addAction(Actions.rotateBy(swordArc, 0.25f));
    sword.addAction(Actions.after(Actions.visible(false)));
    // hero should appear in front of sword when facing north or west
    if (facingAngle == 90 || facingAngle == 180)
      hero.toFront();
    else
      sword.toFront();
  }

  public boolean keyDown(int keycode) {
    if ( gameOver )
      return false;
    if (keycode == Input.Keys.S)
      swingSword();
    return false;
  }
}
