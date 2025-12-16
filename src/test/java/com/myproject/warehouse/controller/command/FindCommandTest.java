package com.myproject.warehouse.controller.command;

import com.myproject.warehouse.AppFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FindCommandTest {

    @BeforeAll
    static void setUp() {
        AppFactory.getInstance();
    }


    @Test
    void testPriceIsAbc() {
        FindCommand findCommand = new FindCommand();
        String argsString = "price=abc";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.startsWith("Error: Invalid command arguments."));
    }

    @Test
    void testWrongKey() {
        FindCommand findCommand = new FindCommand();
        String argsString = "color=red";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.contains("Unknown search key: color"));
    }

    @Test
    void testWrongFormat() {
        FindCommand findCommand = new FindCommand();
        String argsString = "Vogue";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.contains("Invalid argument format. Expected key=value."));
    }

    @Test
    void testWrongCommand() {
        Command wrongCommand = new WrongCommand();
        String response = wrongCommand.execute("findd");
        Assertions.assertEquals("Error: Unknown or invalid command. Please try again.", response);
    }


    @Test
    void testSuccessfulFindAll() {
        FindCommand findCommand = new FindCommand();
        String argsString = "";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.startsWith("Found 4 products:"));
    }

    @Test
    void testSuccessfulFindByNameWithQuotes() {
        FindCommand findCommand = new FindCommand();
        String argsString = "name=\"War and Peace\"";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.startsWith("Found 1 products:"));
        Assertions.assertTrue(response.contains("War and Peace"));
    }

    @Test
    void testSuccessfulFindWithDoubleRange() {
        FindCommand findCommand = new FindCommand();
        String argsString = "price=10.0;35.0";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.startsWith("Found 3 products:"));
        Assertions.assertTrue(response.contains("1984"));
        Assertions.assertTrue(response.contains("Vogue"));
    }

    @Test
    void testSuccessfulFindWithIntegerRange() {
        FindCommand findCommand = new FindCommand();
        String argsString = "pagecount=1000;1500";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.startsWith("Found 1 products:"));
        Assertions.assertTrue(response.contains("War and Peace"));
    }

    @Test
    void testSuccessfulFindWithMultipleCriteriaAndSort() {
        FindCommand findCommand = new FindCommand();
        String argsString = "author=Orwell price=30.0 sort=quantity";
        String response = findCommand.execute(argsString);
        Assertions.assertTrue(response.startsWith("Found 1 products:"));
        Assertions.assertTrue(response.contains("1984"));
    }
}