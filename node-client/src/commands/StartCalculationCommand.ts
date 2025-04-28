import { CommandType } from '../CommandType';
import { Command } from './Command';

export class StartCalculationCommand implements Command {
    public readonly commandType: CommandType = CommandType.START_CALCULATION;
}
