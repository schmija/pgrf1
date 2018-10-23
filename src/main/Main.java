package main;

import controller.PgrfController;
import view.PgrfWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PgrfWindow window = new PgrfWindow();
            new PgrfController(window);
            window.setVisible(true);
        });
    }
}
