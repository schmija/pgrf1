package main;

import java.awt.image.BufferedImage;

public class SeedFill implements Filler{
    private BufferedImage image;
    private int x, y, color, background;

    @Override
    public void setBufferedImage(BufferedImage image) {
        this.image = image;
    }

    public void init(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        background = image.getRGB(x, y);
    }

    @Override
    public void fill() {
        seed(x, y);
    }

    private void seed(int ax, int ay){
        if (ax >= 0 && ay >= 0 && ax < image.getWidth() && ay < image.getHeight()){
            if (background == image.getRGB(ax, ay)){
                image.setRGB(ax, ay, color);
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay +1);
                seed(ax, ay - 1);
            }
        }
    }
}
