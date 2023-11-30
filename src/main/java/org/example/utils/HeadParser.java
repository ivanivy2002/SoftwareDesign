package org.example.utils;

import org.example.utils.StringTool;

public class HeadParser {

    public static String parseCommandHead(String commandString) {
        String[] args = commandString.split("\\s+");
        String commandHead = args[0];
        commandHead = switch (commandHead) {
            case "ls" -> "list";
            case "h" -> "history";
            case "del" -> "delete";
            case "re" -> "redo";
            case "un" -> "undo";
            case "q" -> "exit";
            default -> commandHead;
        };
        commandString = commandHead + " " + StringTool.catString(args, 1);
        return commandString;
    }
}
