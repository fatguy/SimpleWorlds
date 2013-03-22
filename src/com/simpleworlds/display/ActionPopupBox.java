package com.simpleworlds.display;

import java.util.List;

import processing.core.PApplet;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.MetaData;
import com.simpleworlds.utils.Vec;
import com.simpleworlds.world.entities.EntityAction;
import com.simpleworlds.world.entities.HexSpace;

public class ActionPopupBox extends PopupBox {
  public static final int STATIC_X_OFFSET = HexSpace.WIDTH + ImagesData.actionBarImage.width / 2;

  List<EntityAction> actions;

  public ActionPopupBox(Vec pos, List<EntityAction> actionList) {
    super(pos.add(new Vec(STATIC_X_OFFSET, 0)));
    actions = actionList;
    if (actions == null || actions.isEmpty()) {
      width = 0;
      height = 0;
    } else {
      width = ImagesData.actionBarImage.width + POPUP_WIDTH_BUFFER;
      height = actions.size() * (ImagesData.actionBarImage.height + POPUP_HEIGHT_BUFFER);
    }
  }

  public void draw() {
    if (actions != null && !actions.isEmpty()) {
      int yInc = height / actions.size();
      for (int i=0; i<actions.size(); i++) {
        Vec screenPos = MainWindow.worldToScreen(worldPos.add(new Vec(0, i * yInc)));
        MetaData.mainWindow.image(ImagesData.actionBarImage, screenPos.x, screenPos.y);
        ImagesData.drawActionAt(actions.get(i).actionType,
                                screenPos.sub(new Vec(width/2, 0))
                                .add(new Vec(Math.max(ImagesData.ACTION_IMAGE[actions.get(i).actionType.ordinal()].width + 2, 30), 0)));
        if (actions.get(i).progressMax > 0) {
          MetaData.mainWindow.fill(100);
          if (actions.get(i).progress >= actions.get(i).progressMax) {
            MetaData.mainWindow.fill(MetaData.mainWindow.color(0, 255, 0));
          }
          MetaData.mainWindow.noStroke();
          MetaData.mainWindow.rect(screenPos.x,
              screenPos.y - 10,
              Math.round(PApplet.map(actions.get(i).progress, 0, actions.get(i).progressMax, 0, ImagesData.actionBarImage.width/2 - POPUP_WIDTH_BUFFER)),
              20);
        }
      }
    }
  }
  
  public void highlight(Vec screen) {
  if (actions != null && !actions.isEmpty()) {
      int yInc = height / actions.size();
      Vec boundary = new Vec(ImagesData.actionBarImage.width/2, ImagesData.actionBarImage.height/2);
      for (int i=0; i<actions.size(); i++) {
        Vec screenPos = MainWindow.worldToScreen(worldPos.add(new Vec(0, i * yInc)));
        if (screenPos.contains(boundary, screen)) {
          MetaData.mainWindow.image(ImagesData.actionBarSelectedImage, screenPos.x, screenPos.y);
        }
      }
    }
  }
  
  public void executeAction(Vec screen, HexSpace hex) {
  if (actions != null && !actions.isEmpty()) {
      int yInc = height / actions.size();
      Vec boundary = new Vec(ImagesData.actionBarImage.width/2, ImagesData.actionBarImage.height/2);
      for (int i=0; i<actions.size(); i++) {
        Vec screenPos = MainWindow.worldToScreen(worldPos.add(new Vec(0, i * yInc)));
        if (screenPos.contains(boundary, screen)) {
          actions.get(i).execute(hex);
        }
      }
    }
  }
}
