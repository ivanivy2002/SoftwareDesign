package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.observer.TreeViewBuilder;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

import java.util.ArrayList;
import java.util.List;

public class DirTreeCommand extends VisitEditorCommand {

    public DirTreeCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] args) {
        ConsoleTool.println("DirTree...");
        TreeViewBuilder.buildTree(editor.getLines());
        String[] lines = editor.getLines();
        List<Integer> matchingLineNums = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String trueLine = lines[i].split("\\s+", 2)[1];
//            ConsoleTool.println("trueLine: "+trueLine);
//            ConsoleTool.println("args: "+StringTool.catString(args,0));
            if (trueLine.equals(StringTool.catString(args, 0))) {
                matchingLineNums.add(i);
                ConsoleTool.println("added " + i);
            }
        }
        if (matchingLineNums.isEmpty()) {
            ConsoleTool.println("ERR: No matching line");
            return -1;
        }
//        lines=StringTool.deleteLines(lines,matchingLineNums);
        for (Integer matchingLineNum : matchingLineNums) {
            TreeViewBuilder.showDir(matchingLineNum);
        }
        return 0;
    }
}


