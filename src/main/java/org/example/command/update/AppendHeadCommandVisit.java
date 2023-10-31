package org.example.command.update;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

public class AppendHeadCommandVisit extends VisitEditorCommand {


    public AppendHeadCommandVisit(Editor editor) {
        super(editor);
    }

    @Override
//    public void execute(String[] newArgs) {
//        CommandExecutor executor = new CommandExecutor();
//        StringBuilder lineContentBuilder = new StringBuilder();
//        for (String newArg : newArgs) {
//            lineContentBuilder.append(newArg).append(" "); // 添加空格分隔每个参数
//        }
//        String lineContent = lineContentBuilder.toString().trim(); // 去除末尾的空格
//        executor.executeCommand("insert 0 "+ lineContent);
//    }
    public int execute(String[] newArgs) {
        ConsoleTool.println("Appending Head...");
        int lineNum = 0;
        String[] lines = editor.getLines();
        String lineContent =StringTool.catString(newArgs,0);
        if(lineContent.split("\\s+", 2).length != 2){
            ConsoleTool.println("ERR: lineContent without Marking Symbol");
            return -1;
        }
        lines = StringTool.insertLine(lines,lineNum,lineContent);
        editor.setLines(lines);
        return 0;
    }

}
