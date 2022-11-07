package com.mygdx.game.puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseGame;
import com.mygdx.game.base.BaseScreen;

public class LevelScreen extends BaseScreen {
  private Label messageLabel;

  @Override
  public void initialize() {
    var background = new BaseActor(mainStage);
    background.loadTexture("puzzle/background.jpg");
    int numberRows = 3;
    int numberCols = 3;

    var texture = new Texture(Gdx.files.internal("puzzle/sun.jpg"), true);
    int imageWidth = texture.getWidth();
    int imageHeight = texture.getHeight();
    int pieceWidth = imageWidth / numberCols;
    int pieceHeight = imageHeight / numberRows;
    TextureRegion[][] temp = TextureRegion.split(texture, pieceWidth, pieceHeight);
    for (int r = 0; r < numberRows; r++) {
      for (int c = 0; c < numberCols; c++) {
        // create puzzle piece at random location on left half of screen
        int pieceX = MathUtils.random(0, 400 - pieceWidth);
        int pieceY = MathUtils.random(0, 600 - pieceHeight);
        PuzzlePiece pp = new PuzzlePiece(pieceX, pieceY, mainStage);
        Animation<TextureRegion> anim = new Animation<>(1, temp[r][c]);
        pp.setAnimation(anim);
        pp.setRow(r);
        pp.setCol(c);

        int marginX = (400 - imageWidth) / 2;
        int marginY = (600 - imageHeight) / 2;
        int areaX = (400 + marginX) + pieceWidth * c;
        int areaY = (600 - marginY - pieceHeight) - pieceHeight * r;
        PuzzleArea pa = new PuzzleArea(areaX, areaY, mainStage);
        pa.setSize(pieceWidth, pieceHeight);
        pa.setBoundaryRectangle();
        pa.setRow(r);
        pa.setCol(c);
      }
    }


    messageLabel = new Label("...", BaseGame.labelStyle);
    messageLabel.setColor(Color.CYAN);
    uiTable.add(messageLabel).expandX().expandY().bottom().pad(50);
    messageLabel.setVisible(false);

  }

  @Override
  public void update(float dt) {
    boolean solved = true;
    for (BaseActor actor : BaseActor.getList(mainStage, PuzzlePiece.class.getCanonicalName())) {
      PuzzlePiece pp = (PuzzlePiece) actor;
      if (!pp.isCorrectlyPlaced())
        solved = false;
    }
    if (solved) {
      messageLabel.setText("You win!");
      messageLabel.setVisible(true);
    } else {
      messageLabel.setText("...");
      messageLabel.setVisible(false);
    }
  }
}
