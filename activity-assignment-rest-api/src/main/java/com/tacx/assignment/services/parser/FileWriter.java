package com.tacx.assignment.services.parser;

public interface FileWriter<T> {

    void writeFile(T records, final String fileName);
}
