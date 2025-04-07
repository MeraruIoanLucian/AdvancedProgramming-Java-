package com.game;

import javax.swing.*;
import java.awt.*;

class DrawingPanel extends JPanel {
    public DrawingPanel() {
        setBackground(Color.lightGray);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(100, 150, 10, 10);
        g.fillOval(200, 10, 10, 10); 
        g.fillOval(300, 80, 10, 10); 
        g.fillOval(400, 90, 10, 10); 
        g.fillOval(450, 100, 10, 10); 
    }
}