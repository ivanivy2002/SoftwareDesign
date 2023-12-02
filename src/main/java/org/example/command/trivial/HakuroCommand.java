package org.example.command.trivial;

import org.example.command.abstractCommand.ICommand;
import org.example.command.history.HistoryCommand;
import org.example.command.history.HistoryTable;
import org.example.utils.ConsoleTool;

import java.io.Serializable;

public class HakuroCommand extends HistoryCommand {
    public HakuroCommand(HistoryTable historyTable) {
        super(historyTable);

    }

    @Override
    public int execute(String[] args) {
        // 输出爱心形状
//                System.out.println("   ***     ***"     );
        System.out.println("\t  ****    ****");
        System.out.println("\t ******  ******");
        System.out.println("\t  ************");
        System.out.println("\t   **********  ");
        System.out.println("\t     ******  ");
        System.out.println("\t       **     ");
        return 0;
    }
}
