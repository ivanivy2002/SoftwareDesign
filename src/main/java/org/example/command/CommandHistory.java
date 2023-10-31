package org.example.command;

public class CommandHistory {
    private final String[] history = new String[100];
    private int index = 0;

    public void push(String command) {
        history[index] = command;
        index++;
    }

    public String pop() {
        index--;
        return history[index];
    }

    public boolean isEmpty() {
        return index == 0;
    }
}
