package com.ohmybug.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    private int x,y;
    private Direction direction;
    private Group group;
    private boolean live = true;

    public Bullet(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g){
        switch (direction) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        switch (direction){
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
        boundsCheck();
    }

    public void collidesWithTank(Tank tank){
        if (!this.isLive() || !tank.isLive()) return;
        if (this.group == tank.getGroup()) return;
        Rectangle rectBullet = new Rectangle(x,y,ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),
                ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());
        if (rectBullet.intersects(rectTank)){
            this.die();
            tank.die();
        }
    }

    private void boundsCheck() {
        if (x > TankFrame.GAME_WIDTH || x < 0 || y > TankFrame.GAME_HEIGHT || y < 0)
            live = false;
    }

    public void die(){
        this.setLive(false);
    }
}
