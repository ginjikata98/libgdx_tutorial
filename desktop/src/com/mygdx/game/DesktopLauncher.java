package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
  public static void main(String[] arg) {
    var config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Drop 2");
    config.setWindowedMode(800, 480);
    config.useVsync(true);
    config.setForegroundFPS(60);
    new Lwjgl3Application(new Drop(), config);
  }
}
