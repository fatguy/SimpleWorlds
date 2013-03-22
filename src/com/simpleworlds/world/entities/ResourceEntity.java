package com.simpleworlds.world.entities;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.RESOURCE_TYPE;
import com.simpleworlds.utils.Conversions;
import com.simpleworlds.utils.Vec;

public class ResourceEntity extends GameEntity {
  public static final int HARVEST_PROGRESS_MAX = 1000;
  
  public RESOURCE_TYPE resourceType;
  
  public int harvestProgress = 0;
  
  public ResourceEntity(RESOURCE_TYPE type) {
    resourceType = type;
  }

  public void drawAtScreenPos(Vec screen) {
    if (harvestProgress >= HARVEST_PROGRESS_MAX) {
      ImagesData.drawAt(resourceType, screen);
    }
  }

  public List<EntityAction> getEntityActions(HexSpace hex) {
    ArrayList<EntityAction> actions = new ArrayList<EntityAction>();
    EntityAction action = new EntityAction(Conversions.resourceTypeToActionType(resourceType));
    action.progress = harvestProgress;
    action.progressMax = HARVEST_PROGRESS_MAX;
    actions.add(action);
    return actions;
  }
}
