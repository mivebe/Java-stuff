import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private double x;
    private double y;

    private double velX = 0;
    private double velY = 0;

    private int PLAYER_MOVEMENT_SPEED = 1;
    private final int RIGHT_BORDER;
    private final int BOTTOM_BORDER;

    private final BufferedImage player;

    public Player(double x, double y, GalaxianGame game){
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        player = ss.grabImage(3,10,1,1);

        int PLAYER_WIDTH = player.getWidth();
        int PLAYER_HEIGHT = player.getHeight();
        int SCREEN_WIDTH = GalaxianGame.WIDTH * GalaxianGame.SCALE;
        int SCREEN_HEIGHT = GalaxianGame.HEIGHT * GalaxianGame.SCALE;
        this.RIGHT_BORDER = SCREEN_WIDTH - PLAYER_WIDTH;
        this.BOTTOM_BORDER = SCREEN_HEIGHT - PLAYER_HEIGHT;
    }

    public void tick(){
        x += velX;
        y += velY;

        if(x <= 0) x = 0;
        if(x >= RIGHT_BORDER) x = RIGHT_BORDER;
        if(y <= 0) y = 0;
        if(y >= BOTTOM_BORDER) y = BOTTOM_BORDER;
    }

    public void render(Graphics g){
        g.drawImage(player, (int) x, (int) y,null);
    }
    public double getX(){ return x; }
    public double getY(){ return y; }
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }
    public int getMS(){ return PLAYER_MOVEMENT_SPEED; }
    public void setMS(int ms){ this.PLAYER_MOVEMENT_SPEED = ms; }
    public void setVelX(double velX){ this.velX = velX * PLAYER_MOVEMENT_SPEED; }
    public void setVelY(double velY){ this.velY = velY * PLAYER_MOVEMENT_SPEED; }
}
