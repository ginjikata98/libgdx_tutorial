package com.mygdx.game.cardpickup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;

import java.util.ArrayList;

public class LevelScreen extends BaseScreen {
  private ArrayList<Pile> pileList;
  private Label messageLabel;

  @Override
  public void initialize() {
    var background = new BaseActor(mainStage);
    background.loadTexture("cardpickup/felt.jpg");
    BaseActor.setWorldBounds(background);
    for (int r = 0; r < Card.rankNames.length; r++) {
      for (int s = 0; s < Card.suitNames.length; s++) {
        int x = MathUtils.random(0, 800);
        int y = MathUtils.random(0, 300);
        var card = new Card(x, y, mainStage);
        card.setRankSuitValues(r, s);
        card.toBack();
      }
    }
    background.toBack();

    pileList = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      int pileX = 120 + 150 * i;
      int pileY = 450;
      Pile pile = new Pile(pileX, pileY, mainStage);
      pileList.add(pile);
    }

    for (BaseActor actor : BaseActor.getList(mainStage, Card.class.getCanonicalName())) {
      var card = (Card) actor;
      if (card.getRankValue() == 0) {
        Pile pile = pileList.get(card.getSuitValue());
        card.toFront();
        card.moveToActor(pile);
        pile.addCard(card);
        card.setDraggable(false);
      }
    }

    messageLabel = new Label("...", BaseGame.labelStyle);
    messageLabel.setColor(Color.CYAN);
    uiTable.add(messageLabel).expandX().expandY().bottom().pad(50);
    messageLabel.setVisible(false);
  }

  @Override
  public void update(float dt) {
    boolean complete = true;
    for (Pile pile : pileList) {
      if (pile.getSize() < 13)
        complete = false;
    }
    if (complete) {
      messageLabel.setText("You win!");
      messageLabel.setVisible(true);
    }
  }
}
