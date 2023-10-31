package org.example.command.history;

public class CommandHistory {
    String timestamp;
    String commandString;

    CommandHistory(String timestamp, String commandString) {
        this.timestamp = timestamp;
        this.commandString = commandString;
    }
}
