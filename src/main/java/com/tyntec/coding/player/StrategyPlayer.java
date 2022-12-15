package com.tyntec.coding.player;

import com.tyntec.coding.handshape.HandShape;
import com.tyntec.coding.player.strategy.PlayerStrategy;

import java.util.Objects;

/**
 * Player who can delegate his move to a strategy.
 */
public class StrategyPlayer implements Player {

    private final String name;
    private final PlayerStrategy strategy;

    /**
     * Constructor using a strategy, non-null.
     *
     * @param strategy the strategy the player can utilize to make a move
     */
    public StrategyPlayer(String name, PlayerStrategy strategy) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name has to be a valid non blank name");
        }

        Objects.requireNonNull(strategy, "Cannot create player with null strategy");

        this.name = name;
        this.strategy = strategy;
    }

    @Override
    public HandShape makeMove() {
        var result = strategy.apply();

        return result;
    }

    @Override
    public String getName() {
        return name;
    }
}
