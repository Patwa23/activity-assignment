package com.tacx.assignment.exception;


/**
 * This exception can be thrown if there is any problem
 * while writing a file with proper message.
 */
public class FileWriteException extends RuntimeException {

    public FileWriteException(final String message) {
        super(message);
    }

}
