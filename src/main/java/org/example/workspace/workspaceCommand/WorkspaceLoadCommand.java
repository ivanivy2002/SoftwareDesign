package org.example.workspace.workspaceCommand;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.FileAccessor;

public class WorkspaceLoadCommand extends VisitEditorCommand {

    public WorkspaceLoadCommand(Editor editor) {
        super(editor);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Loading " + newArgs[0]);
        editor.setFileString(newArgs[0]);
        // 执行加载文件的操作
        editor.setLines(FileAccessor.readFile(editor.getSubdir() + editor.getFileString()));
//        editor.printLines();
        // 加载文件的逻辑...
        return 0;
    }
}


