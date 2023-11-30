package org.example.client;

import org.example.workspace.WorkspaceManager;

import java.util.Scanner;

public class Client {
    WorkspaceManager workspaceManager = new WorkspaceManager();

    public void clientRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            workspaceManager.execute(input);
            if (input.equalsIgnoreCase("!exit")) {
                System.out.println("Force Exiting...");
                break;
            }
        }
        scanner.close();
    }
}