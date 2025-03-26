package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ImageRepo {
    private List<ImageItem> images;

    public ImageRepo() {
        images = new ArrayList<>();
    }

    public void addImage(ImageItem image) {
        images.add(image);
    }


    public List<ImageItem> getImages() {
        return images;
    }


    public void displayImage(ImageItem image) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File imageFile = new File(image.filePath());
            if (imageFile.exists()) {
                try {
                    desktop.open(imageFile);
                } catch (IOException e) {System.out.println("Eroare la deschidere");}
            } else {
                System.out.println("Image file not found: " + image.filePath());
            }
        } else {
            System.out.println("Desktop is not supported on this platform.");
        }
    }
}