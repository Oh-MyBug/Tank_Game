package com.ohmybug.tank.chainofresponsibility;

import com.ohmybug.tank.AbstractGameObject;

public interface Collider {
    // return true: chain go on , return false: chain break;
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
