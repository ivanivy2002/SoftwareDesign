package org.example.command.loadSave;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResetCommand extends VisitEditorCommand {

    public ResetCommand(Editor editor) {
        super(editor);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Resetting " + newArgs[0]);
        editor.setFileString(editor.getSubdir() + newArgs[0]);
        // 执行加载文件的操作
//        editor.setLines(FileReader.readFile(editor.getFileString()));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(editor.getFileString()))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
            // 或者抛出自定义异常，根据实际情况处理
        }
//        editor.printLines();
        // 加载文件的逻辑...
        return 0;
    }
}


