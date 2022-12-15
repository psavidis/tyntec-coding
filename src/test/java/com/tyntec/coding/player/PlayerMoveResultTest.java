package com.tyntec.coding.player;

import org.junit.jupiter.api.Test;

import static com.tyntec.coding.player.PlayerMoveResult.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerMoveResultTest {

    @Test
    void toInteger_LOSS_shouldReturn_MinusOne() {
        assertEquals(-1, LOSS.toInteger());
    }

    @Test
    void toInteger_TIE_shouldReturn_MinusOne() {
        assertEquals(0, TIE.toInteger());
    }

    @Test
    void toInteger_WIN_shouldReturn_MinusOne() {
        assertEquals(1, WIN.toInteger());
    }

    @Test
    void valueOf_one_shouldReturn_WIN() {
        assertEquals(WIN, PlayerMoveResult.valueOf(1));
    }

    @Test
    void valueOf_minusOne_shouldReturn_LOSS() {
        assertEquals(LOSS, PlayerMoveResult.valueOf(-1));
    }

    @Test
    void valueOf_zero_shouldReturn_TIE() {
        assertEquals(TIE, PlayerMoveResult.valueOf(0));
    }

    @Test
    void valueOf_two_shouldThrow_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> PlayerMoveResult.valueOf(2));
    }

    @Test
    void valueOf_minusTwo_shouldThrow_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> PlayerMoveResult.valueOf(-2));
    }

    // isWin

    @Test
    void WIN_shouldReturn_isWin_True() {
        assertEquals(true, WIN.isWin());
    }

    @Test
    void LOSS_shouldReturn_isWin_False() {
        assertEquals(false, LOSS.isWin());
    }

    @Test
    void TIE_shouldReturn_isWin_False() {
        assertEquals(false, TIE.isWin());
    }

    // isLoss

    @Test
    void WIN_shouldReturn_isLoss_True() {
        assertEquals(false, WIN.isLoss());
    }

    @Test
    void LOSS_shouldReturn_isLoss_False() {
        assertEquals(true, LOSS.isLoss());
    }

    @Test
    void TIE_shouldReturn_isLoss_False() {
        assertEquals(false, TIE.isLoss());
    }

    // isTie

    @Test
    void WIN_shouldReturn_isTie_True() {
        assertEquals(false, WIN.isTie());
    }

    @Test
    void LOSS_shouldReturn_isTie_False() {
        assertEquals(false, LOSS.isTie());
    }

    @Test
    void TIE_shouldReturn_isTie_False() {
        assertEquals(true, TIE.isTie());
    }
}
