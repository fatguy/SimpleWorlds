package com.simpleworlds.data;

import com.simpleworlds.display.MainWindow;
import com.simpleworlds.world.World;

public class MetaData {
  public static MainWindow mainWindow;
  
  public static void initialize(MainWindow w) {
    mainWindow = w;

    ImagesData.initialize();
  }
  
  public static World getActiveWorld() {
    return mainWindow.world;
  }
}
