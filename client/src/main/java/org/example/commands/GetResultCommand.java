package org.example.commands;

import org.example.CommandType;

public class GetResultCommand implements Command {
    
    @Override
    public CommandType getCommandType() {
        return CommandType.GET_RESULT;
    }
}