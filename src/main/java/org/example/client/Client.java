package org.example.client;

import org.example.command.CommandExecutor;

import java.util.Scanner;

public class Client {
    public void clientRun(CommandExecutor executor) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            executor.executeCommand(input);
            if (input.equalsIgnoreCase("!exit")) {
                System.out.println("Force Exiting...");
                break;
            }
        }
        scanner.close();
    }
}