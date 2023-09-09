package minizelda;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    public static BufferedImage spritesheet;
    public static BufferedImage[] player_front;

    public static BufferedImage tileWall;

    public static BufferedImage[] enemy_front;

    // inimigo 1: 176 x 205
    // inimigo 2: 195 x 206

    public Spritesheet() throws IOException {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player_front = new BufferedImage[2];
        enemy_front = new BufferedImage[2];


        player_front[0] = Spritesheet.getSprite(0,11,16,16);
        player_front[1] = Spritesheet.getSprite(16,11,16,16);

        enemy_front[0] = Spritesheet.getSprite(176,206,16,16);
        enemy_front[1] = Spritesheet.getSprite(176+16,206,16,16);

        tileWall = Spritesheet.getSprite(316,234,16,16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x,y,width,height);
    }
}
