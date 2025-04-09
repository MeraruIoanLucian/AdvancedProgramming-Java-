package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel for drawing the game board in retained mode.
 */
public class DrawingPanel extends JPanel {
    private GameModel model;
    private Dot selectedDot; // For selecting a dot to connect with another
    private final int DOT_RADIUS = 5;

    public DrawingPanel(GameModel model) {
        this.model = model;
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getPoint());
            }
        });
    }

    private void handleClick(Point p) {
        // Check if the click is on an existing dot.
        Dot clicked = null;
        for (Dot dot : model.getDots()) {
            if (dot.containsPoint(p)) {
                clicked = dot;
                break;
            }
        }
        if (clicked == null) {
            // If not, add a new dot.
            Dot newDot = new Dot(p.x, p.y);
            model.addDot(newDot);
            repaint();
        } else {
            // If a dot is already selected and it's not the same,
            // create a line between the two, using current player's color.
            if (selectedDot != null && selectedDot != clicked) {
                Color color = model.getCurrentPlayer() == 0 ? Color.BLUE : Color.RED;
                Line line = new Line(selectedDot, clicked, color);
                model.addLine(line);
                model.switchPlayer();
                selectedDot = null;
                repaint();
            } else {
                // Otherwise, select the clicked dot.
                selectedDot = clicked;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw all dots.
        g.setColor(Color.BLACK);
        for (Dot dot : model.getDots()) {
            g.fillOval(dot.getX()-DOT_RADIUS, dot.getY()-DOT_RADIUS, 2*DOT_RADIUS, 2*DOT_RADIUS);
        }
        // Draw all lines.
        for (Line line : model.getLines()) {
            g.setColor(line.getColor());
            int x1 = line.getDot1().getX();
            int y1 = line.getDot1().getY();
            int x2 = line.getDot2().getX();
            int y2 = line.getDot2().getY();
            g.drawLine(x1, y1, x2, y2);
        }
    }
}