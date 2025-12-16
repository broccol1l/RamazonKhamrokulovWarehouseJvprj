package com.myproject.warehouse.controller;

import com.myproject.warehouse.controller.command.Command;
import com.myproject.warehouse.controller.command.CommandProvider;

public class ControllerImpl implements Controller {

    private final CommandProvider provider = new CommandProvider();

    @Override
    public String executeTask(String request) {
        if (request == null || request.trim().isEmpty()) {
            return "Error: No command entered.";
        }

        request = request.trim();
        int firstSpace = request.indexOf(' ');

        String commandName;
        String argsString = "";

        if (firstSpace == -1) {
            commandName = request;
        } else {
            commandName = request.substring(0, firstSpace);
            argsString = request.substring(firstSpace + 1).trim();
        }

        Command command = provider.getCommand(commandName);

        return command.execute(argsString);
    }
}