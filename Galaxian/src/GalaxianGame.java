import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;

import static java.awt.event.KeyEvent.*;

public class GalaxianGame extends Canvas implements Runnable{

    @Serial
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH/12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Galaxian Game";

    private boolean running = false;
    private Thread thread;

    private final BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean shooting = false;

    private Player p1;
    private Controller c;

    public void init(){
        requestFocus();

        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("/ships_sprite_sheet.png");
            background = loader.loadImage("/background.jpg");
        }catch(IOException e){
            e.printStackTrace();
        }

        // This need to be initialized before the player
        addKeyListener(new KeyboardInput(this));

        // This is initialized here but defined above because it is here where we use spriteSheet
        // otherwise it won't work
        p1 = new Player(200,200,this);

        c = new Controller(this);
    }

    private synchronized void start(){
        if(running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run(){
        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                // This resets the metrics display (otherwise they stack like 60fps -> 120fps -> 180fps -> etc.. while it actually 60 all the time)
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    private void tick(){
        p1.tick();
        c.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs ==null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        // Drawing can commence

        g.drawImage(image,0,0,getWidth(),getHeight(),this);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);

        p1.render(g);
        c.render(g);

        // Drawing shall cease
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case VK_LEFT -> p1.setVelX(-5);
            case VK_RIGHT -> p1.setVelX(5);
            case VK_UP -> p1.setVelY(-5);
            case VK_DOWN -> p1.setVelY(5);
            case VK_SPACE -> {
                if (!shooting) {
                    shooting = true;
                    c.addBullet(new Bullet(p1.getX(), p1.getY(), this));
                }
            }
        }

//        int key = e.getKeyCode();
//
//        if(key == KeyEvent.VK_LEFT){
//            p1.setVelX(-5);
//        }else if(key == KeyEvent.VK_RIGHT){
//            p1.setVelX(5);
//        }else if(key == KeyEvent.VK_UP){
//            p1.setVelY(-5);
//        }else if(key == KeyEvent.VK_DOWN){
//            p1.setVelY(5);
//        }else if(key == KeyEvent.VK_SPACE && !shooting){
//            shooting = true;
//            c.addBullet(new Bullet(p1.getX(), p1.getY(),this));
//        }
    }

    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()) {
            case VK_LEFT, VK_RIGHT -> p1.setVelX(0);
            case VK_UP, VK_DOWN -> p1.setVelY(0);
            case VK_SPACE -> shooting = false;
        }
//        int key = e.getKeyCode();
//
//        if(key == KeyEvent.VK_LEFT){
//            p1.setVelX(0);
//        }else if(key == KeyEvent.VK_RIGHT){
//            p1.setVelX(0);
//        }else if(key == KeyEvent.VK_UP){
//            p1.setVelY(0);
//        }else if(key == KeyEvent.VK_DOWN){
//            p1.setVelY(0);
//        }else if(key == KeyEvent.VK_SPACE){
//            shooting = false;
//        }
    }

    public static void main(String[] args){
        GalaxianGame game = new GalaxianGame();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
}
