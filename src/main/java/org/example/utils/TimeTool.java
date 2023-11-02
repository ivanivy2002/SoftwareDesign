package org.example.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class TimeTool {

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        return formatter.format(now);
    }

    public static String getTimeForFile() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(now);
    }
}
