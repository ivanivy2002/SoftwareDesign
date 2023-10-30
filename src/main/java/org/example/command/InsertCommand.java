package org.example.command;

import org.example.Editor;
import org.example.utils.FileReader;

public class InsertCommand extends EditorCommand{
    public InsertCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        System.out.println("Inserting...");
        int lineNum = Integer.parseInt(newArgs[0]);
        //TODO: lineContend shd be including all rest args
        String lineContent = newArgs[1];
        String[] lines = editor.getLines();
        if(lineNum > lines.length){
//            System.out.println("lineNum is too large");
            lineNum =lines.length;
            return;
        }
        if(lineNum < 0){
//            System.out.println("lineNum is too small");
            lineNum = 0;
            return;
        }
        String[] newLines = new String[lines.length + 1];
        // 复制原数组中的前lineNum行到新数组
        System.arraycopy(lines, 0, newLines, 0, lineNum);
        // 在新数组的lineNum位置插入待插入的字符串
        newLines[lineNum] = lineContent;
        // 复制原数组中剩余的行到新数组的对应位置
        System.arraycopy(lines, lineNum, newLines, lineNum + 1, lines.length - lineNum);
        // 将新数组赋值给lines数组
        lines = newLines;
        editor.setLines(lines);
//        editor.insert(lineNum, lineContent);
    }
}
