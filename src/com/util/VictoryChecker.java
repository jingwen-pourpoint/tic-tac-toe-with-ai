package com.util;

import com.model.Symbol;

public class VictoryChecker {

    private VictoryChecker() {
        // Util class; should not be instantiated
    }

    public static Symbol checkWinner(Symbol[][] board) {
        if (win(board, Symbol.X)) {
            return Symbol.X;
        } else if (win(board, Symbol.O)) {
            return Symbol.O;
        } else if (isFilled(board)) {
            return Symbol.EMPTY; //??
        }
        return null;
    }

    private static boolean win(Symbol[][] board, Symbol symbolToCheck) {
        return checkColumns(board, symbolToCheck) || checkRows(board, symbolToCheck)
                || checkTopLeftDiagonal(board, symbolToCheck) || checkTopRightDiagonal(board, symbolToCheck);
    }

    private static boolean checkColumns(Symbol[][] board, Symbol symbolToCheck) {
        for (int column = 0; column < 3; column++) {
            if (board[0][column] == symbolToCheck
                    && board[1][column] == symbolToCheck
                    && board[2][column] == symbolToCheck) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRows(Symbol[][] board, Symbol symbolToCheck) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == symbolToCheck
                    && board[row][1] == symbolToCheck
                    && board[row][2] == symbolToCheck) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTopRightDiagonal(Symbol[][] board, Symbol symbolToCheck) {
        return board[2][0] == symbolToCheck && board[1][1] == symbolToCheck && board[0][2] == symbolToCheck;
    }

    private static boolean checkTopLeftDiagonal(Symbol[][] board, Symbol symbolToCheck) {
        return board[0][0] == symbolToCheck && board[1][1] == symbolToCheck && board[2][2] == symbolToCheck;
    }

    private static boolean isFilled(Symbol[][] board) {
        for (Symbol[] symbols : board) {
            for (Symbol symbol : symbols) {
                if (symbol == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
