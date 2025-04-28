import { CommandType } from '../CommandType';
import { Command } from './Command';

export class GetResultCommand implements Command {
    public readonly commandType: CommandType = CommandType.GET_RESULT;
}
