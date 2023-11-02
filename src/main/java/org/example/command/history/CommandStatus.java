package org.example.command.history;

public class CommandStatus {
    String timestamp;
    String commandString;
    CommandStatus(String timestamp, String commandString) {
        this.timestamp = timestamp;
        this.commandString = commandString;
    }
}
