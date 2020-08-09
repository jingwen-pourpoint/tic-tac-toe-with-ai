package com.model;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(boolean x) {
        super(x);
    }

    @Override
    public void makeMove(char[][] board) {
        System.out.print("Enter the coordinates: ");
        userInput(board, new Scanner(System.in));
    }

    private void userInput(char[][] board, Scanner scanner) {
        String coordinates = scanner.nextLine();
        char userX = coordinates.charAt(0);
        char userY = coordinates.charAt(2);

        if (!isMoveValid(board, userX, userY)) {
            userInput(board, scanner);
        }
    }

    private boolean isMoveValid(char[][] board, char userX, char userY) {
        if (Character.isDigit(userX) && Character.isDigit(userY)) {
            return checkCoordinates(board, userX, userY);
        }
        System.out.println("You should enter numbers!");
        return false;
    }

    private boolean checkCoordinates(char[][] board, char userX, char userY) {
        int nbX = Character.getNumericValue(userX);
        int nbY = Character.getNumericValue(userY);
        if (isCoordinateValid(nbX) && isCoordinateValid(nbY)) {
            return isFree(board, nbX, nbY);
        }
        System.out.println("Coordinates should be from 1 to 3!");
        return false;
    }

    private boolean isCoordinateValid(int coordinate) {
        return coordinate > 0 && coordinate < 4;
    }

    private boolean isFree(char[][] board, int nbX, int nbY) {
        int x = Math.abs(nbY - 3);
        int y = nbX - 1;
        if (board[x][y] == ' ' || board[x][y] == '_') {
            writeMove(board, x, y);
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }
}
