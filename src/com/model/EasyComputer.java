package com.model;

import java.util.Random;

public class EasyComputer extends Player {

    public EasyComputer(boolean x) {
        super(x);
    }

    @Override
    public void makeMove(char[][] board) {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int randomX;
        int randomY;
        do {
            randomX = random.nextInt(3);
            randomY = random.nextInt(3);
        } while (board[randomX][randomY] != ' ');

        writeMove(board, randomX, randomY);
    }
}
