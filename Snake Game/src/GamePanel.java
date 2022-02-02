import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    public static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 200;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    boolean moved = false;
    private final Menu menu;
    public static enum STATE{
        MENU,
        GAME
    };
    public static STATE State = STATE.MENU;
    Timer timer;
    Random random;


    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new SnakeKeyAdapter());
        this.addMouseListener(new MouseInput());
        menu = new Menu();
        start();
    }

    public void start(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        if(State == STATE.GAME) {
            super.paintComponent(g);
            draw(g);
        }else{
            menu.render(g, applesEaten);
        }
    }
    public void renderMenu(Graphics g){
        menu.render(g,0);
    }
    public void draw(Graphics g){
        //Grid
        g.setColor(Color.GRAY);
        for(int i = 0;i < SCREEN_HEIGHT/UNIT_SIZE;i++){
            g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
            g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
        }
        //Apple
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        //Snake
        if(running) {
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            moved = false;
        }else {
            gameOver(g);
        }
        //Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics((g.getFont()));
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
    }
    public void move() {
            for (int i = bodyParts; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            switch (direction) {
                case 'U' -> y[0] = y[0] - UNIT_SIZE;
                case 'D' -> y[0] = y[0] + UNIT_SIZE;
                case 'L' -> x[0] = x[0] - UNIT_SIZE;
                case 'R' -> x[0] = x[0] + UNIT_SIZE;
            }
    }
    public void newApple(){
        appleX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
    }
    public void checkApple(){
        if((x[0] == appleX)&&(y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        //Does head collide with body?
        for(int i = bodyParts; i > 0 ; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }
        //Does head collide with left border
        if(x[0] < 0){
            running = false;
        }
        //Does head collide with right border
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //Does head collide with top border
        if(y[0] < 0){
            running = false;
        }
        //Does head collide with bottom border
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }

        if(!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //Clear Field
        g.setColor(Color.BLACK);
        g.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        //Show Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics scoreMetrics = getFontMetrics((g.getFont()));
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - scoreMetrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        //Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics gameOverMetrics = getFontMetrics((g.getFont()));
        g.drawString("Game Over", (SCREEN_WIDTH - gameOverMetrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class SnakeKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if (!moved) {
                if(State == STATE.GAME){
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                        case KeyEvent.VK_ESCAPE:
                        case KeyEvent.VK_SPACE:
                            State = STATE.MENU;
                            running = false;
                            break;
                    }
                moved = true;
                }else if(State == STATE.MENU){
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER:
                        case KeyEvent.VK_SPACE:
                            State = STATE.GAME;
                            running = true;
                            break;
                        case KeyEvent.VK_ESCAPE:
                            System.exit(1);
                            break;
                    }
                }
            }
        }
    }

}
