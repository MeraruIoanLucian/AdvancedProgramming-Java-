package org.example.commands;

import org.example.ImageRepo;

/**
 * Command to load images from a JSON file.
 * Expected argument: filename.
 */
public class LoadCommand implements Command {
    private final String filename;

    public LoadCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(ImageRepo repo) throws CommandException {
        try {
            repo.load(filename);
            System.out.println("Images loaded from " + filename);
        } catch (Exception e) {
            throw new CommandException("Error loading file: " + e.getMessage());
        }
    }
}