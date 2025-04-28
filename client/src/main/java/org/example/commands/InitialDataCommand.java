package org.example.commands;

import org.example.CommandType;
import org.example.data.ComputationalData;

public class InitialDataCommand implements Command {
    private ComputationalData computationalData;

    public InitialDataCommand(ComputationalData computationalData) {
        this.computationalData = computationalData;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.INITIAL_DATA;
    }

    public ComputationalData getComputationalData() {
        return computationalData;
    }

    public void setComputationalData(ComputationalData computationalData) {
        this.computationalData = computationalData;
    }
}