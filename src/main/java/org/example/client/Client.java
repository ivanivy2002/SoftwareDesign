package org.example.client;

import org.example.workspace.WorkspaceManager;

import java.util.Scanner;

public class Client {
    WorkspaceManager workspaceManager = new WorkspaceManager();

    public void execute(String commandString) {
        workspaceManager.execute(commandString);
    }

    public void clientRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            this.execute(input);
            if (input.equalsIgnoreCase("!exit")) {
                System.out.println("Force Exiting...");
                break;
            }
        }
        scanner.close();
    }
}