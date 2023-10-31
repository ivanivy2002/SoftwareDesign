package org.example.command.update;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends EditorCommand {
    public DeleteCommand(Editor editor) {
        super(editor);
    }

//    public void execute(String[] newArgs) {
//        ConsoleTool.println("Deleting...");
//        int lineNum = 0;
//        String[] lines = editor.getLines();
//        StringBuilder lineContentBuilder = new StringBuilder();
//        //TIPS: lineContent shd be including all rest args from Args[1] to the end
//        if(JudgeTool.isNaturalNumber(newArgs[0])){
//            //优先按照行号删除
////            ConsoleTool.println("Default insert to the end");
////            lineNum = lines.length+1;
////            for (String newArg : newArgs) {
////                lineContentBuilder.append(newArg).append(" "); // 添加空格分隔每个参数
////            }
//        }
//        else{
//            lineNum = Integer.parseInt(newArgs[0]);
//            for (int i = 1; i < newArgs.length; i++) {
//                lineContentBuilder.append(newArgs[i]).append(" "); // 添加空格分隔每个参数
//            }
//        }
//        String lineContent = lineContentBuilder.toString().trim(); // 去除末尾的空格
////        ConsoleTool.println("lineContent: "+lineContent);
////        ConsoleTool.println("lineNum: "+lineNum);
////        ConsoleTool.println("lines.length: "+lines.length);
//        lineNum--;
//        lineNum =Math.max(0,Math.min(lineNum,lines.length));
////        ConsoleTool.println("lineNum: "+lineNum);
//        String[] newLines = new String[lines.length + 1];
//        // 复制原数组中的前lineNum行到新数组
//        // 如果lineNum超过了原数组的长度，直接将原数组全部复制到新数组
//        System.arraycopy(lines, 0, newLines, 0, lineNum);
//        // 在新数组的lineNum位置插入待插入的字符串
//        newLines[lineNum] = lineContent;
////        ConsoleTool.println("newLines[lineNum]: "+newLines[lineNum]);
//        // 复制原数组中剩余的行到新数组的对应位置
//        System.arraycopy(lines, lineNum, newLines, lineNum + 1, lines.length - lineNum);
//        // 将新数组赋值给lines数组
//        lines = newLines;
//        editor.setLines(lines);
////        editor.insert(lineNum, lineContent);
//    }

    public void execute(String[] args) {
        String[] lines = editor.getLines();
        if (StringTool.isNaturalNumber(args[0])) {
            //找文字
            ConsoleTool.println("Pairing Texts");
            List<Integer> matchingLineNums = new ArrayList<>();
            for (int i = 0; i < lines.length; i++) {
                String trueLine = lines[i].split("\\s+", 2)[1];
                if(trueLine.equals(StringTool.catString(args,0))){
                    matchingLineNums.add(i);
                }
            }
            lines=StringTool.deleteLines(lines,matchingLineNums);
            editor.setLines(lines);
        }
        else{
            //找行号
            ConsoleTool.println("Pairing LineNums");
            int lineNum = Integer.parseInt(args[0]);
            if (lineNum < 1 || lineNum > lines.length) {
                ConsoleTool.println("EXC: Line number out of range");
                return;
            }
            lineNum--; // 将行数转换为数组索引
            lines = StringTool.deleteLine(lines,lineNum);
            ConsoleTool.println("Deleted line [" + (lineNum + 1)+ "] " + lines[lineNum]);
            editor.setLines(lines);
        }
    }
}
