package com.model.player;

import com.model.Game;
import com.model.Symbol;
import com.util.VictoryChecker;

import java.util.Arrays;

public class HardComputer extends Player {

    @Override
    public void makeMove(Game game) {
        System.out.println("Making move level \"hard\"");
        Symbol player = game.nextSymbol();
        int bestMove = player == Symbol.X ? -100 : 100;
        int bestRow = -1;
        int bestColumn = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getBoard()[i][j] != Symbol.EMPTY) {
                    continue;
                }
                Symbol[][] clonedBoard = deepCopy(game.getBoard());
                clonedBoard[i][j] = player;
                int score = minimax(clonedBoard, player.opposite());
                if (player == Symbol.X && score > bestMove || player == Symbol.O && score < bestMove) {
                    bestMove = score;
                    bestRow = i;
                    bestColumn = j;
                }
            }
        }
        game.write(bestRow, bestColumn);
    }

    private int minimax(Symbol[][] board, Symbol player) {
        Integer evaluation = evaluate(board);
        if (evaluation != null) {
            return evaluation;
        }
        int bestMove = player == Symbol.X ? -100 : 100;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != Symbol.EMPTY) {
                    continue;
                }
                Symbol[][] clonedBoard = deepCopy(board);
                clonedBoard[i][j] = player;
                int score = minimax(clonedBoard, player.opposite());
                if (player == Symbol.X && score > bestMove || player == Symbol.O && score < bestMove) {
                    bestMove = score;
                }
            }
        }
        return bestMove;
    }

    private Integer evaluate(Symbol[][] board) {
        Symbol winner = VictoryChecker.checkWinner(board);
        if (winner == Symbol.X) {
            return 10;
        } else if (winner == Symbol.O) {
            return -10;
        } else if (winner == Symbol.EMPTY) {
            return 0;
        } else {
            return null;
        }
    }

    private Symbol[][] deepCopy(Symbol[][] original) {
        final Symbol[][] result = new Symbol[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

}
