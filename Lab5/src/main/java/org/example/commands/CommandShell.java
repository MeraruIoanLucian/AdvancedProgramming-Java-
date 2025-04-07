package org.example.commands;

import org.example.ImageRepo;

import java.util.Scanner;

/**
 * A shell that reads and executes commands.
 */
public class CommandShell {
    private final ImageRepo repository;

    public CommandShell(ImageRepo repository) {
        this.repository = repository;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Image Repository Shell. Type 'exit' to quit.");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                Command command = parseCommand(line);
                command.execute(repository);
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Parses an input line into a Command.
     * Expected format: commandName [args...]
     */
    private Command parseCommand(String line) throws CommandException {
        String[] parts = line.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";
        switch (cmd) {
            case "add":
                // Expected: add name date tags filePath
                String[] addArgs = args.split("\\s+", 4);
                if (addArgs.length < 4)
                    throw new CommandException("Usage: add <name> <date:YYYY-MM-DD> <tags,comma-separated> <filePath>");
                return new AddCommand(addArgs[0], addArgs[1], addArgs[2], addArgs[3]);
            case "remove":
                // Expected: remove name
                if (args.isEmpty())
                    throw new CommandException("Usage: remove <name>");
                return new RemoveCommand(args);
            case "update":
                // Expected: update oldName newName newDate newTags newFilePath
                String[] updateArgs = args.split("\\s+", 5);
                if (updateArgs.length < 5)
                    throw new CommandException("Usage: update <oldName> <newName> <newDate:YYYY-MM-DD> <newTags,comma-separated> <newFilePath>");
                return new UpdateCommand(updateArgs[0], updateArgs[1], updateArgs[2], updateArgs[3], updateArgs[4]);
            case "load":
                // Expected: load filename
                if (args.isEmpty())
                    throw new CommandException("Usage: load <filename>");
                return new LoadCommand(args);
            case "save":
                // Expected: save filename
                if (args.isEmpty())
                    throw new CommandException("Usage: save <filename>");
                return new SaveCommand(args);
            case "report":
                // Expected: report outputFilename
                if (args.isEmpty())
                    throw new CommandException("Usage: report <outputFilename>");
                return new ReportCommand(args);
            default:
                throw new CommandException("Unknown command: " + cmd);
        }
    }
}