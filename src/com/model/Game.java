package com.model;

import com.model.player.Player;
import com.util.VictoryChecker;

import java.util.Arrays;

public class Game {

    private final Player xPlayer;
    private final Player oPlayer;

    private Symbol[][] board;
    private boolean xIsNext;
    private Symbol winner;

    public Game(String[] command) {
        xPlayer = Player.of(command[1]);
        oPlayer = Player.of(command[2]);
        xIsNext = true;
        buildBoard();
    }

    private void buildBoard() {
        board = new Symbol[3][3];
        for (Symbol[] row : board) {
            Arrays.fill(row, Symbol.EMPTY);
        }
    }

    private void displayBoard() {
        System.out.println("---------");
        for (Symbol[] row : board) {
            System.out.print("| ");
            for (Symbol cell : row) {
                System.out.print(cell.getRepresentation() + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public void play() {
        displayBoard();
        while (continues()) {
            if (xIsNext) {
                xPlayer.makeMove(this);
            } else {
                oPlayer.makeMove(this);
            }
            displayBoard();
            winner = VictoryChecker.checkWinner(board);
        }
        displayEndGame();
    }

    private boolean continues() {
        return winner == null;
    }

    public void write(int row, int column) {
        if (winner != null) {
            throw new IllegalStateException();
        }
        board[row][column] = nextSymbol();
        xIsNext = !xIsNext;
    }

    public boolean isFree(int row, int column) {
        return board[row][column] == Symbol.EMPTY;
    }

    public Symbol nextSymbol() {
        return xIsNext ? Symbol.X : Symbol.O;
    }

    public boolean willWin(int row, int column, Symbol symbol) {
        if (isFree(row, column)) {
            board[row][column] = symbol;

            boolean won = VictoryChecker.checkWinner(board) == symbol;

            board[row][column] = Symbol.EMPTY;
            return won;
        }
        return false;
    }

    private void displayEndGame() {
        if (winner == Symbol.X) {
            System.out.println("X wins");
        } else if (winner == Symbol.O) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
        System.out.println();
    }

    public Symbol[][] getBoard() {
        return board;
    }
}
