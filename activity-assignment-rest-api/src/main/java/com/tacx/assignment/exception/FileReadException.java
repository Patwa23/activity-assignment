package com.tacx.assignment.exception;

/**
 * This exception can be thrown if there is any problem
 * while reading a file with proper message.
 */
public class FileReadException extends Exception {

    public FileReadException(final String message) {
        super(message);
    }

}
