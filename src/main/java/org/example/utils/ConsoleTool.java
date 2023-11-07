package org.example.utils;

public class ConsoleTool {
    public static void println(String str) {
        System.out.println("["+str+"]");
    }

    public static void prints(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.println("[" + (i + 1) + "] " + lines[i]);
        }
    }
}
