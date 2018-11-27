package main;

import controller.PgrfController;
import controller.PgrfController3D;
import view.PgrfWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PgrfWindow window = new PgrfWindow();
//            new PgrfController(window);
            new PgrfController3D(window.getRaster());
            window.setVisible(true);
        });
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
    }
}
