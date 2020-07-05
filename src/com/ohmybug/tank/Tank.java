package com.ohmybug.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x, y;
    private Direction direction;
    private boolean bL, bU, bR, bD;
    private boolean moving = false;
    private Group group;
    private TankFrame tankFrame;

    public static final int SPEED = 5;

    public Tank(int x, int y, Direction direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        if (this.group == Group.GOOD) {
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
        }
        if (this.group == Group.BAD) {
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
        tankFrame.add(new Bullet(x,y,direction,group));
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
}
