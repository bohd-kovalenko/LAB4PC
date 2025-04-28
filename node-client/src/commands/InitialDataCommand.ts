import { CommandType } from '../CommandType';
import { Command } from './Command';
import { ComputationalData } from '../data/ComputationalData';

export class InitialDataCommand implements Command {
    public readonly commandType: CommandType = CommandType.INITIAL_DATA;

    constructor(public computationalData: ComputationalData) {}
}
