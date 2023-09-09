package minizelda;

import java.awt.*;

public class Bullet extends Rectangle {

    public int direction = 1;
    public int speed = 8;
    public int frames = 0;
    public Bullet(int x, int y, int direction) {
        super(x+16, y+16, 10, 10);
        this.direction = direction;
    }

    public void tick() {
        x+=speed * direction;

        frames++;
        if (frames == 60) {
            Player.bullets.remove(this);
            return;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.yellow);
//        graphics.fillRect(x, y, width, height);
        graphics.fillOval(x, y, width, height);
    }
}
