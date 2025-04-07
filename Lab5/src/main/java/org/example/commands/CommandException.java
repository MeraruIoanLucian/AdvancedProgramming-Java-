package org.example.commands;

/**
 * Exception thrown when a command fails.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}