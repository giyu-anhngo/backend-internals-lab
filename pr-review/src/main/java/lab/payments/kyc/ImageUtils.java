package lab.payments.kyc;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

public final class ImageUtils {

    private ImageUtils() {
    }

    public static byte[] resize(byte[] image, int width, int height) {
        try {
            BufferedImage source = ImageIO.read(new ByteArrayInputStream(image));
            BufferedImage target = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = target.createGraphics();
            g.drawImage(source, 0, 0, width, height, null);
            g.dispose();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(target, "png", out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
