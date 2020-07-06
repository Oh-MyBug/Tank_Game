package com.ohmybug.tank.chainofresponsibility;

import com.ohmybug.tank.AbstractGameObject;

import java.io.Serializable;

public interface Collider extends Serializable {
    // return true: chain go on , return false: chain break;
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
