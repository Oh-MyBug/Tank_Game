package com.ohmybug.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    private Tank myTank;
    private Tank enemyTank;
    private Bullet bullet;

    public TankFrame(){
        this.setTitle("Tank War");
        this.setLocation(400,100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());

        myTank = new Tank(100, 100, Direction.R, Group.GOOD, this);
        enemyTank = new Tank(200, 200, Direction.D, Group.BAD, this);
    }

    public void add(Bullet bullet){
        this.bullet = bullet;
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);    // 具体画什么 交给坦克去做
        enemyTank.paint(g);
        if (bullet != null)
            bullet.paint(g);
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

}
