package org.example.command.history;

import lombok.Getter;

@Getter
public class HistoryMap implements java.io.Serializable {
    protected String rawCommand;
    protected String[] lines;

    HistoryMap(String rawCommand, String[] lines) {
        this.rawCommand = rawCommand;
        this.lines = lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public void setRawCommand(String rawCommand) {
        this.rawCommand = rawCommand;
    }
}
