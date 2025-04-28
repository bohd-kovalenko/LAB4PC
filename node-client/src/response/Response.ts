import { OperationStatus } from './OperationStatus';

export interface Response {
    status: OperationStatus;
    result?: number[][];
    message: string;
}
