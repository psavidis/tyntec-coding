package com.tyntec.coding;

import com.tyntec.coding.player.PlayerFactory;

import java.util.List;

import static com.tyntec.coding.handshape.HandShape.PAPER;

/**
 * Main class, entrypoint to run the program of competing rock-paper-scissors players.
 */
public class Main {

    public static void main(String[] args) {

        var player1 = PlayerFactory.createFixed("Player A", PAPER);
        var player2 = PlayerFactory.createRandom("Player B");

        var game = new Game(List.of(player1, player2), 100);

        game.execute();

        var result = game.getWinsByPlayerNameMap();
        result.forEach((name, count) -> {
            System.out.println(name + " wins " + count + " of " + game.getTotalRounds());
        });

        System.out.println("Tie: " + game.getTotalTies() + " of " + game.getTotalRounds());
    }
}
