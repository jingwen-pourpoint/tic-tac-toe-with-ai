package com.steps;

import java.util.Scanner;

public class TicTacToeAI1 {

    private char[][] board;
    private int numberX = 0;
    private int numberO = 0;
    private char winner;


    public static void main(String[] args) {
        new TicTacToeAI1().play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        buildBoard();
        displayBoard();


        userInput(scanner);
        displayBoard();
        int getWinner = hasWinner();
        analyzeBoard();


        if (getWinner == 1) {
            System.out.println(winner + " wins");
        } else if (numberX + numberO == 9) {
            System.out.println("Draw");
        } else {
            System.out.println(numberX);
            System.out.println(numberO);
            System.out.println("Game not finished");
        }
    }

    private void buildBoard() {
        Scanner scanner = new Scanner(System.in);
        String moves = scanner.nextLine();
        System.out.println("Enter cells: " + moves);
        board = new char[3][3];
        board[0][0] = moves.charAt(0);
        board[0][1] = moves.charAt(1);
        board[0][2] = moves.charAt(2);
        board[1][0] = moves.charAt(3);
        board[1][1] = moves.charAt(4);
        board[1][2] = moves.charAt(5);
        board[2][0] = moves.charAt(6);
        board[2][1] = moves.charAt(7);
        board[2][2] = moves.charAt(8);
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
        analyzeBoard();
        if (numberX <= numberO) {
            board[x][y] = 'X';
        } else {
            board[x][y] = 'O';
        }
    }

    private int hasWinner() {
        return checkWin(board[0][0], board[1][0], board[2][0])
                + checkWin(board[0][1], board[1][1], board[2][1])
                + checkWin(board[0][2], board[1][2], board[2][2])
                + checkWin(board[0][0], board[0][1], board[0][2])
                + checkWin(board[1][0], board[1][1], board[1][2])
                + checkWin(board[2][0], board[2][1], board[2][2])
                + checkWin(board[0][0], board[1][1], board[2][2])
                + checkWin(board[0][2], board[1][1], board[2][0]);
    }

    private int checkWin(char a, char b, char c) {
        if (a == b && b == c && b != ' ' && b != '_') {
            winner = a;
            return 1;
        }
        return 0;
    }


    private void analyzeBoard() {
        numberX = 0;
        numberO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    numberX++;
                } else if (board[i][j] == 'O') {
                    numberO++;
                }
            }
        }
    }

}

