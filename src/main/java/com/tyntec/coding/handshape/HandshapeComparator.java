package com.tyntec.coding.handshape;

import com.tyntec.coding.player.PlayerMoveResult;

import java.util.*;
import java.util.stream.Collectors;

import static com.tyntec.coding.handshape.HandShape.*;
import static com.tyntec.coding.player.PlayerMoveResult.*;

/**
 * {@link Comparator} of {@link HandShape} that encapsulates the rules of the rock-paper-scissors game.
 * Compares two handshakes and can determine if the first one wins, loses or gets a tie against
 * the second argument of the comparison.
 */
public class HandshapeComparator implements Comparator<HandShape> {

    private final Map<HandShape, HandShape> winsAgainst;
    private final Map<HandShape, HandShape> lossesAgainst;

    private HandshapeComparator(Map<HandShape, HandShape> winsAgainst, Map<HandShape, HandShape> lossesAgainst) {
        Objects.requireNonNull(winsAgainst, "win mappings cannot be null");
        Objects.requireNonNull(lossesAgainst, "lose mappings cannot be null");

        validateAllRulesExist(winsAgainst, lossesAgainst);

        this.winsAgainst = winsAgainst;
        this.lossesAgainst = lossesAgainst;
    }

    /**
     * Static factory method to create a new handshape-comparator.
     */
    public static HandshapeComparator newComparator() {
        final var winsAgainst = Map.of(
                SCISSORS, PAPER,
                ROCK, SCISSORS,
                PAPER, ROCK
        );

        final var lossesAgainst = Map.of(
                SCISSORS, ROCK,
                ROCK, PAPER,
                PAPER, SCISSORS
        );

        return new HandshapeComparator(winsAgainst, lossesAgainst);
    }

    @Override
    public int compare(HandShape first, HandShape enemy) {
        if (first == null || enemy == null) {
            throw new IllegalArgumentException("Comparator does not support null values");
        }

        if (winsAgainst.get(first) == enemy) {
            return WIN.toInteger();
        }

        if (lossesAgainst.get(first) == enemy) {
            return LOSS.toInteger();
        }

        return TIE.toInteger();
    }

    /**
     * Compares two {@link HandShape}s and returns the result as a {@link PlayerMoveResult}.
     */
    public PlayerMoveResult compareAsResult(HandShape first, HandShape enemy) {
        return PlayerMoveResult.valueOf(compare(first, enemy));
    }

    private void validateAllRulesExist(Map<HandShape, HandShape> winsAgainst, Map<HandShape, HandShape> lossesAgainst) {
        Set<HandShape> allValues = Arrays.stream(HandShape.values())
                .collect(Collectors.toSet());

        if (!winsAgainst.keySet().equals(allValues)) {
            throw new RuntimeException("Win mappings must contain one rule for all hand shape values");
        }

        if (!lossesAgainst.keySet().equals(allValues)) {
            throw new RuntimeException("Loss mappings must contain one rule for all hand shape values");
        }
    }
}