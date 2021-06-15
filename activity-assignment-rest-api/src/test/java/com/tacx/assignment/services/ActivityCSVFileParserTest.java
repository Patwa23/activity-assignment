package com.tacx.assignment.services;

import com.tacx.assignment.exception.FileReadException;
import com.tacx.assignment.model.Activity;
import com.tacx.assignment.services.parser.ActivityCSVFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ActivityCSVFileParserTest {

    private ActivityCSVFileParser fileParser;

    @BeforeAll
    void setup() {
        fileParser = new ActivityCSVFileParser();
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/static/assignment_activity.csv"})
    void processFile_Success(final String file) throws FileReadException {
        final Activity activity = fileParser.parseFile(Paths.get(file));
        Assertions.assertEquals("My first ride", activity.getName());
    }
}

