package org.example.workspace;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Editor;
import org.example.command.CommandExecutor;
import org.example.command.CommandRegister;
import org.example.command.history.HistoryTable;

@NoArgsConstructor
@Getter
@Setter
public class Workspace {
    protected boolean active;
    protected String name;
    protected String path;
    protected CommandExecutor executor;
    protected CommandRegister register;
    protected Editor editor;
    protected HistoryTable historyTable;
    Workspace(String name) {
        path = "./data/";
        this.name = name;
        editor = new Editor(path);
        historyTable = new HistoryTable(editor);
        executor = new CommandExecutor();
        register = new CommandRegister();
        //注册
        register.commandReg(executor, editor, historyTable);
        active = false;
    }

    protected boolean unsaved() {
        return historyTable.checkUnsaved();
    }
}
