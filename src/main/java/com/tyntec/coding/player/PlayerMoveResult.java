package com.tyntec.coding.player;

/**
 * Enumeration that describes the outcome of a round between two players of the game.
 */
public enum PlayerMoveResult {
    WIN, LOSS, TIE;

    /**
     * Converts a result into an integer.
     */
    public int toInteger() {
        if (this == WIN) {
            return 1;
        }

        if (this == TIE) {
            return 0;
        }

        if (this == LOSS) {
            return -1;
        }

        throw new UnsupportedOperationException(this.name() + " result is not supported and probably it is a bug");
    }

    /**
     * Converts a given number into a {@link PlayerMoveResult}.
     *
     * @param number the given number to convert
     * @return the corresponding {@link PlayerMoveResult}
     * @throws IllegalArgumentException in case the number does not correspond to a valid {@link PlayerMoveResult}
     */
    public static PlayerMoveResult valueOf(int number) {
        if (number == 1) {
            return WIN;
        }

        if (number == -1) {
            return LOSS;
        }

        if (number == 0) {
            return TIE;
        }

        throw new IllegalArgumentException("Number " + number + " cannot be converted to a valid PlayerMoveResult");
    }

    /**
     * Returns true if the result is wining, false otherwise.
     */
    public boolean isWin() {
        return this == WIN;
    }

    /**
     * Returns true if the result is a loss, false otherwise.
     */
    public boolean isLoss() {
        return this == LOSS;
    }

    /**
     * Returns true if the result is a tie, false otherwise.
     */
    public boolean isTie() {
        return this == TIE;
    }
}