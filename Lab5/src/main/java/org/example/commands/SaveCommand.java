package org.example.commands;

import org.example.ImageRepo;

/**
 * Command to save images to a JSON file.
 * Expected argument: filename.
 */
public class SaveCommand implements Command {
    private final String filename;

    public SaveCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(ImageRepo repo) throws CommandException {
        try {
            repo.save(filename);
            System.out.println("Images saved to " + filename);
        } catch (Exception e) {
            throw new CommandException("Error saving file: " + e.getMessage());
        }
    }
}