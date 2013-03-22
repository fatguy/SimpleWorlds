package com.simpleworlds.world.entities;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.UNIT_TYPE;
import com.simpleworlds.utils.Vec;

public class UnitEntity extends GameEntity {
  public UNIT_TYPE unitType;

  public int life;
  public int skill;
  public int equipment;
  public int morale;

  public UnitEntity(UNIT_TYPE type) {
    unitType = type;
  }

  public void drawAtScreenPos(Vec screen) {
    ImagesData.drawAt(unitType, screen);
  }

  public List<EntityAction> getEntityActions(HexSpace hex) {
    ArrayList<EntityAction> actions = new ArrayList<EntityAction>();

    return actions;
  }
}
