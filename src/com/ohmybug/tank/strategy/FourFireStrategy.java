package com.ohmybug.tank.strategy;

import com.ohmybug.tank.*;

public class FourFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        int bX = player.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = player.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        for (Direction direction: Direction.values())
            TankFrame.INSTANCE.getGM().add(new Bullet(bX,bY,direction,player.getGroup()));
    }
}
