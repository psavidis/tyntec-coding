package com.tyntec.coding.player;

import com.tyntec.coding.handshape.HandShape;
import com.tyntec.coding.player.strategy.FixedMovePlayerStrategy;
import com.tyntec.coding.player.strategy.RandomPlayerStrategy;

/**
 * Factory that simplifies the creates of Players.
 */
public class PlayerFactory {

    /**
     * Creates a Player with a {@link FixedMovePlayerStrategy}.
     */
    public static Player createFixed(String name, HandShape handShape) {
        return new StrategyPlayer(name, new FixedMovePlayerStrategy(handShape));
    }

    /**
     * Creates a Player with a {@link RandomPlayerStrategy}.
     */
    public static Player createRandom(String name) {
        return new StrategyPlayer(name, new RandomPlayerStrategy());
    }
}
