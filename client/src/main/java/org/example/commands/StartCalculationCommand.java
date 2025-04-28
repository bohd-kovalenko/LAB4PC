package org.example.commands;

import org.example.CommandType;

public class StartCalculationCommand implements Command {
    
    @Override
    public CommandType getCommandType() {
        return CommandType.START_CALCULATION;
    }
}