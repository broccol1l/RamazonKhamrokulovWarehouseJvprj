package com.myproject.warehouse.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<String, Command> repository = new HashMap<>();

    private final Command wrongCommand = new WrongCommand();

    public CommandProvider() {
        repository.put("find", new FindCommand());
        repository.put("exit", new ExitCommand());
    }
    public Command getCommand(String commandName) {
        if (commandName == null) {
            return wrongCommand;
        }

        String key = commandName.toLowerCase();
        Command command = repository.getOrDefault(key, wrongCommand);

        return command;
    }
}