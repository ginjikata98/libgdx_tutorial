package com.mygdx.game.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.base.TilemapActor;

public class LevelScreen extends BaseScreen {
  private Turtle turtle;
  private boolean win;
  private Label starfishLabel;
  private DialogBox dialogBox;
  private float audioVolume;
  private Sound waterDrop;
  private Music instrumental;
  private Music oceanSurf;

  @Override
  public void initialize() {
    var tma = new TilemapActor("starfish/map.tmx", mainStage);

    for (var obj : tma.getTileList("Starfish")) {
      var props = obj.getProperties();
      new Starfish((float) props.get("x"), (float) props.get("y"), mainStage);
    }

    for (var obj : tma.getTileList("Rock")) {
      var props = obj.getProperties();
      new Rock((float) props.get("x"), (float) props.get("y"), mainStage);
    }
    for (var obj : tma.getTileList("Sign")) {
      var props = obj.getProperties();
      var s = new Sign((float) props.get("x"), (float) props.get("y"), mainStage);
      s.setText((String) props.get("message"));
    }

    var startPoint = tma.getRectangleList("start").get(0);
    var props = startPoint.getProperties();
    turtle = new Turtle((float) props.get("x"), (float) props.get("y"), mainStage);

    starfishLabel = new Label("Starfish left:", BaseGame.labelStyle);
    starfishLabel.setColor(Color.CYAN);
    uiStage.addActor(starfishLabel);

    var buttonStyle = new Button.ButtonStyle();
    var buttonTex = new Texture(Gdx.files.internal("starfish/undo.png"));
    var buttonRegion = new TextureRegion(buttonTex);
    buttonStyle.up = new TextureRegionDrawable(buttonRegion);
    var restartButton = new Button(buttonStyle);
    restartButton.setColor(Color.CYAN);
    restartButton.addListener(e -> {
      if (!isTouchDownEvent(e)) {
        return false;
      }

      instrumental.dispose();
      oceanSurf.dispose();

      StarfishGame.setActiveScreen(new LevelScreen());

      return true;
    });

    var buttonStyle2 = new Button.ButtonStyle();
    Texture buttonTex2 = new Texture(Gdx.files.internal("starfish/audio.png"));
    var buttonRegion2 = new TextureRegion(buttonTex2);
    buttonStyle2.up = new TextureRegionDrawable(buttonRegion2);
    var muteButton = new Button(buttonStyle2);
    muteButton.setColor(Color.CYAN);
    muteButton.addListener(e -> {
      if (!isTouchDownEvent(e)) {
        return false;
      }
      audioVolume = 1 - audioVolume;
      instrumental.setVolume(audioVolume);
      oceanSurf.setVolume(audioVolume);
      return true;
    });

    uiStage.addActor(restartButton);
    uiTable.pad(10);
    uiTable.add(starfishLabel).top();
    uiTable.add().expandX().expandY();
    uiTable.add(restartButton).top();
    uiTable.add(muteButton).top();

    dialogBox = new DialogBox(0, 0, uiStage);
    dialogBox.setBackgroundColor(Color.TAN);
    dialogBox.setFontColor(Color.BROWN);
    dialogBox.setDialogSize(600, 100);
    dialogBox.setFontScale(0.80f);
    dialogBox.alignCenter();
    dialogBox.setVisible(false);
    uiTable.row();
    uiTable.add(dialogBox).colspan(4);

    waterDrop = Gdx.audio.newSound(Gdx.files.internal("starfish/Water_Drop.ogg"));
    instrumental = Gdx.audio.newMusic(Gdx.files.internal("starfish/Master_of_the_Feast.ogg"));
    oceanSurf = Gdx.audio.newMusic(Gdx.files.internal("starfish/Ocean_Waves.ogg"));
    audioVolume = 1.00f;
    instrumental.setLooping(true);
    instrumental.setVolume(audioVolume);
    instrumental.play();
    oceanSurf.setLooping(true);
    oceanSurf.setVolume(audioVolume);
    oceanSurf.play();

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
        waterDrop.play(audioVolume);

        var whirl = new Whirlpool(0, 0, mainStage);
        whirl.centerAtActor(starfish);
        whirl.setOpacity(0.25f);
      }

    }

    starfishLabel.setText("Starfish Left: " + BaseActor.count(mainStage, Starfish.class.getCanonicalName()));

    for (BaseActor signActor : BaseActor.getList(mainStage, Sign.class.getCanonicalName())) {
      var sign = (Sign) signActor;
      turtle.preventOverlap(sign);
      boolean nearby = turtle.isWithinDistance(4, sign);
      if (nearby && !sign.isViewing()) {
        dialogBox.setText(sign.getText());
        dialogBox.setVisible(true);
        sign.setViewing(true);
      }
      if (sign.isViewing() && !nearby) {
        dialogBox.setText(" ");
        dialogBox.setVisible(false);
        sign.setViewing(false);
      }
    }

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
