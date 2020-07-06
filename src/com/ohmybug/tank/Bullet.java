package com.ohmybug.tank;

import java.awt.*;

public class Bullet extends AbstractGameObject{
    public static final int SPEED = 10,
            BULLET_WIDTH = ResourceMgr.bulletU.getWidth(),
            BULLET_HEIGHT = ResourceMgr.bulletU.getHeight();
    private int x,y;
    private Direction direction;
    private Group group;
    private boolean live = true;
    private Rectangle rectBullet;

    public Bullet(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        rectBullet = new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
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

        // 更新子弹的方块位置
        rectBullet.x = x;
        rectBullet.y = y;

        boundsCheck();
    }

    public Rectangle getRect(){
        return rectBullet;
    }

    private void boundsCheck() {
        if (x > TankFrame.GAME_WIDTH || x < 0 || y > TankFrame.GAME_HEIGHT || y < 0)
            live = false;
    }

    public void die(){
        this.setLive(false);
    }
}
