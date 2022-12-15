package com.tyntec.coding;

import com.google.common.collect.Multimap;
import com.tyntec.coding.exception.GameException;
import com.tyntec.coding.handshape.HandshapeComparator;
import com.tyntec.coding.player.Player;
import com.tyntec.coding.player.PlayerPairs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class representing a {@link Game} instance during which all its players will compete against each other
 * the total number of rounds of this game playing rock-paper-scissors game. The game monitors also their
 * results to be reported at the end.
 */
public final class Game {

    private final Map<String, Player> playersByName;
    private final long totalRounds;
    private final Map<String, AtomicLong> winsByPlayerName;
    private final Multimap<String, String> uniquePlayerNamePairs;
    private long totalTies;

    /**
     * Constructor using players and rounds.
     *
     * @param players     the list of players that will participate in the game
     * @param totalRounds the total number of rounds the game will have
     * @throws GameException in case of invalid data
     */
    public Game(List<Player> players, long totalRounds) {
        validateInput(players, totalRounds);

        this.playersByName = new ArrayList<>(players).stream()
                .collect(Collectors.toMap(Player::getName, (Function.identity())));

        this.totalRounds = totalRounds;
        this.totalTies = 0L;

        this.winsByPlayerName = initializeEmptyResultsMap(players);
        this.uniquePlayerNamePairs = new PlayerPairs(players).getUniquePairsAsMap();
    }

    /**
     * Executes the game by running all the rounds.
     */
    public void execute() {
        var comparator = HandshapeComparator.newComparator();

        for (int i = 0; i < totalRounds; i++) {
            executeRound(comparator);
        }
    }

    private void executeRound(HandshapeComparator comparator) {
        uniquePlayerNamePairs.forEach((playerName1, playerName2) -> {
            var player1 = playersByName.get(playerName1);
            var player2 = playersByName.get(playerName2);

            var move1 = player1.makeMove();
            var move2 = player2.makeMove();

            var result = comparator.compareAsResult(move1, move2);

            if (result.isWin()) {
                winsByPlayerName.get(playerName1).incrementAndGet();
            } else if (result.isLoss()) {
                winsByPlayerName.get(playerName2).incrementAndGet();
            } else {
                totalTies++;
            }

        });
    }

    /**
     * Get the total number of rounds for this game.
     */
    public long getTotalRounds() {
        return totalRounds;
    }

    /**
     * Get a map which contains entries of each player's name - number of wins they had during the game.
     */
    public Map<String, AtomicLong> getWinsByPlayerNameMap() {
        return winsByPlayerName;
    }

    /**
     * Retrieves the total number of ties for this game.
     */
    public long getTotalTies() {
        return this.totalTies;
    }

    private void validateInput(List<Player> players, long totalRounds) {
        if (players == null || players.isEmpty()) {
            throw new GameException("The game cannot start without players");
        }

        if (players.size() == 1) {
            throw new GameException("The game needs to have more than one players to compete");
        }

        if (totalRounds < 0) {
            throw new GameException("The total rounds of the game need to be a positive number");
        }
    }

    private Map<String, AtomicLong> initializeEmptyResultsMap(List<Player> players) {
        var results = new HashMap<String, AtomicLong>();
        for (Player p : players) {
            results.put(p.getName(), new AtomicLong(0L));
        }
        return results;
    }
}