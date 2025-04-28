package command

import Command
import data.ComputationalData

data class InitialDataCommand(
    val computationalData: ComputationalData
) : Command {

    override fun commandType(): CommandType {
        return CommandType.INITIAL_DATA
    }
}