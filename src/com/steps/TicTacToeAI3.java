package com.steps;

import com.model.EasyComputer;
import com.model.HumanPlayer;
import com.model.Player;

import java.util.Scanner;

public class TicTacToeAI3 {

    private char[][] board;
    private Player firstPlayer;
    private Player secondPlayer;

    public static void main(String[] args) {
        new TicTacToeAI3().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String[] command;
        do {
            command = getCommand(scanner);
            if (isStartValid(command)) {
                playGame(command);
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

    private void playGame(String[] command) {
        int numberMoves = 0;
        boolean isFirstPlayer = true;
        boolean hasWinner = false;

        firstPlayer = initalizePlayer(command[1], true);
        secondPlayer = initalizePlayer(command[2], false);

        buildBoard();
        displayBoard();

        while (numberMoves != 9 && !hasWinner) {
            getCurrentPlayer(isFirstPlayer).makeMove(board);
            displayBoard();
            numberMoves++;
            hasWinner = hasWinner();
            getCurrentPlayer(isFirstPlayer).setWinner(hasWinner);
            isFirstPlayer = !isFirstPlayer;
        }
        displayEndGame();
    }

    private void displayEndGame() {
        if (firstPlayer.isWinner()) {
            System.out.println("X wins");
        } else if (secondPlayer.isWinner()) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
        System.out.println();
    }

    private Player initalizePlayer(String parameter, boolean isX) {
        if ("user".equals(parameter)) {
            return new HumanPlayer(isX);
        }
        return new EasyComputer(isX);
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
        return "user".equals(playerValue) || "easy".equals(playerValue);
    }

    private void buildBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void displayBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    private boolean hasWinner() {
        return checkWin(board[0][0], board[1][0], board[2][0])
                || checkWin(board[0][1], board[1][1], board[2][1])
                || checkWin(board[0][2], board[1][2], board[2][2])
                || checkWin(board[0][0], board[0][1], board[0][2])
                || checkWin(board[1][0], board[1][1], board[1][2])
                || checkWin(board[2][0], board[2][1], board[2][2])
                || checkWin(board[0][0], board[1][1], board[2][2])
                || checkWin(board[0][2], board[1][1], board[2][0]);
    }

    private boolean checkWin(char a, char b, char c) {
        return a == b && b == c && b != ' ';
    }

    private Player getCurrentPlayer(boolean isFirstPlayer) {
        return isFirstPlayer ? firstPlayer : secondPlayer;
    }
}
