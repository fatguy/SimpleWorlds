package com.simpleworlds.world.entities;

import java.util.List;

import com.simpleworlds.utils.Vec;

public abstract class GameEntity {
  public static enum ENTITY_TYPE {RESOURCE, UNIT};

  public ENTITY_TYPE entityType;

  public abstract void drawAtScreenPos(Vec screen);

  public abstract List<EntityAction> getEntityActions(HexSpace hex);
}
