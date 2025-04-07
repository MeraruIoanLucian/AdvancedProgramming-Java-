package org.example.commands;

import org.example.ImageItem;
import org.example.ImageRepo;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Command to add an image.
 * Expected arguments: name, date (YYYY-MM-DD), tags (comma-separated), filePath.
 */
public class AddCommand implements Command {
    private final String name;
    private final LocalDate date;
    private final String[] tags;
    private final String filePath;

    public AddCommand(String name, String dateStr, String tagsStr, String filePath) {
        this.name = name;
        this.date = LocalDate.parse(dateStr);
        this.tags = tagsStr.split(",");
        this.filePath = filePath;
    }

    @Override
    public void execute(ImageRepo repo) throws CommandException {
        ImageItem image = new ImageItem(name, date, Arrays.asList(tags), filePath);
        repo.addImage(image);
        System.out.println("Image added: " + image);
    }
}