package org.example.command.history;

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
    //    protected final Editor editor;
    protected String subdir;
    //    protected String fileName;
    protected List<CommandLog> logs = new ArrayList<>();
    protected List<OutputLog> outputLogs = new ArrayList<>();
    protected LocalDateTime timestampStart;
    protected LocalDateTime timestampEnd;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
    protected boolean saved = true;

    public Session(String subdir) {
//        this.editor = editor;
        this.subdir = subdir;
        ConsoleTool.println("Subdir: " + subdir);
//        this.fileName = fileName;
    }

    public void push(String commandString) {
        if (StringTool.getCommandName(commandString).equals("load")) {
            if (!saved) {
                OutputLog o = outputLogs.get(outputLogs.size() - 1);
//                timestampEnd = LocalDateTime.now();
//                o.duration = TimeTool.calculateTimeDifference(timestampStart, timestampEnd);
//                o.sessionEnder = subdir + o.fileName + "\t" + o.duration;
                updateEnd(o);
                o.outputLog.add(o.sessionEnder);
                saved = true;
            }
            OutputLog o = new OutputLog();
            timestampStart = LocalDateTime.now();
            o.sessionHeader = "session start at " + formatter.format(timestampStart);
            o.outputLog.add(o.sessionHeader);
            o.fileName = parseFileNameFromCommand(commandString);
            outputLogs.add(o);
            saved = false;
        }
        logs.add(new CommandLog(TimeTool.getCurrentTime(), commandString));
        OutputLog o = outputLogs.get(outputLogs.size() - 1);
//        o.sessionHeaderNum = logs.size()-1;
        o.outputLog.add(logs.get(logs.size() - 1).timestamp + " " + logs.get(logs.size() - 1).commandString);
//        timestampEnd = LocalDateTime.now();
//        o.duration = TimeTool.calculateTimeDifference(timestampStart, timestampEnd);
//        o.sessionEnder = subdir + o.fileName + "\t" + o.duration;
        updateEnd(o);
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
        updateEnd(o);
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
            if (o == outputLogs.get(outputLogs.size() - 1)) {
//                timestampEnd = LocalDateTime.now();
//                o.duration = TimeTool.calculateTimeDifference(timestampStart, timestampEnd);
//                o.sessionEnder = subdir + o.fileName + "\t" + o.duration;
                updateEnd(o);
                o.outputLog.add(o.sessionEnder);
            }
            String fileName = "./log/" + parseFileNameFromExtension(o.fileName) + "_" + TimeTool.getTimeForFile() + ".txt";
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

    public static String parseFileNameFromExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    public static String parseFileNameFromCommand(String commandString) {
        String[] args = commandString.split("\\s+", 2); // 使用空格作为分隔符切割命令字符串
        return args[1];
    }

    public void updateEnd(OutputLog o) {
        timestampEnd = LocalDateTime.now();
        o.duration = TimeTool.calculateTimeDifference(timestampStart, timestampEnd);
        o.sessionEnder = subdir + o.fileName + "\t" + o.duration;
//        o.outputLog.add(o.sessionEnder);
    }

}
