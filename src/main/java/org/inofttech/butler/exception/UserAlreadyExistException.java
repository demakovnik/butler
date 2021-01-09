package org.inofttech.butler.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
