package com.model.player;

import com.model.Game;
import com.model.Symbol;

public class MediumComputer extends Player {

    @Override
    public void makeMove(Game game) {
        System.out.println("Making move level \"medium\"");

        if (playWinningMove(game)) {
            return;
        }

        if (playBlockingMove(game)) {
            return;
        }

        playRandomMove(game);
    }

    public boolean playWinningMove(Game game) {
        Symbol symbolToCheck = game.nextSymbol();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (game.willWin(row, column, symbolToCheck)) {
                    game.write(row, column);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean playBlockingMove(Game game) {
        Symbol symbolToCheck = game.nextSymbol().opposite();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (game.willWin(row, column, symbolToCheck)) {
                    game.write(row, column);
                    return true;
                }
            }
        }
        return false;
    }
}
