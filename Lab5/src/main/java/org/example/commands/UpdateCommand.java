package org.example.commands;

import org.example.ImageItem;
import org.example.ImageRepo;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Command to update an image.
 * Expected arguments: oldName, newName, newDate (YYYY-MM-DD), newTags (comma-separated), newFilePath.
 */
public class UpdateCommand implements Command {
    private final String oldName;
    private final String newName;
    private final LocalDate newDate;
    private final String[] newTags;
    private final String newFilePath;

    public UpdateCommand(String oldName, String newName, String newDateStr, String newTagsStr, String newFilePath) {
        this.oldName = oldName;
        this.newName = newName;
        this.newDate = LocalDate.parse(newDateStr);
        this.newTags = newTagsStr.split(",");
        this.newFilePath = newFilePath;
    }

    @Override
    public void execute(ImageRepo repo) throws CommandException {
        ImageItem updated = new ImageItem(newName, newDate, Arrays.asList(newTags), newFilePath);
        boolean updatedFlag = repo.updateImage(oldName, updated);
        if (updatedFlag) {
            System.out.println("Image updated: " + updated);
        } else {
            throw new CommandException("Image not found: " + oldName);
        }
    }
}