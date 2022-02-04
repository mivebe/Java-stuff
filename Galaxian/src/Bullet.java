import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private double x;
    private double y;

    BufferedImage image;

    public Bullet(double x, double y, GalaxianGame game){
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        image = ss.grabImage(1,2,1,1);
    }
    public void tick(){
        y -= 10;
    }

    public void render(Graphics g){
        g.drawImage(image,(int) x,(int) y, null);
    }

    public double getY(){
        return y;
    }
}
