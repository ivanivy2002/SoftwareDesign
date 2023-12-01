package org.example.command.loadSave;
import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.FileAccessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends VisitEditorCommand {
    public SaveCommand(Editor editor) {
        super(editor);
    }

    @Override
    public int execute(String[] newArgs) {
        String fileString = editor.getSubdir() + editor.getFileString();
        ConsoleTool.println("Saving " + fileString);
        // 执行保存文件的操作
        FileAccessor.writeFile(fileString, editor.getLines());
        // 保存文件的逻辑...
        return 0;
    }
}