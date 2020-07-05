package com.ohmybug.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private static final int TANK_WIDTH = 48, TANK_HEIGHT = 48;
    private int x, y;
    private Direction direction;
    private boolean bL, bU, bR, bD;
    private boolean moving = false;
    private boolean live = true;
    private Group group;

    public static final int SPEED = 5;

    public Player(int x, int y, Direction direction, Group group) {
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g){
        if (!this.isLive()) return;

        switch (direction) {
            case L:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
        }
        move();
    }


    private void setMainDir() {
        // 所有方向键都释放 Tank停止
        if (!bL && !bU && !bR && !bD) {
            moving = false;
            return;
        }
        // 任意方向键被按动 Tank移动
        moving = true;
        if (bL && !bU && !bR && !bD)
            direction = Direction.L;
        if (!bL && bU && !bR && !bD)
            direction = Direction.U;
        if (!bL && !bU && bR && !bD)
            direction = Direction.R;
        if (!bL && !bU && !bR && bD)
            direction = Direction.D;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setMainDir();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setMainDir();
    }

    private void fire() {
        int bX = x + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = y + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullet(bX,bY,direction,group));
    }

    private void move(){
        if (!moving) return;
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
    }

    public void die() {
        this.setLive(false);
    }

}
