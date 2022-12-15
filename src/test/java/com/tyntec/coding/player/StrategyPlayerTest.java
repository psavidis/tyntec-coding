package com.tyntec.coding.player;

import com.tyntec.coding.handshape.HandShape;
import com.tyntec.coding.player.strategy.FixedMovePlayerStrategy;
import com.tyntec.coding.player.strategy.RandomPlayerStrategy;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;

import java.util.Random;

import static com.tyntec.coding.handshape.HandShape.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class StrategyPlayerTest {

    @Test
    void construct_nullName_shouldThrow_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new StrategyPlayer(null, new RandomPlayerStrategy()));
    }

    @Test
    void construct_emptyName_shouldThrow_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new StrategyPlayer("", new RandomPlayerStrategy()));
    }

    @Test
    void construct_blankName_shouldThrow_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new StrategyPlayer(" ", new RandomPlayerStrategy()));
    }

    @Test
    void construct_nullStrategy_shouldThrow_IllegalArgumentException() {
        assertThrows(NullPointerException.class, () -> new StrategyPlayer("test", null));
    }

    @Test
    void makeMove_withFixed_PaperStrategy_shouldReturn_PAPER() {
        assertEquals(PAPER, new StrategyPlayer("test", new FixedMovePlayerStrategy(PAPER)).makeMove());
    }

    @Test
    void makeMove_withFixed_ScissorsStrategy_shouldReturn_SCISSORS() {
        assertEquals(SCISSORS, new StrategyPlayer("test", new FixedMovePlayerStrategy(SCISSORS)).makeMove());
    }

    @Test
    void makeMove_withFixed_RockStrategy_shouldReturn_ROCK() {
        assertEquals(ROCK, new StrategyPlayer("test", new FixedMovePlayerStrategy(ROCK)).makeMove());
    }

    @Test
    void makeMove_randomStrategy() {
        var random = mock(Random.class);
        var strategy = new RandomPlayerStrategy(random);

        var player = new StrategyPlayer("test", strategy);
        player.makeMove();

        verify(random, times(1)).nextInt(anyInt());
    }
}
