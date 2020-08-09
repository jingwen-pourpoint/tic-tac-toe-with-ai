package com.model;

public class Player {

    private final boolean x;
    private boolean winner;

    // Constructeur
    public Player(boolean x) {
        this.x = x;
    }

    // Methods
    public void makeMove(char[][] board) {

    }

    protected void writeMove(char[][] board, int x, int y) {
        board[x][y] = isX() ? 'X' : 'O';
    }


    // Getters & setters
    public boolean isX() {
        return x;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
