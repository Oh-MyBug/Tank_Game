import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
    private Tank myTank;
    private Tank enemyTank;
    public TankFrame(){
        this.setTitle("Tank War");
        this.setLocation(400,100);
        this.setSize(800,600);

        this.addKeyListener(new TankKeyListener());

        myTank = new Tank(100, 100, Direction.R);
        enemyTank = new Tank(200, 200, Direction.D);

    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        enemyTank.paint(g);
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
