package com.tyntec.coding.player.strategy;

import com.tyntec.coding.handshape.HandShape;

import java.util.Objects;

/**
 * Strategy that selects always a fixed hand shape.
 */
public final class FixedMovePlayerStrategy extends PlayerStrategy {

    private final HandShape handShape;

    /**
     * Constructor using a non null hand shape.
     */
    public FixedMovePlayerStrategy(HandShape handShape) {
        this.handShape = handShape = Objects.requireNonNull(handShape, "cannot accept null handShape");
    }

    @Override
    public HandShape apply() {
        return handShape;
    }
}
