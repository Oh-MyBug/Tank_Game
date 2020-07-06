package com.ohmybug.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.get("gameWidth")),
                GAME_HEIGHT = Integer.parseInt(PropertyMgr.get("gameHeight")),
                GAME_LOC_X = Integer.parseInt(PropertyMgr.get("gameLocationX")),
                GAME_LOC_Y = Integer.parseInt(PropertyMgr.get("gameLocationY"));
    public static final TankFrame INSTANCE = new TankFrame();
    private Image offScreenImage = null;

    private GameModel gameModel = new GameModel();

    private TankFrame() {
        this.setTitle("Tank War");
        this.setLocation(GAME_LOC_X, GAME_LOC_Y);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());

    }

    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
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
            gameModel.getMyTank().keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            gameModel.getMyTank().keyReleased(e);
        }
    }

    public GameModel getGM(){
        return this.gameModel;
    }
}
