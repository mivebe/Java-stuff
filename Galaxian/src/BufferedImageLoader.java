import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BufferedImageLoader {
    public BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
    }
//    private BufferedImage image;
//    public BufferedImage loadImage(String path) throws IOException {
//        image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
//        return image;
//    }
}
