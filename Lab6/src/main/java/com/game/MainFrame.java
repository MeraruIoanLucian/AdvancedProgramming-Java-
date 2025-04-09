package com.game;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the application.
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        super("Game GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        GameModel model = new GameModel();
        // Optionally, add some initial random dots:
        for (int i = 0; i < 10; i++) {
            int x = (int)(Math.random() * 700) + 50;
            int y = (int)(Math.random() * 400) + 50;
            model.addDot(new Dot(x, y));
        }

        ConfigurationPanel configPanel = new ConfigurationPanel(); // From your compulsory part
        DrawingPanel drawingPanel = new DrawingPanel(model);
        ControlPanel controlPanel = new ControlPanel(model, drawingPanel);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
}