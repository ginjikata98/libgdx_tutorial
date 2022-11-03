package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.drop.Drop;
import com.mygdx.game.starfish.StarfishCollector;
import com.mygdx.game.starfish.StarfishCollectorBeta;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
  private enum Games {
    drop,
    starfish
  }

  public static void main(String[] arg) {
    var config = new Lwjgl3ApplicationConfiguration();
    ApplicationListener app = null;
    var game = Games.starfish;

    switch (game) {
      case drop -> {
        config.setTitle("Drop");
        config.setWindowedMode(800, 480);
        config.useVsync(true);
        config.setForegroundFPS(60);
        app = new Drop();
      }
      case starfish -> {
        config.setTitle("Starfish Collector");
        config.setWindowedMode(800, 600);
        config.useVsync(true);
        config.setForegroundFPS(60);
        app = new StarfishCollectorBeta();
      }
    }

    new Lwjgl3Application(app, config);
  }
}
