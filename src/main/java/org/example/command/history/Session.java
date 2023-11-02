package org.example.command.history;

import org.example.Editor;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;
import org.example.utils.TimeTool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session {
    protected final Editor editor;
    protected List<CommandLog> logs = new ArrayList<>();
    protected List<OutputLog> outputLogs = new ArrayList<>();
    //    protected OutputLog o = new OutputLog();
//    protected List<String> sessionHeader = new ArrayList<>();
//    protected List<Integer> sessionHeaderNum = new ArrayList<>();
//    protected List<String> sessionEnder = new ArrayList<>();
    protected LocalDateTime timestampStart;
    protected LocalDateTime timestampEnd;
    //    protected List<LocalDateTime> duration = new ArrayList<>();
//    protected List<String> fileName = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
    protected boolean saved = true;

    public Session(Editor editor) {
        this.editor = editor;
    }

    public void push(String commandString) {
        if (StringTool.getCommandName(commandString).equals("load")) {
            if (!saved) {
                OutputLog o = outputLogs.get(outputLogs.size() - 1);
                timestampEnd = LocalDateTime.now();
//              outputLogs.add("session start at "+ formatter.format(timestampEnd));
                o.duration = timestampEnd.minusSeconds(timestampStart.getSecond());
                o.sessionEnder = editor.getSubdir() + o.fileName + "\t" + o.duration.getSecond() + " seconds";
                o.outputLog.add(o.sessionEnder);
                saved = true;
            }
            OutputLog o = new OutputLog();
            timestampStart = LocalDateTime.now();
            o.sessionHeader = "session start at " + formatter.format(timestampStart);
            o.outputLog.add(o.sessionHeader);
            o.fileName = editor.getFileString();
            outputLogs.add(o);
            saved = false;
        }
        logs.add(new CommandLog(TimeTool.getCurrentTime(), commandString));
        OutputLog o = outputLogs.get(outputLogs.size() - 1);
//        o.sessionHeaderNum = logs.size()-1;
        o.outputLog.add(logs.get(logs.size() - 1).timestamp + " " + logs.get(logs.size() - 1).commandString);
        timestampEnd = LocalDateTime.now();
        o.duration = timestampEnd.minusSeconds(timestampStart.getSecond());
        o.sessionEnder = editor.getSubdir() + o.fileName + "\t" + o.duration.getSecond() + " seconds";
        if (StringTool.getCommandName(commandString).equals("save")) {
            o.outputLog.add(o.sessionEnder);
            saved = true;
        }
    }


    public List<CommandLog> getLogs() {
        return logs;
    }

    public void printStatus() {
        ConsoleTool.println("Current: ");
        OutputLog o = outputLogs.get(outputLogs.size() - 1);
        System.out.println(o.sessionHeader);
        listLogToSessionHead();
        System.out.println(o.sessionEnder);
    }

    public void listLog() {
        for (CommandLog commandHistory : logs) {
            System.out.println(commandHistory.timestamp + " " + commandHistory.commandString);
        }
    }

    public void listLogToSessionHead() {
        OutputLog o = outputLogs.get(outputLogs.size() - 1);
        for (int i = 0; i < o.outputLog.size(); i++) {
            System.out.println(o.outputLog.get(i));
        }
//            System.out.println(o.sessionEnder);

//        for (int i = o.sessionHeaderNum; i < logs.size(); i++) {
//            System.out.println(logs.get(i).timestamp + " " + logs.get(i).commandString);
//        }
    }

    public void printAll() {
        for (OutputLog o : outputLogs) {
            for (int i = 0; i < o.outputLog.size(); i++) {
                System.out.println(o.outputLog.get(i));
            }
        }
    }

    public void saveLog() {
        for (OutputLog o : outputLogs) {
            String fileName = "./log/" + parseFileName(o.fileName) + "_" + TimeTool.getTimeForFile() + ".txt";
            try (FileWriter fileWriter = new FileWriter(fileName); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (String line : o.outputLog) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();  // 插入换行符
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String parseFileName(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }
}
