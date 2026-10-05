package com.makerspace.makerstore.model;

/** The base class of every MakerStore problem, so the UI can catch them all in one place. */
public class MakerStoreException extends Exception {
    public MakerStoreException(String message) {
        super(message);
    }
}
