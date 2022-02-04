import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private final LinkedList<Bullet> b = new LinkedList<>();

    Bullet TempBullet;

    GalaxianGame game;

    public Controller(GalaxianGame game){
        this.game = game;
    }

    public void tick(){
        for(int i = 0; i < b.size(); i++){
            TempBullet = b.get(i);

            if(TempBullet.getY() < 0){
                removeBullet(TempBullet);
            }

            TempBullet.tick();
        }
    }

    public void render(Graphics g){
        for (Bullet bullet : b) {
            TempBullet = bullet;

            TempBullet.render(g);
        }
//        for(int i = 0; i < b.size(); i++){
//            TempBullet = b.get(i);
//
//            TempBullet.render(g);
//        }
    }

    public  void addBullet(Bullet block){
        b.add(block);
    }
    public  void removeBullet(Bullet block){
        b.remove(block);
    }
}
