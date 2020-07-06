package com.ohmybug.tank.strategy;

import com.ohmybug.tank.*;

public class DoubleFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        int bX = player.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = player.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        if (player.getDirection() == Direction.D || player.getDirection() == Direction.U) {
            TankFrame.INSTANCE.add(new Bullet(bX, bY, Direction.D, player.getGroup()));
            TankFrame.INSTANCE.add(new Bullet(bX, bY, Direction.U, player.getGroup()));
        }
        if (player.getDirection() == Direction.L || player.getDirection() == Direction.R) {
            TankFrame.INSTANCE.add(new Bullet(bX, bY, Direction.L, player.getGroup()));
            TankFrame.INSTANCE.add(new Bullet(bX, bY, Direction.R, player.getGroup()));
        }
    }
}
