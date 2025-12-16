package com.myproject.warehouse.controller.command;

public class WrongCommand implements Command {
    @Override
    public String execute(String argsString) {
        return "Error: Unknown or invalid command. Please try again.";
    }
}