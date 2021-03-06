package com.ohmybug.tank;

import com.ohmybug.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    private List<AbstractGameObject> objects;
    private Player myTank;
    private ColliderChain chain = new ColliderChain();

    public GameModel() {
        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100, 100, Direction.R, Group.GOOD);
        objects = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for (int i = 0; i < tankCount; i++)
            this.add(new Tank(100 + 50*i,200, Direction.D, Group.BAD));

//        this.add(new Wall(300,200,250,100));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    public void paint(Graphics g){
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
        }
        for (int i = 0; i < objects.size(); i++) {

            AbstractGameObject go1 = objects.get(i);
            for (int j = 0; j < objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1,go2);
            }
            if (objects.get(i).isLive())
                objects.get(i).paint(g);
        }
    }

    public Player getMyTank(){
        return myTank;
    }
}
