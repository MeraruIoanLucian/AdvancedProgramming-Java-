package org.example;

import org.example.commands.CommandShell;

/**
 * Main class to run the Image Manager application.
 */
public class Main {
    public static void main(String[] args) {
        ImageRepo repository = new ImageRepo();
        CommandShell shell = new CommandShell(repository);
        shell.run();
    }
}