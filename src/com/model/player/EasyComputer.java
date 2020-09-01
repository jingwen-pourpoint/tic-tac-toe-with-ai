package com.model.player;

import com.model.Game;

public class EasyComputer extends Player {

    @Override
    public void makeMove(Game game) {
        System.out.println("Making move level \"easy\"");
        playRandomMove(game);
    }
}
