package org.example.command.loadSave;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.FileReader;

public class LoadCommand extends EditorCommand {

    public LoadCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        ConsoleTool.println("Loading file...");
        editor.setFileString(editor.getSubdir() + newArgs[0]);
        // 执行加载文件的操作
        editor.setLines(FileReader.readFile(editor.getFileString()));
//        editor.printLines();
        // 加载文件的逻辑...
    }
}


