import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    static final int MENU_WIDTH = (int) (GamePanel.SCREEN_WIDTH/1.5);
    static final int MENU_HEIGHT = (int) (GamePanel.SCREEN_HEIGHT/1.5);
    static final int MENU_X = (GamePanel.SCREEN_WIDTH/2) - MENU_WIDTH/2;
    static final int MENU_Y = (GamePanel.SCREEN_HEIGHT/2) - MENU_HEIGHT/2;
    static final int BUTTON_WIDTH = 100;
    static final int BUTTON_HEIGHT = BUTTON_WIDTH/2;
    static final int BUTTON_X = (GamePanel.SCREEN_WIDTH/2) - BUTTON_WIDTH/2;
    private final int BUTTON_HPADDING = 20;
    private final int BUTTON_VPADDING = 15;


    public Rectangle playButton = new Rectangle(BUTTON_X,150,BUTTON_WIDTH,BUTTON_HEIGHT);
    public Rectangle helpButton = new Rectangle(BUTTON_X,250,BUTTON_WIDTH,BUTTON_HEIGHT);
    public Rectangle quitButton = new Rectangle(BUTTON_X,350,BUTTON_WIDTH,BUTTON_HEIGHT);

    public void render(Graphics g,int applesEaten){
        Graphics2D g2d = (Graphics2D) g;

        //Clear Field
        g.setColor(Color.BLACK);
        g.fillRect(MENU_X, MENU_Y, (int) MENU_WIDTH, (int) MENU_HEIGHT);

        //Pause Menu Text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics menuTitleMetrics = getFontMetrics((g.getFont()));
        g.drawString("Pause Menu", (GamePanel.SCREEN_WIDTH - menuTitleMetrics.stringWidth("Pause Menu"))/2, GamePanel.SCREEN_HEIGHT/8);

        //Render Buttons
        g2d.draw(playButton);
        g.setFont(new Font("arial",Font.BOLD,30));
        g.drawString("Play",playButton.x + BUTTON_HPADDING, (playButton.y + BUTTON_HEIGHT) - BUTTON_VPADDING);

        g2d.draw(helpButton);
        g.drawString("Help",helpButton.x + BUTTON_HPADDING, (helpButton.y + BUTTON_HEIGHT) - BUTTON_VPADDING);

        g2d.draw(quitButton);
        g.drawString("Quit",quitButton.x + BUTTON_HPADDING, (quitButton.y + BUTTON_HEIGHT) - BUTTON_VPADDING);
    }
}

