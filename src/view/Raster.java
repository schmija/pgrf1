package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends Canvas{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private BufferedImage img;
    private static final int FPS = 1000/30;

    public Raster(){
        this.img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        setLoop();
    }

    private void setLoop() {
        // časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazil aktuální img
                getGraphics().drawImage(img, 0, 0, null);
                // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
            }
        }, 0, FPS);
    }

    public void clear() {
        // https://stackoverflow.com/a/5843470
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, 800, 600);
    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
    }

    public int getPixel(int x, int y){
        return img.getRGB(x, y);
    }
}
