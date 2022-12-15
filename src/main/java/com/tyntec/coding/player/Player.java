package com.tyntec.coding.player;

import com.tyntec.coding.handshape.HandShape;

/**
 * A player that can make a move.
 */
public interface Player {

    /**
     * Makes the move in favor of the player and gets the result hand shape.
     */
    HandShape makeMove();

    /**
     * Retrieves the players name.
     */
    String getName();
}