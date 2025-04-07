package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

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

    public boolean removeImage(String name) {
        return images.removeIf(img -> img.name().equalsIgnoreCase(name));
    }

    public boolean updateImage(String name, ImageItem updatedImage) {
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).name().equalsIgnoreCase(name)) {
                images.set(i, updatedImage);
                return true;
            }
        }
        return false;
    }



    public List<ImageItem> getImages() {
        return images;
    }

    public void save(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), images);
    }

    public void load(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, ImageItem.class);
        images = mapper.readValue(new File(filename), listType);
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