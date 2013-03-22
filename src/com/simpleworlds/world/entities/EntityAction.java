package com.simpleworlds.world.entities;

import com.simpleworlds.data.MetaData;
import com.simpleworlds.data.ImagesData.ACTION_TYPE;
import com.simpleworlds.data.ImagesData.RESOURCE_TYPE;
import com.simpleworlds.data.ImagesData.STRUCTURE_TYPE;
import com.simpleworlds.data.ImagesData.TERRAIN_TYPE;
import com.simpleworlds.utils.Conversions;

public class EntityAction {
  public ACTION_TYPE actionType;

  public boolean enabled = true;
  
  public int progress = 0;
  public int progressMax = 0;
  
  public EntityAction(ACTION_TYPE type) {
    actionType = type;
  }
  
  public void execute(HexSpace hex) {
    switch (actionType) {
      case BUILD_CITY:
        hex.structure = new StructureEntity(STRUCTURE_TYPE.CITY);
        break;
      case BUILD_SETTLEMENT:
        hex.structure = new StructureEntity(STRUCTURE_TYPE.SETTLEMENT);
        break;
      case BUILD_TOWN:
        hex.structure = new StructureEntity(STRUCTURE_TYPE.TOWN);
        break;
    	case CREATE_FARM:
    	  hex.terrainType = TERRAIN_TYPE.FARMLAND;
    	  hex.resource = new ResourceEntity(RESOURCE_TYPE.GRAIN);
    		break;
    	case EXPLORE:
    	  hex.visible = true;
    		break;
    	case FISH:
    	  harvestResource(hex, actionType);
    		break;
    	case GRAIN:
        harvestResource(hex, actionType);
    		break;
    	case HIDE:
        harvestResource(hex, actionType);
    		break;
    	case METAL:
        harvestResource(hex, actionType);
    		break;
    	case ORE:
        harvestResource(hex, actionType);
    		break;
    	case WOOD:
        harvestResource(hex, actionType);
    		break;
    	default:
    		break;
    }
  }
  
  public void harvestResource(HexSpace hex, ACTION_TYPE actionType) {
    if (hex.resource.harvestProgress >= ResourceEntity.HARVEST_PROGRESS_MAX) {
      MetaData.getActiveWorld().getActiveNation().resourceCount[Conversions.actionTypeToResourceType(actionType).ordinal()]++;
      hex.resource.harvestProgress = 0;
    } else {
      hex.resource.harvestProgress += MetaData.getActiveWorld().getActiveNation()
        .resourceHarvestEff[Conversions.actionTypeToResourceType(actionType).ordinal()];
    }
  }
}
