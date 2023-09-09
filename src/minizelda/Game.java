package minizelda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGHT = 480;
    public static int SCALE = 3;
    public static Player player;
    public World world;
    public List<Enemy> enemies = new ArrayList<Enemy>();

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        try {
            new Spritesheet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player = new Player(32,32);
        world = new World();

        enemies.add(new Enemy(32, 32));
        enemies.add(new Enemy(32, 64));

    }

    public void tick() {

        player.tick();

        for (int enemy = 0; enemy < enemies.size(); enemy++) {
            enemies.get(enemy).tick();
        }
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(new Color(0,135,13));
        graphics.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);


        player.render(graphics);
        world.render(graphics);

        for (int enemy = 0; enemy < enemies.size(); enemy++) {
            enemies.get(enemy).render(graphics);
        }

        bufferStrategy.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda");
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }
    @Override
    public void run() {
        while (true) {
//            System.out.println("Chamando game looping");
            tick();
            render();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}
