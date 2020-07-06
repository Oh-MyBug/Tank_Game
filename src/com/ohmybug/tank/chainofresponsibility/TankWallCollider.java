package com.ohmybug.tank.chainofresponsibility;

import com.ohmybug.tank.AbstractGameObject;
import com.ohmybug.tank.Bullet;
import com.ohmybug.tank.Tank;
import com.ohmybug.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Wall){
            Tank t = (Tank) go1;
            Wall w = (Wall) go2;
            if (t.isLive()){
                if (t.getRect().intersects(w.getRect())){
                    t.back();
                    return false;
                }
            }
        }else if(go2 instanceof Bullet && go1 instanceof Wall){
            return collide(go2, go1);
        }
        return true;
    }
}
