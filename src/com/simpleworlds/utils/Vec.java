package com.simpleworlds.utils;

public class Vec {
  public int x;
  public int y;

  public Vec(int i, int j) {
    x = i;
    y = j;
  }

  public Vec add(int i, int j) {
    return new Vec(x+i, y+j);
  }

  public Vec add(Vec v) {
    return add(v.x, v.y);
  }

  public Vec addLocal(int i, int j) {
    x += i;
    y += j;
    return this;
  }

  public Vec addLocal(Vec v) {
    return addLocal(v.x, v.y);
  }

  public Vec sub(int i, int j) {
    return new Vec(x-i, y-j);
  }

  public Vec sub(Vec v) {
    return sub(v.x, v.y);
  }

  public Vec subLocal(int i, int j) {
    x -= i;
    y -= j;
    return this;
  }

  public Vec subLocal(Vec v) {
    return subLocal(v.x, v.y);
  }

  public Vec mul(int m) {
    return new Vec(x*m, y*m);
  }

  public Vec mulLocal(int m) {
    x *= m;
    y *= m;
    return this;
  }
  
  public boolean contains(Vec boundaries, Vec point) {
    return (point.x >= x - boundaries.x && point.x <= x + boundaries.x &&
    		point.y >= y - boundaries.y && point.y <= y + boundaries.y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
