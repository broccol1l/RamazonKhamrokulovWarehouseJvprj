package com.myproject.warehouse.view;

import com.myproject.warehouse.controller.Controller;

import com.myproject.warehouse.AppFactory;

import java.util.Scanner;

public class ConsoleViewImpl implements View {

    @Override
    public void start() {
        Controller controller = AppFactory.getInstance().getController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Warehouse Search System (Printed Products) v1.0 ===");
        System.out.println("Creation Date: 2025-11-06");

        System.out.println("Developer: Ramazon Khamrokulov");

        System.out.println("--- Available Commands (Menu) ---");
        System.out.println("  find [criteria...]");
        System.out.println("     (e.g., find author=Orwell price=30.0)");
        System.out.println("     (e.g., find publisher=\"Conde Nast\")");
        System.out.println("     (e.g., find sort=price)");
        System.out.println("  exit");
        System.out.println("-----------------------------------------------------");

        while (true) {
            System.out.print("> ");
            String requestLine = scanner.nextLine();

            if ("exit".equalsIgnoreCase(requestLine)) {
                System.out.println("Goodbye!");
                break;
            }

            String response = controller.executeTask(requestLine);
            System.out.println(response);
        }

        scanner.close();
    }

    @Override
    public void crush() {
        System.out.println("Sorry, something went wrong... The application has to close.");
    }
}