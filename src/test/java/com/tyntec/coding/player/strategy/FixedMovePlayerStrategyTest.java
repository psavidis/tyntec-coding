package com.tyntec.coding.player.strategy;

import org.junit.jupiter.api.Test;

import static com.tyntec.coding.handshape.HandShape.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FixedMovePlayerStrategyTest {

    private FixedMovePlayerStrategy strategy;

    @Test
    void newStrategy_with_nullHandshake_shouldThrow_NullPointerException() {
        assertThrows(NullPointerException.class, () -> new FixedMovePlayerStrategy(null));
    }

    @Test
    void apply_given_PAPER_shouldReturn_always_PAPER() {
        strategy = new FixedMovePlayerStrategy(PAPER);

        assertEquals(PAPER, strategy.apply());
    }

    @Test
    void apply_given_SCISSORS_shouldReturn_always_SCISSORS() {
        strategy = new FixedMovePlayerStrategy(SCISSORS);

        assertEquals(SCISSORS, strategy.apply());
    }

    @Test
    void apply_given_ROCK_shouldReturn_always_ROCK() {
        strategy = new FixedMovePlayerStrategy(ROCK);

        assertEquals(ROCK, strategy.apply());
    }
}
