package com.ohmybug.tank;

import java.awt.*;
import java.util.Random;

public class Tank extends AbstractGameObject {
    private static final int TANK_WIDTH = ResourceMgr.badTankL.getWidth(),
            TANK_HEIGHT = ResourceMgr.badTankL.getHeight();
    private int x, y;
    private Direction direction;
    private boolean moving = true;
    private boolean live = true;
    private Group group;

    private int oldX, oldY;

    public static final int SPEED = Integer.parseInt(PropertyMgr.get("enemySpeed"));;

    public Tank(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;

        oldX = x;
        oldY = y;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g){
        if (!this.isLive()) return;
        switch (direction) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void fire() {
        int bX = x + TANK_WIDTH/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = y + TANK_HEIGHT/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullet(bX,bY,direction,group));
    }

    private void move(){
        if (!moving) return;
        oldX = x;
        oldY = y;
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
        randomDirection();
        if (random.nextInt(100) > 90) {
            fire();
        }
    }

    private void boundsCheck() {
        if (x + TANK_WIDTH > TankFrame.GAME_WIDTH || x < 0
                || y + TANK_HEIGHT > TankFrame.GAME_HEIGHT || y < 0)
            this.back();
    }

    private void back() {
        x = oldX;
        y = oldY;
    }

    private Random random = new Random();
    private void randomDirection(){
        if (random.nextInt(100) > 90)
            this.direction = Direction.randomDirection();
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x,y));
    }

}
