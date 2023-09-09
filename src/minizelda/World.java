package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Blocks> blocks = new ArrayList<Blocks>();

    public World() {
        for (int createBlocksXAxis = 0; createBlocksXAxis < 15*2; createBlocksXAxis++) {
            blocks.add(new Blocks(createBlocksXAxis*32, 0));
        }

        for (int createBlocksXAxis = 0; createBlocksXAxis < 15*2; createBlocksXAxis++) {
            blocks.add(new Blocks(createBlocksXAxis*32, 480-32));
        }

        for (int createBlocksYAxis = 0; createBlocksYAxis < 15*2; createBlocksYAxis++) {
            blocks.add(new Blocks(0, createBlocksYAxis*32));
        }

        for (int createBlocksYAxis = 0; createBlocksYAxis < 15*2; createBlocksYAxis++) {
            blocks.add(new Blocks(640-32, createBlocksYAxis*32));
        }


        blocks.add(new Blocks(220,100));

    }

    public static boolean isFree(int x, int y) {
        for (int renderBlock = 0; renderBlock < blocks.size(); renderBlock++) {
            Blocks actuallyBlock = blocks.get(renderBlock);

            if (actuallyBlock.intersects(new Rectangle(x,y,32,32))) {
                return false;
            }
        }

        return true;
    }
    public void render(Graphics graphics) {
        for (int renderBlock = 0; renderBlock < blocks.size(); renderBlock++) {
            blocks.get(renderBlock).render(graphics);
        }
    }
}
