package org.example.utils;

import java.util.ArrayList;
import java.util.List;

public class StringTool {

//    public boolean isNumeric(String str) {
//        return str.matches("-?\\d+(\\.\\d+)?");
//    }
//    public boolean isInteger(String str) {
//        return str.matches("-?\\d+");
//    }
public static boolean isNotNaturalNumber(String str) {
        return !str.matches("\\d+");
    }

    public static String catString(String[] strings, int st) {
        StringBuilder sb = new StringBuilder();
        for (int i = st; i < strings.length; i++) {
            sb.append(strings[i]).append(" "); // 添加空格分隔每个参数
        }
        return sb.toString().trim();
    }

    public static String replaceCharAt(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    public static String replaceStringAt(String s, int pos, String c) {
        return s.substring(0, pos) + c + s.substring(pos + c.length());
    }

    public static String[] insertLine(String[] lines,int lineNum, String lineContent){
        String[] newLines = new String[lines.length + 1];
        // 复制原数组中的前lineNum行到新数组
        // 如果lineNum超过了原数组的长度，直接将原数组全部复制到新数组
        System.arraycopy(lines, 0, newLines, 0, lineNum);
        // 在新数组的lineNum位置插入待插入的字符串
        newLines[lineNum] = lineContent;
//        ConsoleTool.println("newLines[lineNum]: "+newLines[lineNum]);
        // 复制原数组中剩余的行到新数组的对应位置
        System.arraycopy(lines, lineNum, newLines, lineNum + 1, lines.length - lineNum);
        // 将新数组赋值给lines数组
        lines = newLines;
        return lines;
    }

    public static String[] deleteLine(String[] lines, int lineNum){
        String[] newLines = new String[lines.length - 1];
        // 复制原数组中的前lineNum行到新数组
        System.arraycopy(lines, 0, newLines, 0, lineNum);
        // 复制原数组中剩余的行到新数组的对应位置
        System.arraycopy(lines, lineNum + 1, newLines, lineNum, lines.length - lineNum - 1);
        // 将新数组赋值给lines数组
        lines = newLines;
        return lines;
    }

    public static String[] deleteLines( String[] lines, List<Integer> lineNums) {
        List<Integer> deletedLineNums = new ArrayList<>();
        for (int i = lineNums.size() - 1; i >= 0; i--) {
            lines = StringTool.deleteLine(lines, lineNums.get(i));
            deletedLineNums.add(lineNums.get(i) - 1); // 更新行号，减去已删除的行数
        }
        ConsoleTool.println("Deleted lines: " + lineNums);
        // 更新删除行号列表
        lineNums.clear();
        lineNums.addAll(deletedLineNums);
        return lines;
    }

    public static String getCommandName(String commandString) {
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        return args[0];
    }

    public static int countHashes(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                count++;
            }
        }
        return count;
    }

    public static String repeatString(String str, int count) {
        return String.valueOf(str).repeat(Math.max(0, count));
    }

    public static String convertLineNumber(int totalLines, int lineNumber) {
//        int leadingZeros = (int) (Math.log10(totalLines) + 1) - (int) (Math.log10(lineNumber) + 1);
//        return "0".repeat(Math.max(0, leadingZeros)) + lineNumber;
        int leadingZeros = (int) (Math.log10(totalLines) + 1);
        String format = "%0" + leadingZeros + "d";
        return String.format(format, lineNumber);
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
}
