package com.model.player;

import com.model.Game;

import java.util.Random;

public abstract class Player {

    // Methods
    public abstract void makeMove(Game game);

    protected void playRandomMove(Game game) {
        Random random = new Random();
        int row;
        int column;
        do {
            row = random.nextInt(3);
            column = random.nextInt(3);
        } while (!game.isFree(row, column));
        game.write(row, column);
    }

    public static Player of(String parameter) {
        switch (parameter) {
            case "user":
                return new HumanPlayer();
            case "easy":
                return new EasyComputer();
            case "medium":
                return new MediumComputer();
            case "hard":
                return new HardComputer();
            default:
                throw new IllegalArgumentException("Value " + parameter + " is not supported.");
        }
    }
}
