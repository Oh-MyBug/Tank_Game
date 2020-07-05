import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ImageTest {
    @Test
    public void testLoadImage() {
        BufferedImage image1 = null;
        BufferedImage image2 = null;
        try {
            image1 = ImageIO.read(new File("src/images/BadTank1.png"));
            assertNotNull(image1);

            image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage rorateImage(final BufferedImage bufferedImage, final int degree){
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2D;
        (graphics2D = (img = new BufferedImage(w,h,type))
                    .createGraphics()).setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree), w/2, h/2);
        graphics2D.drawImage(bufferedImage, 0, 0, null);
        graphics2D.dispose();
        return img;
    }
}
