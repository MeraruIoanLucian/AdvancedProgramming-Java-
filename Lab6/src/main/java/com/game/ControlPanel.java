package com.game;

import javax.swing.*;
import java.awt.*;

class ControlPanel extends JPanel {
    public ControlPanel() {
        setLayout(new FlowLayout());
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));

        add(loadButton);
        add(saveButton);
        add(exitButton);
    }
}
