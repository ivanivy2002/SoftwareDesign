package org.example.command.loadSave;
import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class SaveCommand extends EditorCommand {
    public SaveCommand(Editor editor) {
        super(editor);
    }

    @Override
    public void execute(String[] newArgs) {
        ConsoleTool.println("Saving file...");
        // 执行保存文件的操作
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(editor.getFileString()))) {
                for (String line : editor.getLines()) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                // 或者抛出自定义异常，根据实际情况处理
            }
        // 保存文件的逻辑...
    }
}