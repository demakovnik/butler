package org.inofttech.butler.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ItemIncorrectData> handleException(NoSuchItemException exception) {
        ItemIncorrectData data = new ItemIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ItemIncorrectData> handleException(IncorrectDataItemException exception) {
        ItemIncorrectData data = new ItemIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
