package command

import Command

class ErrorCommand() : Command {
    
    override fun commandType(): CommandType {
        return CommandType.ERROR
    }
}