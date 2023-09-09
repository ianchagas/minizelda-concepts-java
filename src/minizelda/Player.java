package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
    public int speed = 4;

    public boolean right, up, down, left;

    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean shoot = false;

    public int lastDirectionWalk = 1;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        boolean moved = false;

        if (right && World.isFree(x+speed, y)) {
            x+=speed;
            moved = true;
            lastDirectionWalk = 1;

        } else if (left && World.isFree(x-speed, y)) {
            x-=speed;
            moved = true;
            lastDirectionWalk = -1;
        }

        if (up && World.isFree(x, y-speed)) {
            y-=speed;
            moved = true;

        } else if (down && World.isFree(x, y+speed)) {
            y+=speed;
            moved = true;
        }

        if (moved) {
            curFrames++;

            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;

                if (curAnimation == Spritesheet.player_front.length) {
                    curAnimation = 0;
                }
            }
        }

        if (shoot) {
            shoot = false;
            bullets.add(new Bullet(x, y, lastDirectionWalk));
        }


        for (int bullet = 0; bullet < bullets.size(); bullet++) {
            bullets.get(bullet).tick();
        }

    }

    public void render(Graphics graphics) {
//        graphics.setColor(Color.blue);
//        graphics.fillRect(x, y, width, height);
        graphics.drawImage(Spritesheet.player_front[curAnimation], x,y,32,32, null);

        for (int bullet = 0; bullet < bullets.size(); bullet++) {
            bullets.get(bullet).render(graphics);
        }
    }
}
