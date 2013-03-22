package com.simpleworlds.world.entities;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.ACTION_TYPE;
import com.simpleworlds.data.ImagesData.STRUCTURE_TYPE;
import com.simpleworlds.utils.Vec;

public class StructureEntity extends GameEntity {
  public STRUCTURE_TYPE structureType;

  public int skill;
  public int equipment;
  public int morale;

  public StructureEntity(STRUCTURE_TYPE type) {
    structureType = type;
  }

  public void drawAtScreenPos(Vec screen) {
    ImagesData.drawAt(structureType, screen);
  }

  public List<EntityAction> getEntityActions(HexSpace hex) {
    ArrayList<EntityAction> actions = new ArrayList<EntityAction>();
    switch (structureType) {
	case CITY:
		break;
	case SETTLEMENT:
		actions.add(new EntityAction(ACTION_TYPE.BUILD_TOWN));
		break;
	case TOWN:
		actions.add(new EntityAction(ACTION_TYPE.BUILD_CITY));
		break;
	default:
		break;
    }
    return actions;
  }
}
