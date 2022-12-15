package com.tyntec.coding.player.strategy;

import com.tyntec.coding.handshape.HandShape;

import java.util.Random;

/**
 * Strategy that selects a random {@link HandShape}.
 */
public class RandomPlayerStrategy extends PlayerStrategy {

    private final Random random;

    /**
     * Constructor using random for unit testing purposes.
     */
    public RandomPlayerStrategy(Random random) {
        this.random = random;
    }

    /**
     * Empty constructor.
     */
    public RandomPlayerStrategy() {
        this(new Random());
    }

    public HandShape apply() {
        int nextId = random.nextInt(HandShape.values().length);
        return HandShape.values()[nextId];
    }
}