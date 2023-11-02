package org.example.command.history;

public class CommandLog {
    String timestamp;
    String commandString;

    CommandLog(String timestamp, String commandString) {
        this.timestamp = timestamp;
        this.commandString = commandString;
    }
}
