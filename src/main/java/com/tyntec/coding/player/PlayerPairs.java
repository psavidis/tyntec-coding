package com.tyntec.coding.player;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class to encapsulate the unique combination of pairs of the different player names.
 */
public class PlayerPairs {

    private final Set<String> playerNames;
    private final Set<Set<String>> uniquePairs;

    /**
     * Constructor using a non-null list of players.
     */
    public PlayerPairs(List<Player> players) {

        if (players == null || players.isEmpty()) {
            throw new RuntimeException("Players cannot be null or empty");
        }

        if (players.size() == 1) {
            throw new RuntimeException("Cannot make pairs of players list with size 1");
        }

        this.playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        this.uniquePairs = Sets.combinations(playerNames, 2);
    }

    /**
     * Retrieve the unique pairs as a map.
     */
    public Multimap<String, String> getUniquePairsAsMap() {
        Multimap<String, String> results = ArrayListMultimap.create();

        for (Set<String> uniquePairSet : uniquePairs) {
            var pair = asPair(uniquePairSet);
            results.put(pair.getLeft(), pair.getRight());
        }

        return results;
    }

    private Pair<String, String> asPair(Set<String> pair) {
        if (pair.size() != 2) {
            throw new IllegalArgumentException("The given set should have exactly two elements to represent a pair");
        }

        var iterator = pair.iterator();

        return new ImmutablePair<>(iterator.next(), iterator.next());
    }
}
