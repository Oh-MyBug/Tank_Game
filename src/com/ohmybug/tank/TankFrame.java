package com.ohmybug.tank;

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
    Image offScreenImage = null;
    private Player myTank;

    List<AbstractGameObject> objects;

    public static final TankFrame INSTANCE = new TankFrame();

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

        this.add(new Wall(300,200,50,10));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);

    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("bullets: " + bullets.size(), 10, 50);
//        g.drawString("enemies: " + enemyTanks.size(), 10, 70);
        g.setColor(c);

        myTank.paint(g);    // 具体画什么 交给坦克去做
        for (int i = objects.size()-1; i >= 0; i--)
            objects.get(i).paint(g);

//        for (int i = enemyTanks.size()-1; i >= 0; i --) {
//            if (!enemyTanks.get(i).isLive())
//                enemyTanks.remove(i);
//            else
//                enemyTanks.get(i).paint(g);
//        }
//        for (int i = explodes.size()-1; i >= 0; i --) {
//            if (!explodes.get(i).isLive())
//                explodes.remove(i);
//            else
//                explodes.get(i).paint(g);
//        }
//        for (int i = bullets.size() - 1; i >= 0; i --) {
//            for (int j = enemyTanks.size()-1; j >= 0; j --)
//                bullets.get(i).collidesWithTank(enemyTanks.get(j));
//
//            if (!bullets.get(i).isLive()) {
//                bullets.remove(i);
//            }
//            else {
//                bullets.get(i).paint(g);
//            }
//        }
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
