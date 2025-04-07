package org.example.commands;


public interface Command {
    void execute(org.example.ImageRepo repo) throws CommandException;
}