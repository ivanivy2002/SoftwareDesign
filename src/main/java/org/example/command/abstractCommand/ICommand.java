package org.example.command.abstractCommand;

import java.io.Serializable;

public interface ICommand extends Serializable {
    int execute(String[] args);
}
