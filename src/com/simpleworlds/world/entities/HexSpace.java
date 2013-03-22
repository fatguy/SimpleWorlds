package com.simpleworlds.world.entities;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.MetaData;
import com.simpleworlds.data.ImagesData.ACTION_TYPE;
import com.simpleworlds.data.ImagesData.TERRAIN_TYPE;
import com.simpleworlds.display.MainWindow;
import com.simpleworlds.utils.Vec;

public class HexSpace implements DrawableInterface {
  public static final int WIDTH = 56;
  public static final int HEIGHT = 36;
  public static final int X_OFFSET = 28;
  public static final int Y_OFFSET = 36;

  public TERRAIN_TYPE terrainType;
  public ResourceEntity resource;
  public StructureEntity structure;
  public UnitEntity unit;
  public EffectEntity effect;
  public Vec worldPos;
  public boolean visible = false;
  public boolean highlighted = false;

  public HexSpace(Vec world, TERRAIN_TYPE t) {
    worldPos = world;
    terrainType = t;
  }

  public void draw() {
    Vec screenPos = MainWindow.worldToScreen(worldPos);
    if (visible) {
      ImagesData.drawTerrainAt(terrainType, screenPos);
      if (resource != null) {
        resource.drawAtScreenPos(screenPos);
      }
      if (structure != null) {
        structure.drawAtScreenPos(screenPos);
      }
      if (unit != null) {
        unit.drawAtScreenPos(screenPos);
      }
      if (effect != null) {
        effect.drawAtScreenPos(screenPos);
      }
    } else {
      ImagesData.drawTerrainAt(TERRAIN_TYPE.UNKNOWN, screenPos);
    }
    if (highlighted) {
  	  MetaData.mainWindow.image(ImagesData.highlightedImage, screenPos.x, screenPos.y);
    }
  }

  public List<EntityAction> getHexActions() {
    ArrayList<EntityAction> actions = new ArrayList<EntityAction>();
    if (!visible) {
      boolean hasVisibleNeighbor = false;
      for (HexSpace hex : MetaData.getActiveWorld().getNeighbors(this)) {
        if (hex.visible) {
          hasVisibleNeighbor = true;
        }
      }
      if (hasVisibleNeighbor) {
        actions.add(new EntityAction(ACTION_TYPE.EXPLORE));
      }
    } else {
    	if (terrainType.equals(TERRAIN_TYPE.GRASSLAND) && structure == null) {
    		actions.add(new EntityAction(ACTION_TYPE.CREATE_FARM));
    	}
    	if (resource != null) {
        actions.addAll(resource.getEntityActions(this));
      }
      if (structure != null) {
        actions.addAll(structure.getEntityActions(this));
      }
      if (unit != null) {
        actions.addAll(unit.getEntityActions(this));
      }
      if (effect != null) {
        actions.addAll(effect.getEntityActions(this));
      }
    }
    return actions;
  }
}
