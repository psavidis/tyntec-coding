package com.tyntec.coding.player;

import com.google.common.collect.Multimap;
import com.tyntec.coding.player.strategy.RandomPlayerStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPairsTest {

    @Test
    void constructWith_null_playersList_shouldThrow_RuntimeException() {
        assertThrows(RuntimeException.class, () -> new PlayerPairs(null));
    }

    @Test
    void constructWith_empty_playersList_shouldThrow_RuntimeException() {
        assertThrows(RuntimeException.class, () -> new PlayerPairs(List.of()));
    }

    @Test
    void listWith_sizeOne_shouldThrow_RuntimeException() {
        List<Player> singleList = List.of(new StrategyPlayer("test", new RandomPlayerStrategy()));

        assertThrows(RuntimeException.class, () -> new PlayerPairs(singleList));
    }

    @Test
    void getUniquePairsAsMap_happyPath_withTwoPlayers() {
        List<Player> players = List.of(
                new StrategyPlayer("player1", new RandomPlayerStrategy()),
                new StrategyPlayer("player2", new RandomPlayerStrategy()));

        var result = new PlayerPairs(players).getUniquePairsAsMap();

        assert_isPairedWith(result, "player1", "player2");
    }

    @Test
    void getUniquePairsAsMap_happyPath_withThreePlayers() {
        List<Player> players = List.of(
                new StrategyPlayer("player1", new RandomPlayerStrategy()),
                new StrategyPlayer("player2", new RandomPlayerStrategy()),
                new StrategyPlayer("player3", new RandomPlayerStrategy()));

        var result = new PlayerPairs(players).getUniquePairsAsMap();

        assert_isPairedWith(result, "player1", "player2", "player3");
        assert_isPairedWith(result, "player2", "player3");
    }

    private void assert_isPairedWith(Multimap<String, String> results, String player, String... players) {
        var result = results.get(player);

        assertEquals(players.length, result.size(), "Results of " + player + " should be exactly " + players.length);
        for (String p : players) {
            assertTrue(result.contains(p));
        }
    }


}
