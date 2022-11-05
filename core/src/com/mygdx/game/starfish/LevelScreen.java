package com.mygdx.game.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private Turtle turtle;
  private boolean win;
  private Label starfishLabel;
  private DialogBox dialogBox;

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
    uiStage.addActor(starfishLabel);

    var buttonStyle = new Button.ButtonStyle();
    var buttonTex = new Texture(Gdx.files.internal("starfish/undo.png"));
    var buttonRegion = new TextureRegion(buttonTex);
    buttonStyle.up = new TextureRegionDrawable(buttonRegion);
    var restartButton = new Button(buttonStyle);
    restartButton.setColor(Color.CYAN);
    restartButton.addListener(e -> {
      if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
        return false;
      }

      StarfishGame.setActiveScreen(new LevelScreen());

      return true;
    });
    uiStage.addActor(restartButton);

    uiTable.pad(10);
    uiTable.add(starfishLabel).top();
    uiTable.add().expandX().expandY();
    uiTable.add(restartButton).top();

    var sign1 = new Sign(20, 400, mainStage);
    sign1.setText("West Starfish Bay");
    var sign2 = new Sign(600, 300, mainStage);
    sign2.setText("East Starfish Bay");
    dialogBox = new DialogBox(0, 0, uiStage);
    dialogBox.setBackgroundColor(Color.TAN);
    dialogBox.setFontColor(Color.BROWN);
    dialogBox.setDialogSize(600, 100);
    dialogBox.setFontScale(0.80f);
    dialogBox.alignCenter();
    dialogBox.setVisible(false);
    uiTable.row();
    uiTable.add(dialogBox).colspan(3);

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
