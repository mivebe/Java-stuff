import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        //Play Button
        if (mx >= Menu.BUTTON_X && mx <= Menu.BUTTON_X + Menu.BUTTON_WIDTH){
            if(my >= 150 && my <= 150 + Menu.BUTTON_HEIGHT ){
                //Pressed Play Button
                GamePanel.State = GamePanel.STATE.GAME;
            }
        }
        //Play Button
        if (mx >= Menu.BUTTON_X && mx <= Menu.BUTTON_X + Menu.BUTTON_WIDTH){
            if(my >= 250 && my <= 250 + Menu.BUTTON_HEIGHT ){
                //Pressed Play Button
                System.out.println("HELP");
            }
        }
        //Play Button
        if (mx >= Menu.BUTTON_X && mx <= Menu.BUTTON_X + Menu.BUTTON_WIDTH){
            if(my >= 350 && my <= 350 + Menu.BUTTON_HEIGHT ){
                //Pressed Play Button
                System.exit(1);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
