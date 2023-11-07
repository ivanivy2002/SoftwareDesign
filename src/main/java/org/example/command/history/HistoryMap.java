package org.example.command.history;

public class HistoryMap {
    protected String rawCommand;
    protected String[] lines;

    HistoryMap(String rawCommand, String[] lines) {
        this.rawCommand = rawCommand;
        this.lines = lines;
    }

    public String[] getLines() {
        return lines;
    }

    public String getRawCommand() {
        return rawCommand;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public void setRawCommand(String rawCommand) {
        this.rawCommand = rawCommand;
    }
}
