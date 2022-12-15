package com.tyntec.coding.exception;

/**
 * Business exception related to {@link com.tyntec.coding.Game}.
 */
public class GameException extends RuntimeException {

    public GameException(String message) {
        super(message);
    }
}
