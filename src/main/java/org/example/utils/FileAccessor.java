package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileAccessor {
    public static String[] readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            // 如果文件不存在，则创建新文件
            if (!file.exists()) {
                file.createNewFile();
                ConsoleTool.println("Created File：" + filePath);
            }
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
    }

    public static void clearFile(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.close();
            ConsoleTool.println("File Cleared");
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("发生了一个错误：" + e.getMessage());
        }
    }

    public static void writeFile(String filePath, String[] lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 或者抛出自定义异常，根据实际情况处理
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                ConsoleTool.println("Deleted: " + filePath);
            } else {
                ConsoleTool.printERR("FileAccessor.deleteFile", "Failed to delete file");
            }
        } else {
            ConsoleTool.printERR("FileAccessor.deleteFile", "File not found");
        }
    }

}