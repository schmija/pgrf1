package main;

import java.awt.image.BufferedImage;

public interface Filler {

    void setBufferedImage(BufferedImage image);
    void fill();
}
