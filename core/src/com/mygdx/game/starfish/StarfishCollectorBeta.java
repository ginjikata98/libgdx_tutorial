package com.mygdx.game.starfish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarfishCollectorBeta extends Game {
  private Turtle turtle;
  private ActorBeta starfish;
  private ActorBeta ocean;
  private ActorBeta winMessage;

  private Stage mainStage;

  private boolean isWon;

  private Array<Disposable> disposeList;


  @Override
  public void create() {
    disposeList = new Array<>();

    mainStage = new Stage();
    disposeList.add(mainStage);

    ocean = new ActorBeta();
    ocean.setTexture(new Texture(Gdx.files.internal("starfish/water.jpg")));
    mainStage.addActor(ocean);

    starfish = new ActorBeta();
    starfish.setTexture(new Texture(Gdx.files.internal("starfish/starfish.png")));
    starfish.setPosition(380, 380);
    mainStage.addActor(starfish);

    turtle = new Turtle();
    turtle.setTexture(new Texture(Gdx.files.internal("starfish/turtle-1.png")));
    turtle.setPosition(20, 20);
    mainStage.addActor(turtle);

    winMessage = new ActorBeta();
    winMessage.setTexture(new Texture(Gdx.files.internal("starfish/you-win.png")));
    winMessage.setPosition(180, 180);
    winMessage.setVisible(false);
    mainStage.addActor(winMessage);

    isWon = false;
  }

  @Override
  public void render() {
    handleInput();
    handleCollisions();
    updateScreen();
  }

  private void handleInput() {
    if (isWon) return;
    mainStage.act(1 / 60f);
  }

  private void handleCollisions() {
    if (turtle.overlaps(starfish)) {
      starfish.remove();
      winMessage.setVisible(true);
      isWon = true;
    }
  }

  private void updateScreen() {
    ScreenUtils.clear(0, 0, 0, 1);
    mainStage.draw();
  }


  @Override
  public void dispose() {
    Gdx.app.log("dispose", "disposing");
    disposeList.forEach(Disposable::dispose);
  }
}
