package com.simpleworlds.display;

import processing.core.PApplet;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.MetaData;
import com.simpleworlds.utils.Vec;
import com.simpleworlds.utils.WorldGenerator;
import com.simpleworlds.world.World;
import com.simpleworlds.world.entities.HexSpace;

public class MainWindow extends PApplet {
  private static final long serialVersionUID = 1L;

  public static final int screenWidth = 1024;
  public static final int screenHeight = 600;

  public static Vec screenOffset = new Vec(0, 0);

  public World world;

  Vec selected = null;

  ActionPopupBox actionPopup;

  public static void main(String args[]) {
    PApplet.main(new String[] { "--present", "com.simpleworlds.display.MainWindow" });
  }

  public void setup() {
    size(screenWidth, screenHeight, OPENGL);

    MetaData.initialize(this);

    world = WorldGenerator.generateRandomWorld(20, 20);

    imageMode(CENTER);
    ellipseMode(CENTER);
  }

  public void draw() {
    background(0);

    world.draw();

    if (selected != null) {
      Vec world = worldToScreen(gridToWorld(selected));
      image(ImagesData.selectedImage, world.x, world.y);
    }
    
    if (actionPopup != null) {
      actionPopup.draw();
      if (actionPopup.pointInPopup(mouseX, mouseY)) {
        actionPopup.highlight(new Vec(mouseX, mouseY));
      }
    }
  }

  public void mouseDragged() {
    mousePressed();
  }

  public void mousePressed() {
    if (mouseButton == LEFT) {
      if (actionPopup != null && actionPopup.pointInPopup(mouseX, mouseY)) {
        if (actionPopup.pointInPopup(mouseX, mouseY) && selected != null) {
          actionPopup.executeAction(new Vec(mouseX, mouseY), world.getHex(selected.x, selected.y));
          //actionPopup = new ActionPopupBox(gridToWorld(selected), world.getHex(selected.x, selected.y).getHexActions());
          actionPopup = null;
        }
      } else {
        selected = worldToGrid(screenToWorld(new Vec(mouseX, mouseY)));
        if (world.getHex(selected.x, selected.y) == null) {
        	selected = null;
        }
        actionPopup = null;
      }
    } else if (mouseButton == RIGHT) {
      selected = worldToGrid(screenToWorld(new Vec(mouseX, mouseY)));
      if (world.getHex(selected.x,  selected.y) != null) {
    	  actionPopup = new ActionPopupBox(gridToWorld(selected), world.getHex(selected.x, selected.y).getHexActions());
      } else {
    	  selected = null;
      }
    } else if (mouseButton == CENTER) {
      screenOffset.addLocal(mouseX - pmouseX, mouseY - pmouseY);
    }
  }

  public void mouseReleased() {

  }

  public static Vec gridToWorld(Vec grid) {
    return new Vec(Math.round(grid.x * HexSpace.WIDTH + (grid.y % 2) * HexSpace.X_OFFSET),
                   Math.round(grid.y * HexSpace.HEIGHT)); // + (grid.y % 2) * HexSpace.Y_OFFSET));
  }

  public static Vec worldToGrid(Vec world) {
    int xOffset = ((world.y - HexSpace.Y_OFFSET/2) % (2 * HexSpace.Y_OFFSET) <= HexSpace.Y_OFFSET) ? -HexSpace.X_OFFSET : 0;
    return new Vec(Math.round((world.x + HexSpace.WIDTH/2 + xOffset) / HexSpace.WIDTH), Math.round((world.y + HexSpace.HEIGHT/2) / HexSpace.HEIGHT));
  }

  public static Vec worldToScreen(Vec world) {
    return world.add(screenOffset);
  }

  public static Vec screenToWorld(Vec screen) {
    return screen.sub(screenOffset);
  }
}
