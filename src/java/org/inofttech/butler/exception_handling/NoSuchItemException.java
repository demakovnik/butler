package org.inofttech.butler.exception_handling;

public class NoSuchItemException extends RuntimeException{
    public NoSuchItemException(String message) {
        super(message);
    }
}
