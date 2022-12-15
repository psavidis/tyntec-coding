package com.tyntec.coding;

import com.tyntec.coding.exception.GameException;
import com.tyntec.coding.player.StrategyPlayer;
import com.tyntec.coding.player.strategy.FixedMovePlayerStrategy;
import com.tyntec.coding.player.strategy.RandomPlayerStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tyntec.coding.handshape.HandShape.PAPER;
import static com.tyntec.coding.handshape.HandShape.SCISSORS;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void construct_with_emptyPlayers_shouldThrow_GameException() {
        assertThrows(GameException.class, () -> new Game(List.of(), 1));
    }

    @Test
    void construct_with_onePlayer_shouldThrow_GameException() {
        assertThrows(GameException.class, () -> new Game(List.of(new StrategyPlayer("test", new RandomPlayerStrategy())), 1));
    }

    @Test
    void construct_with_negativeRounds_shouldThrow_GameException() {
        assertThrows(GameException.class, () -> new Game(List.of(new StrategyPlayer("test", new RandomPlayerStrategy())), -1));
    }

    @Test
    void construct_happyPath() {
        assertNotNull(new Game(List.of(
                new StrategyPlayer("p1", new RandomPlayerStrategy()),
                new StrategyPlayer("p2", new RandomPlayerStrategy())
        ), 1));
    }

    @Test
    void execute_TIE_shouldReturn_zeroWins_forPlayers() {
        var game = new Game(List.of(
                new StrategyPlayer("p1", new FixedMovePlayerStrategy(PAPER)),
                new StrategyPlayer("p2", new FixedMovePlayerStrategy(PAPER))
        ), 1);

        game.execute();

        assertEquals(1L, game.getTotalRounds());
        assertEquals(1, game.getTotalTies());

        assertPlayerWins("p1", 0, game);
        assertPlayerWins("p2", 0, game);
    }

    @Test
    void execute_PlayerOne_Wins() {
        var game = new Game(List.of(
                new StrategyPlayer("p1", new FixedMovePlayerStrategy(SCISSORS)),
                new StrategyPlayer("p2", new FixedMovePlayerStrategy(PAPER))
        ), 2);

        game.execute();

        assertEquals(2L, game.getTotalRounds());
        assertEquals(0, game.getTotalTies());

        assertPlayerWins("p1", 2, game);
        assertPlayerWins("p2", 0, game);
    }

    private void assertPlayerWins(String playerName, long playerWins, Game game) {
        assertEquals(playerWins, game.getWinsByPlayerNameMap().get(playerName).longValue());
    }

}
