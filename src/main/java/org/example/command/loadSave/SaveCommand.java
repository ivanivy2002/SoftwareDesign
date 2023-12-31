package org.example.command.loadSave;
import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileString))) {
                for (String line : editor.getLines()) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                // 或者抛出自定义异常，根据实际情况处理
            }
        // 保存文件的逻辑...
        return 0;
    }
}