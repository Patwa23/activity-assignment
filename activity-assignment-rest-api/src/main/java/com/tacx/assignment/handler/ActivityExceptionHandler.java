package com.tacx.assignment.handler;

import com.tacx.assignment.exception.ApiError;
import com.tacx.assignment.exception.FileNotFoundException;
import com.tacx.assignment.exception.IllegalException;
import com.tacx.assignment.exception.InvalidFileException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Activity Exception Handler holds all the Custom exception thrown by Service Class
 *
 * Created by Prakash Patwa on 13/06/2021
 */
@RestControllerAdvice
@Log4j2
public class ActivityExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ApiError handleNotFound(Exception ex) {
        return new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFileException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public final ApiError handleNotAcceptableException(InvalidFileException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ApiError handleIllegalException(IllegalException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

