package org.example.be.exception;

// đi thi có cái exception này được tận 1 điểm.
public class BeerResponseException extends RuntimeException {
    public BeerResponseException(String message) {
        super(message);
    }
}
