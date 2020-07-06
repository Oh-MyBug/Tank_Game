package com.ohmybug.tank;

import java.awt.*;

public class Wall extends AbstractGameObject {
    private int x,y,width,height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);
        g.setColor(c);
    }
}
