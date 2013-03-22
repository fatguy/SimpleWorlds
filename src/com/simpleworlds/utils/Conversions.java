package com.simpleworlds.utils;

import com.simpleworlds.data.ImagesData.ACTION_TYPE;
import com.simpleworlds.data.ImagesData.RESOURCE_TYPE;

public class Conversions {
  public static ACTION_TYPE resourceTypeToActionType(RESOURCE_TYPE type) {
    for (ACTION_TYPE t : ACTION_TYPE.values()) {
      if (t.toString().equals(type.toString())) {
        return t;
      }
    }
    return null;
  }
  
  public static RESOURCE_TYPE actionTypeToResourceType(ACTION_TYPE type) {
    for (RESOURCE_TYPE t : RESOURCE_TYPE.values()) {
      if (t.toString().equals(type.toString())) {
        return t;
      }
    }
    return null;
  }
}
