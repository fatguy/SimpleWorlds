package com.simpleworlds.world.entities;

import java.util.ArrayList;
import java.util.List;

import com.simpleworlds.data.ImagesData;
import com.simpleworlds.data.ImagesData.EFFECT_TYPE;
import com.simpleworlds.utils.Vec;

public class EffectEntity extends GameEntity {
  public EFFECT_TYPE effectType;

  public EffectEntity(EFFECT_TYPE type) {
    effectType = type;
  }

  public void drawAtScreenPos(Vec screen) {
    ImagesData.drawAt(effectType, screen);
  }

  public List<EntityAction> getEntityActions(HexSpace hex) {
    ArrayList<EntityAction> actions = new ArrayList<EntityAction>();

    return actions;
  }
}
