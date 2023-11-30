package org.example.command.trivial;

import org.example.command.debug.CheckCommand;
import org.example.command.exit.ExitCommand;
import org.example.command.history.*;
import org.example.command.loadSave.LoadCommand;
import org.example.command.loadSave.ResetCommand;
import org.example.command.loadSave.SaveCommand;
import org.example.command.observe.DirTreeCommand;
import org.example.command.observe.ListCommand;
import org.example.command.observe.ListTreeCommand;
import org.example.command.update.AppendHeadCommand;
import org.example.command.update.AppendTailCommand;
import org.example.command.update.DeleteCommand;
import org.example.command.update.InsertCommand;
import org.example.utils.ConsoleTool;

public class HelpCommand extends HistoryCommand {
    public HelpCommand(HistoryTable historyTable) {
        super(historyTable);

    }

    @Override
    public int execute(String[] args) {
        ConsoleTool.println("Help: command <args> - description");
        ConsoleTool.printHelp("load <path>", "Load file from path");
        ConsoleTool.printHelp("save", "Save file to path");
        ConsoleTool.printHelp("reset", "Reset file");
        ConsoleTool.printHelp("insert <line> <content>", "Insert content to line");
        ConsoleTool.printHelp("append-head <content>", "Append content to head");
        ConsoleTool.printHelp("append-tail <content>", "Append content to tail");
        ConsoleTool.printHelp("delete <line/content>", "Delete line according to line number or content");
        ConsoleTool.printHelp("undo", "Undo");
        ConsoleTool.printHelp("redo", "Redo");
        ConsoleTool.printHelp("history", "Show history");
        ConsoleTool.printHelp("status", "Show status");
        ConsoleTool.printHelp("list", "List all observers");
        ConsoleTool.printHelp("list-tree", "List all observers in tree");
        ConsoleTool.printHelp("dir-tree <dir>", "List all observers in tree");
        ConsoleTool.printHelp("c", "Check");
        ConsoleTool.printHelp("exit", "Exit");
//        ConsoleTool.printHelp("hakuro", "Hakuro");
        ConsoleTool.printHelp("help", "Help");
        return 0;
    }
}
