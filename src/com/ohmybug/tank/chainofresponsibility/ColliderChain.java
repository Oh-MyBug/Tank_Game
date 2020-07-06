package com.ohmybug.tank.chainofresponsibility;

import com.ohmybug.tank.AbstractGameObject;
import com.ohmybug.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain {
    private List<Collider> colliders;

    public ColliderChain() {
        initColliders();
    }

    private void initColliders(){
        colliders = new ArrayList<>();
        String[] collidersString = PropertyMgr.get("colliders").split(",");
        try {
            for (String name : collidersString){
                Class clazz = Class.forName("com.ohmybug.tank.chainofresponsibility." + name);
                Collider collider = (Collider) clazz.getConstructor().newInstance();
                colliders.add(collider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collide(AbstractGameObject go1, AbstractGameObject go2){
        for (Collider collider : colliders)
            if (!collider.collide(go1,go2))
                break;
    }
}
