package com.simpleworlds.world.entities;

import com.simpleworlds.data.ImagesData;

public class Nation {
  public int resourceCount[] = new int[ImagesData.RESOURCE_TYPE.values().length];
  public int resourceHarvestEff[] = new int[ImagesData.RESOURCE_TYPE.values().length];
  
  public int structureBuildEff[] = new int[ImagesData.STRUCTURE_TYPE.values().length];
  
  public int unitTrainEff[] = new int[ImagesData.UNIT_TYPE.values().length];
  
  public Nation() {
    for (int i=0; i<resourceHarvestEff.length; i++) {
      resourceHarvestEff[i] = 100 + (int)Math.round(Math.random()*50);
    }
    
    for (int i=0; i<structureBuildEff.length; i++) {
      structureBuildEff[i] = 50 + (int)Math.round(Math.random()*50);
    }
    
    for (int i=0; i<unitTrainEff.length; i++) {
      unitTrainEff[i] = 50 + (int)Math.round(Math.random()*50);
    }
  }
}
