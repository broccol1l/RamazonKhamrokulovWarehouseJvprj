package com.myproject.warehouse.controller.command;

public class ExitCommand implements Command {
    @Override
    public String execute(String argsString) {
        return "Goodbye!";
    }
}