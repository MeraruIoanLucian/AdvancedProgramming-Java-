package com.game;

import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {
    public MainFrame() {
        super("Game GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        add(new ConfigurationPanel(), BorderLayout.NORTH);
        add(new DrawingPanel(), BorderLayout.CENTER);
        add(new ControlPanel(), BorderLayout.SOUTH);
    }
}