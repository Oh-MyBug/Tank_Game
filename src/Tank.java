import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x, y;
    private Direction direction;
    private boolean bL, bU, bR, bD;

    public static final int SPEED = 5;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void paint(Graphics g){
        g.fillRect(x, y, 50, 50);
        move();
    }


    private void setMainDir() {
        if (!bL && !bU && !bR && !bD)
            direction = Direction.S;
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
        }
        setMainDir();
    }

    private void move(){
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
