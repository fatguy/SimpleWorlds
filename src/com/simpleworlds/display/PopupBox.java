package com.simpleworlds.display;

import com.simpleworlds.utils.Vec;
import com.simpleworlds.world.entities.DrawableInterface;

public abstract class PopupBox implements DrawableInterface {
  public static final int POPUP_WIDTH_BUFFER = 10;
  public static final int POPUP_HEIGHT_BUFFER = 4;

  public Vec worldPos;
  public int width;
  public int height;

  public PopupBox(Vec world) {
    worldPos = world;
  }

  public boolean pointInPopup(int x, int y) {
    Vec screenPos = MainWindow.worldToScreen(worldPos);
    if (x >= screenPos.x - width/2 && x <= screenPos.x + width/2 && y >= screenPos.y - height/2 && y <= screenPos.y + height/2) {
      return true;
    } else {
      return false;
    }
  }

  public boolean pointInPopup(Vec v) {
    return pointInPopup(v.x, v.y);
  }

  public abstract void draw();
}
