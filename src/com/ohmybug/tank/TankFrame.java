package com.ohmybug.tank;

import com.ohmybug.tank.chainofresponsibility.BulletTankCollider;
import com.ohmybug.tank.chainofresponsibility.Collider;
import com.ohmybug.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.get("gameWidth")),
                GAME_HEIGHT = Integer.parseInt(PropertyMgr.get("gameHeight")),
                GAME_LOC_X = Integer.parseInt(PropertyMgr.get("gameLocationX")),
                GAME_LOC_Y = Integer.parseInt(PropertyMgr.get("gameLocationY"));
    public static final TankFrame INSTANCE = new TankFrame();
    private Image offScreenImage = null;
    private List<AbstractGameObject> objects;
    private Player myTank;

    ColliderChain chain = new ColliderChain();

    private TankFrame() {
        this.setTitle("Tank War");
        this.setLocation(GAME_LOC_X, GAME_LOC_Y);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());

        initGameObjects();
    }


    private void initGameObjects() {
        myTank = new Player(100, 100, Direction.R, Group.GOOD);
        objects = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for (int i = 0; i < tankCount; i++)
            this.add(new Tank(100 + 50*i,200, Direction.D, Group.BAD));

        this.add(new Wall(300,200,250,100));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("objects: " + objects.size(), 10, 50);
        g.setColor(c);

        myTank.paint(g);    // 具体画什么 交给坦克去做
        for (int i = 0; i < objects.size(); i++) {
            if (!objects.get(i).isLive()){
                objects.remove(i);
                break;
            }
            AbstractGameObject go1 = objects.get(i);
            for (int j = 0; j < objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1,go2);
            }
            if (objects.get(i).isLive())
                objects.get(i).paint(g);
        }
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
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
