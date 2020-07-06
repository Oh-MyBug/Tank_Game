package com.ohmybug.tank;

import java.awt.*;

public class Wall {
    private int x,y,width,height;

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);
        g.setColor(c);
    }
}
