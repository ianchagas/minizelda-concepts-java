package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle {
    public int speed = 2;

    public int right = 1, up = 0, down = 0, left = 0;

    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean shoot = false;

    public int lastDirectionWalk = 1;

    public Enemy(int x, int y) {
        super(x, y, 32, 32);
    }

    public void chasePlayer() {
        Player player = Game.player;

        if (x < player.x && World.isFree(x + speed, y)) {
            if (new Random().nextInt(100) < 50) {
                x+= speed;
            }
        } else if (x > player.x && World.isFree(x - speed, y)) {
            if (new Random().nextInt(100) < 50) {
                x-= speed;
            }
        }

        if (y < player.y && World.isFree(x, y + speed)) {
            if (new Random().nextInt(100) < 50) {
                y+= speed;
            }

        } else if (y > player.y && World.isFree(x, y - speed)) {
            if (new Random().nextInt(100) < 50) {
                y-= speed;
            }
        }
    }

    public void tick() {
        boolean moved = true;

        chasePlayer();

        if (moved) {
            curFrames++;

            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;

                if (curAnimation == Spritesheet.enemy_front.length) {
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
        graphics.drawImage(Spritesheet.enemy_front[curAnimation], x,y,32,32, null);

        for (int bullet = 0; bullet < bullets.size(); bullet++) {
            bullets.get(bullet).render(graphics);
        }
    }
}
