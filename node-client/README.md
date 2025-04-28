# Node.js Matrix Calculation Client

This is a Node.js client for the matrix calculation server, implemented in TypeScript. It is functionally identical to the Java client in the `client` folder.

## Prerequisites

- Node.js (v14 or later)
- npm (v6 or later)

## Installation

1. Navigate to the `node-client` directory:
   ```
   cd node-client
   ```

2. Install dependencies:
   ```
   npm install
   ```

## Usage

1. Start the client:
   ```
   npm start
   ```

2. Enter the port number to connect to the server.

3. Choose a command to send to the server:
   - 1: Send initial data (matrix size: 10000, max cell number: 100, thread count: 1)
   - 2: Start calculation
   - 3: Get result
   - 4: Exit

## Building

To compile the TypeScript code to JavaScript:
```
npm run build
```

The compiled JavaScript files will be in the `dist` directory.

## Project Structure

- `src/CommandType.ts`: Enum for command types
- `src/commands/Command.ts`: Interface for commands
- `src/commands/InitialDataCommand.ts`: Command for sending initial data
- `src/commands/StartCalculationCommand.ts`: Command for starting calculation
- `src/commands/GetResultCommand.ts`: Command for getting results
- `src/data/ComputationalData.ts`: Data class for computational parameters
- `src/response/OperationStatus.ts`: Enum for operation status
- `src/response/Response.ts`: Interface for server responses
- `src/main.ts`: Main client application