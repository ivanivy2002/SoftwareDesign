package org.example.utils;


public class ConsoleTool {

    public static void println(String str) {
        System.out.println("["+str+"]");
    }

    public static void printERR(String loc, String str) {
        System.out.println("[ERR:[" + loc + "] " + str + "]");
    }

    public static void printDebug(String str) {
        System.out.println("[Debug: " + str + "]");
    }

    public static void printHelp(String command, String description) {
        System.out.println("\t" + command + "\t- " + description);
    }

    public static void prints(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.println("[" + (i + 1) + "] " + lines[i]);
        }
    }
}
