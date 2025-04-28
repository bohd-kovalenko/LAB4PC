import * as net from 'net';
import * as readline from 'readline-sync';
import { CommandType } from './CommandType';
import { Command } from './commands/Command';
import { InitialDataCommand } from './commands/InitialDataCommand';
import { StartCalculationCommand } from './commands/StartCalculationCommand';
import { GetResultCommand } from './commands/GetResultCommand';
import { ComputationalData } from './data/ComputationalData';
import { Response } from './response/Response';

async function main() {
    const port = readline.questionInt('Please enter the port to which you want to connect: ');

    const client = new net.Socket();

    try {
        await new Promise<void>((resolve, reject) => {
            client.connect(port, 'localhost', () => {
                console.log(`Connected to server on port ${port}`);
                resolve();
            });
            client.on('error', (err) => {
                reject(err);
            });
        });

        while (true) {
            const commandType = readline.questionInt(
                'Please enter the type of command, you want to send (1 - initial data, 2 - start calculation, 3 - get result, 4 - exit): '
            );

            if (commandType === 4) {
                break;
            }

            const command = convertCommand(commandType);

            const commandStr = JSON.stringify(command);
            const sentBytes = Buffer.byteLength(commandStr);
            console.log(`Sent: ${sentBytes} bytes`);
            client.write(commandStr);

            const response = await readResponse(client);

            console.log(response);
        }
    } catch (error) {
        console.error('Error:', error);
    } finally {
        client.end();
    }
}

function convertCommand(commandType: number): Command {
    switch (commandType) {
        case 1:
            return new InitialDataCommand(new ComputationalData(3, 100, 1));
        case 2:
            return new StartCalculationCommand();
        default:
            return new GetResultCommand();
    }
}

function readResponse(client: net.Socket): Promise<Response> {
    return new Promise<Response>((resolve) => {
        let data = '';
        let totalBytesReceived = 0;

        const onData = (chunk: Buffer) => {
            totalBytesReceived += chunk.length;
            data += chunk.toString();
            try {
                const response = JSON.parse(data) as Response;

                client.removeListener('data', onData);
                console.log(`Received: ${totalBytesReceived} bytes`);

                resolve(response);
            } catch (e) {
            }
        };

        client.on('data', onData);
    });
}

main().catch(console.error);
