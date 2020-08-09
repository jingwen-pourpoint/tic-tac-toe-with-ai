package com.steps;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeAI2 {

    private char[][] board;
    private int numberMoves = 0;
    private boolean firstPlayer = true;


    public static void main(String[] args) {
        new TicTacToeAI2().play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        buildBoard();
        displayBoard();

        boolean hasWinner = false;
        while (numberMoves != 9) {
            userInput(scanner);
            displayBoard();
            hasWinner = hasWinner();
            if (hasWinner) {
                break;
            }
            levelComputer();
            computerInput();
            hasWinner = hasWinner();
            if (hasWinner) {
                break;
            }
        }


        if (hasWinner) {
            if (!firstPlayer) {
                System.out.println("X wins");
            } else {
                System.out.println("O wins");
            }
        } else {
            System.out.println("Draw");
        }
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

    private void userInput(Scanner scanner) {
        System.out.println("Enter the coordinates: ");
        String coordinates = scanner.nextLine();
        char userX = coordinates.charAt(0);
        char userY = coordinates.charAt(2);

        if (!isMoveValid(userX, userY)) {
            userInput(scanner);
        }
    }

    private boolean isMoveValid(char userX, char userY) {
        if (Character.isDigit(userX) && Character.isDigit(userY)) {
            return checkCoordinates(userX, userY);
        }
        System.out.println("You should enter numbers!");
        return false;
    }

    private boolean checkCoordinates(char userX, char userY) {
        int nbX = Character.getNumericValue(userX);
        int nbY = Character.getNumericValue(userY);
        if (isCoordinateValid(nbX) && isCoordinateValid(nbY)) {
            return isFree(nbX, nbY);
        }
        System.out.println("Coordinates should be from 1 to 3!");
        return false;
    }

    private boolean isCoordinateValid(int coordinate) {
        return coordinate > 0 && coordinate < 4;
    }

    private boolean isFree(int nbX, int nbY) {
        int x = Math.abs(nbY - 3);
        int y = nbX - 1;
        if (board[x][y] == ' ' || board[x][y] == '_') {
            writeMove(x, y);
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }


    private void writeMove(int x, int y) {
        if (firstPlayer) {
            board[x][y] = 'X';
        } else {
            board[x][y] = 'O';
        }
        firstPlayer = !firstPlayer;
        numberMoves++;
    }


    private void levelComputer() {
        System.out.println("Making move level \"easy\"");
    }

    private void computerInput() {
        Random random = new Random();
        int randomX;
        int randomY;
        do {
            randomX = random.nextInt(3);
            randomY = random.nextInt(3);
        } while (board[randomX][randomY] != ' ');

        writeMove(randomX, randomY);
        displayBoard();
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


}
