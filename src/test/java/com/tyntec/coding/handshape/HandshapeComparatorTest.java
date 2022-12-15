package com.tyntec.coding.handshape;

import org.junit.jupiter.api.Test;

import static com.tyntec.coding.handshape.HandShape.*;
import static com.tyntec.coding.player.PlayerMoveResult.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HandshapeComparatorTest {

    private HandshapeComparator comparator = HandshapeComparator.newComparator();

    @Test
    void compare_null_arguments_shouldReturn_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, null));
    }

    @Test
    void compare_null_argumentOne_shouldReturn_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, PAPER));
    }

    @Test
    void compare_null_argumentTwo_shouldReturn_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(PAPER, null));
    }

    // equality cases

    @Test
    void compare_PAPER_Against_PAPER_shouldReturn_Zero() {
        assertEquals(0, comparator.compare(PAPER, PAPER));
    }

    @Test
    void compare_ROCK_Against_ROCK_shouldReturn_Zero() {
        assertEquals(0, comparator.compare(ROCK, ROCK));
    }

    @Test
    void compare_SCISSORS_Against_SCISSORS_shouldReturn_Zero() {
        assertEquals(0, comparator.compare(SCISSORS, SCISSORS));
    }

    // first argument is less than

    @Test
    void compare_PAPER_Against_SCISSORS_shouldReturn_MinusOne() {
        assertEquals(-1, comparator.compare(PAPER, SCISSORS));
    }

    @Test
    void compare_ROCK_Against_ROCK_shouldReturn_MinusOne() {
        assertEquals(-1, comparator.compare(ROCK, PAPER));
    }

    @Test
    void compare_SCISSORS_Against_SCISSORS_shouldReturn_MinusOne() {
        assertEquals(-1, comparator.compare(SCISSORS, ROCK));
    }

    // first argument greater than

    @Test
    void compare_PAPER_Against_SCISSORS_shouldReturn_One() {
        assertEquals(1, comparator.compare(PAPER, ROCK));
    }

    @Test
    void compare_ROCK_Against_ROCK_shouldReturn_One() {
        assertEquals(1, comparator.compare(ROCK, SCISSORS));
    }

    @Test
    void compare_SCISSORS_Against_SCISSORS_shouldReturn_One() {
        assertEquals(1, comparator.compare(SCISSORS, PAPER));
    }

    // compareAsResult

    @Test
    void compareAsResult_null_arguments_shouldReturn_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compareAsResult(null, null));
    }

    @Test
    void compareAsResult_null_argumentOne_shouldReturn_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compareAsResult(null, PAPER));
    }

    @Test
    void compareAsResult_null_argumentTwo_shouldReturn_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compareAsResult(PAPER, null));
    }

    // TIE

    @Test
    void compareAsResult_PAPER_Against_PAPER_shouldReturn_TIE() {
        assertEquals(TIE, comparator.compareAsResult(PAPER, PAPER));
    }

    @Test
    void compareAsResult_ROCK_Against_ROCK_shouldReturn_TIE() {
        assertEquals(TIE, comparator.compareAsResult(ROCK, ROCK));
    }

    @Test
    void compareAsResult_SCISSORS_Against_SCISSORS_shouldReturn_TIE() {
        assertEquals(TIE, comparator.compareAsResult(SCISSORS, SCISSORS));
    }

    // LOSS

    @Test
    void compareAsResult_PAPER_Against_SCISSORS_shouldReturn_LOSS() {
        assertEquals(LOSS, comparator.compareAsResult(PAPER, SCISSORS));
    }

    @Test
    void compareAsResult_ROCK_Against_ROCK_shouldReturn_MLOSS() {
        assertEquals(LOSS, comparator.compareAsResult(ROCK, PAPER));
    }

    @Test
    void compareAsResult_SCISSORS_Against_SCISSORS_shouldReturn_LOSS() {
        assertEquals(LOSS, comparator.compareAsResult(SCISSORS, ROCK));
    }

    // WIN

    @Test
    void compareAsResult_PAPER_Against_SCISSORS_shouldReturn_WIN() {
        assertEquals(WIN, comparator.compareAsResult(PAPER, ROCK));
    }

    @Test
    void compareAsResult_Against_ROCK_shouldReturn_WIN() {
        assertEquals(WIN, comparator.compareAsResult(ROCK, SCISSORS));
    }

    @Test
    void compareAsResult_SCISSORS_Against_SCISSORS_shouldReturn_WIN() {
        assertEquals(WIN, comparator.compareAsResult(SCISSORS, PAPER));
    }

}