package com.game;

import javax.swing.*;
import java.awt.*;

class ConfigurationPanel extends JPanel {
    public ConfigurationPanel() {
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Number of Dots:");
        JTextField textField = new JTextField(5);
        JButton createButton = new JButton("Create Game");

        add(label);
        add(textField);
        add(createButton);
    }
}