package com.steps;

import com.model.*;

import java.util.Scanner;

public class TicTacToeAI5 {

    public static void main(String[] args) {
        new TicTacToeAI5().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String[] command;
        do {
            command = getCommand(scanner);
            if (isStartValid(command)) {
                Game tictactoe = new Game(command);
                tictactoe.play();
            }
        } while (!isExitValid(command[0]));
    }

    private String[] getCommand(Scanner scanner) {
        String[] command;
        boolean commandValid;
        do {
            System.out.print("Input command: ");
            command = scanner.nextLine().split(" ");
            commandValid = isCommandValid(command);
        } while (!commandValid);
        return command;
    }

    private boolean isCommandValid(String[] parameters) {
        if (isExitValid(parameters[0]) || isStartValid(parameters)) {
            return true;
        }
        System.out.println("Bad parameters!");
        return false;
    }

    private boolean isExitValid(String parameter) {
        return "exit".equals(parameter);
    }

    private boolean isStartValid(String[] parameters) {
        return parameters.length == 3 && "start".equals(parameters[0])
                && isValidPlayerValue(parameters[1]) && isValidPlayerValue(parameters[2]);
    }

    private boolean isValidPlayerValue(String playerValue) {
        return "user".equals(playerValue) || "easy".equals(playerValue)
                || "medium".equals(playerValue) || "hard".equals(playerValue);
    }
}

