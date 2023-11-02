package org.example.command.loadSave;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.FileReader;

public class LoadCommand extends VisitEditorCommand {

    public LoadCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Loading " + newArgs[0]);
        editor.setFileString(newArgs[0]);
        // 执行加载文件的操作
        editor.setLines(FileReader.readFile(editor.subdir + editor.getFileString()));
//        editor.printLines();
        // 加载文件的逻辑...
        return 0;
    }
}


