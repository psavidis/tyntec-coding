package com.tyntec.coding.player.strategy;

import com.tyntec.coding.handshape.HandShape;

/**
 * A Strategy that a player can utilize to make their move in the game.
 */
public abstract class PlayerStrategy {

    /**
     * Apply the strategy of the player to get a hand shape.
     */
    public abstract HandShape apply();
}
