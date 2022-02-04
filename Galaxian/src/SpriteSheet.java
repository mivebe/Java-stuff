import java.awt.image.BufferedImage;

public class SpriteSheet {
    private final BufferedImage image;
    // Image Unit Size in pixels;
    private final int UNIT_SIZE = 32;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    // Usage grabImage(column, row, size)  if image is square
    // grabImage(column, row, sizeX, sizeY)  if image is not square
    public BufferedImage grabImage(int col, int row, int size){
        return grabImage(col,row,size,size);
    }

    public BufferedImage grabImage(int col, int row, int sizeX, int sizeY){
        return image.getSubimage((col * UNIT_SIZE) - UNIT_SIZE, (row * UNIT_SIZE ) - UNIT_SIZE, sizeX * UNIT_SIZE, sizeY * UNIT_SIZE);
//        BufferedImage img = image.getSubimage((col * UNIT_SIZE) - UNIT_SIZE, (row * UNIT_SIZE ) - UNIT_SIZE, sizeX * UNIT_SIZE, sizeY * UNIT_SIZE);
//        return img;
    }
}
