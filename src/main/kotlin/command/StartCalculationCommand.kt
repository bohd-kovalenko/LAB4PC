package command

import Command

class StartCalculationCommand() : Command {
    
    override fun commandType(): CommandType {
        return CommandType.START_CALCULATION
    }
}
