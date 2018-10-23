package controller;

import view.PgrfWindow;
import view.Raster;
import renderer.Renderer;
import fill.SeedFill;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PgrfController {


    private BufferedImage img; // objekt pro zápis pixelů
    private Canvas canvas; // plátno pro vykreslení BufferedImage
    private Renderer renderer;
    private Raster raster;
    private SeedFill seedFill;


    public PgrfController(PgrfWindow window) {

        initObjects(window);
        initListeners();
    }


    private void initObjects(PgrfWindow window) {
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        // inicializace plátna, do kterého budeme kreslit img
        canvas = new Canvas();

        renderer = new Renderer(raster);
        window.add(canvas); // vložit plátno do okna
        window.setVisible(true); // zobrazit okno

        seedFill = new SeedFill();
        seedFill.setBufferedImage(img);
    }


    private void initListeners() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                renderer.drawPixel(e.getX(), e.getY(), 0xffffff);
                seedFill.fill();
            }
        });
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                raster.clear();
                renderer.drawDda(400, 300, e.getX(), e.getY(), 0x00ffff);
            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_C){
                    raster.clear();
                }
            }
        });
    }

}

