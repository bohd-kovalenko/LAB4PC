package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commands.Command;
import org.example.commands.GetResultCommand;
import org.example.commands.InitialDataCommand;
import org.example.commands.StartCalculationCommand;
import org.example.data.ComputationalData;
import org.example.response.Response;

import java.io.BufferedInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var jsonMapper = new ObjectMapper();
        jsonMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        var scanner = new Scanner(System.in);
        System.out.println("Please enter the port to which you want to connect:");
        var port = scanner.nextInt();
        try (var client = new Socket(InetAddress.getLocalHost(), port)) {
            var input = new BufferedInputStream(client.getInputStream(),500000);
            while (true) {
                System.out.println("Please enter the type of command, you want to send (1 - initial data, 2 - start calculation, 3 - get result, 4 - exit):");
                var commandType = scanner.nextInt();
                if (commandType == 4) break;
                var command = convertCommand(commandType);
                client.getOutputStream().write(jsonMapper.writeValueAsBytes(command));
                input.mark(2);
                input.read();
                input.reset();
                var response = jsonMapper.readValue(input, Response.class);
                System.out.println(response);
            }
        } catch (Exception ignored) {
        } finally {
            scanner.close();
        }
    }
    
    private static Command convertCommand(int commandType) {
        return switch (commandType) {
            case 1 -> new InitialDataCommand(new ComputationalData(10000, 100, 1));
            case 2 -> new StartCalculationCommand();
            default -> new GetResultCommand();
        };
    }
}