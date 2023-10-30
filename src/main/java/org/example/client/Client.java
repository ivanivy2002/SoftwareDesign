package org.example.client;

import org.example.command.CommandExecutor;

import java.util.Scanner;

public class Client {
    public void clientRun(CommandExecutor executor) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            executor.executeCommand(input);
        }
        scanner.close();
    }
}