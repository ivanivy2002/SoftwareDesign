package org.example.command.history;

import java.io.Serializable;

public class CommandLog implements Serializable {
    String timestamp;
    String commandString;
    CommandLog(String timestamp, String commandString) {
        this.timestamp = timestamp;
        this.commandString = commandString;
    }
}
