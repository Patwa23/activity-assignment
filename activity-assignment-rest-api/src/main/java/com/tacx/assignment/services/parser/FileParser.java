package com.tacx.assignment.services.parser;

import com.tacx.assignment.exception.FileReadException;

import java.nio.file.Path;

/**
 * This interface can be implemented by a class that
 * gets a file path {@link Path} as input to process as per requirement
 * and generates a list of records.
 * @param <T>
 */
public interface FileParser<T> {

    T parseFile(final Path filePath) throws FileReadException;
}
