package com.myproject.warehouse.controller.command;

public interface Command {
    String execute(String argsString);
}