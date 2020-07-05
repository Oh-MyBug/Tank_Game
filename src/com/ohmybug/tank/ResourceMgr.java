package com.ohmybug.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[29];

    static {
        try {
            goodTankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.gif"));
            goodTankR = ImageUtil.rorateImage(goodTankD, -90);
            goodTankL = ImageUtil.rorateImage(goodTankD, 90);
            goodTankU = ImageUtil.rorateImage(goodTankD, 180);

            badTankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.gif"));
            badTankR = ImageUtil.rorateImage(badTankD, -90);
            badTankL = ImageUtil.rorateImage(badTankD, 90);
            badTankU = ImageUtil.rorateImage(badTankD, 180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rorateImage(bulletU, -90);
            bulletR = ImageUtil.rorateImage(bulletU, 90);
            bulletD = ImageUtil.rorateImage(bulletU, 180);

            for (int i = 0; i < 29; i++)
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
