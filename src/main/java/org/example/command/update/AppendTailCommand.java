package org.example.command.update;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

public class AppendTailCommand extends VisitEditorCommand {
    public AppendTailCommand(Editor editor) {
        super(editor);
    }

    @Override
//    public void execute(String[] newArgs) {
//        CommandExecutor executor = new CommandExecutor();
//        StringBuilder lineContentBuilder = new StringBuilder();
//        for (int i = 0; i < newArgs.length; i++) {
//            lineContentBuilder.append(newArgs[i]).append(" "); // 添加空格分隔每个参数
//        }
//        String lineContent = lineContentBuilder.toString().trim(); // 去除末尾的空格
//        executor.executeCommand("insert "+ lineContent);
//    }
    public int execute(String[] newArgs) {
        ConsoleTool.println("Appending Tail...");
        String[] lines = editor.getLines();
//        int lineNum = lines.length;
        String lineContent =StringTool.catString(newArgs,0);
        if(lineContent.split("\\s+", 2).length != 2){
            ConsoleTool.println("ERR: lineContent without Marking Symbol");
            return -1;
        }
        lines = StringTool.insertLine(lines, lines.length, lineContent);
        editor.setLines(lines);
        return 0;
    }
}
