package command

import Command

class GetResultCommand(): Command {
  
    override fun commandType(): CommandType {
        return CommandType.GET_RESULT   
    }
}