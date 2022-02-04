import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    GalaxianGame game;

    public KeyboardInput(GalaxianGame game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
    }
}
