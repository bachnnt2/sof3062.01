package org.example.be.exception;

public class BeerResponseException extends RuntimeException {
    public BeerResponseException(String message) {
        super(message);
    }
}
