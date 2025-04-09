package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.image.BufferedImage;

/**
 * Control panel for game actions.
 */
public class ControlPanel extends JPanel {
    private GameModel model;
    private DrawingPanel drawingPanel;

    public ControlPanel(GameModel model, DrawingPanel drawingPanel) {
        this.model = model;
        this.drawingPanel = drawingPanel;
        setLayout(new FlowLayout());
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exportButton = new JButton("Export PNG");
        JButton reportButton = new JButton("Report Score");
        JButton exitButton = new JButton("Exit");

        loadButton.addActionListener((ActionEvent e) -> loadGame());
        saveButton.addActionListener((ActionEvent e) -> saveGame());
        exportButton.addActionListener((ActionEvent e) -> exportPNG());
        reportButton.addActionListener((ActionEvent e) -> showReport());
        exitButton.addActionListener((ActionEvent e) -> System.exit(0));

        add(loadButton);
        add(saveButton);
        add(exportButton);
        add(reportButton);
        add(exitButton);
    }

    private void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game.dat"))) {
            out.writeObject(model);
            JOptionPane.showMessageDialog(this, "Game saved successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving game: " + ex.getMessage());
        }
    }

    private void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game.dat"))) {
            GameModel loaded = (GameModel) in.readObject();
            model.getDots().clear();
            model.getDots().addAll(loaded.getDots());
            model.getLines().clear();
            model.getLines().addAll(loaded.getLines());
            JOptionPane.showMessageDialog(this, "Game loaded successfully.");
            drawingPanel.repaint();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error loading game: " + ex.getMessage());
        }
    }

    private void exportPNG() {
        BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        drawingPanel.paint(g2);
        g2.dispose();
        try {
            javax.imageio.ImageIO.write(image, "png", new File("gameboard.png"));
            JOptionPane.showMessageDialog(this, "Exported PNG as gameboard.png");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error exporting PNG: " + ex.getMessage());
        }
    }

    private void showReport() {
        double currentScore = model.getLines().stream().mapToDouble(Line::length).sum();
        double optimalScore = MSTCalculator.computeMSTWeight(model.getDots());
        String message = "Current Score: " + currentScore + "\nOptimal Score (MST): " + optimalScore;
        JOptionPane.showMessageDialog(this, message, "Score Report", JOptionPane.INFORMATION_MESSAGE);
    }
}