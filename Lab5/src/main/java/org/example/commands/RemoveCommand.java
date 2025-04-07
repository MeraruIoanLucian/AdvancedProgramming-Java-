package org.example.commands;

import org.example.ImageRepo;

/**
 * Command to remove an image.
 * Expected argument: name.
 */
public class RemoveCommand implements Command {
    private final String name;

    public RemoveCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(org.example.ImageRepo repo) throws CommandException {
        boolean removed = repo.removeImage(name);
        if (removed) {
            System.out.println("Image removed: " + name);
        } else {
            throw new CommandException("Image not found: " + name);
        }
    }
}