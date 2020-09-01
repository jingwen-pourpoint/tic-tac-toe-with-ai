package com.model.player;

import com.model.Game;

import java.util.Scanner;

public class HumanPlayer extends Player {

    @Override
    public void makeMove(Game game) {
        System.out.print("Enter the coordinates: ");
        userInput(game, new Scanner(System.in));
    }

    private void userInput(Game game, Scanner scanner) {
        String coordinates = scanner.nextLine();
        char userX = coordinates.charAt(0);
        char userY = coordinates.charAt(2);

        if (!isMoveValid(game, userX, userY)) {
            userInput(game, scanner);
        }
    }

    private boolean isMoveValid(Game game, char userX, char userY) {
        if (Character.isDigit(userX) && Character.isDigit(userY)) {
            return checkCoordinates(game, userX, userY);
        }
        System.out.println("You should enter numbers!");
        return false;
    }

    private boolean checkCoordinates(Game game, char userX, char userY) {
        int nbX = Character.getNumericValue(userX);
        int nbY = Character.getNumericValue(userY);
        if (isCoordinateValid(nbX) && isCoordinateValid(nbY)) {
            return isFree(game, nbX, nbY);
        }
        System.out.println("Coordinates should be from 1 to 3!");
        return false;
    }

    private boolean isCoordinateValid(int coordinate) {
        return coordinate > 0 && coordinate < 4;
    }

    private boolean isFree(Game game, int nbX, int nbY) {
        int row = Math.abs(nbY - 3);
        int column = nbX - 1;
        if (game.isFree(row, column)) {
            game.write(row, column);
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }
}
