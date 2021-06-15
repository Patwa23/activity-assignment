package com.tacx.assignment.services;

import com.google.common.collect.ImmutableList;
import com.tacx.assignment.exception.FileReadException;
import com.tacx.assignment.exception.InvalidFileException;
import com.tacx.assignment.model.Activity;
import com.tacx.assignment.services.parser.ActivityCSVFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DisplayName("Activity Service Test")
class ActivityServiceTest {

    /**
     * The service that we want to test
     */

    @Mock
    private ActivityCSVFileParser csvFileParser;


    private ActivityServiceImpl activityService;

    @BeforeEach
    void setup() {
        activityService = new ActivityServiceImpl( csvFileParser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/static/assignment_activity.csv"})
    void parseFile_Success(final String file) throws InvalidFileException, FileReadException {

        final Path filePath = Paths.get(file);

        final Activity activity = Activity.builder()
                .activityDef("activity")
                .name("My first ride")
                .type("cycling")
                .startTime(LocalDateTime.now())
                .build();

        when(csvFileParser.parseFile(filePath))
                .thenReturn(activity);

        final Activity result = activityService.parseFile(filePath);

        assertEquals(result.getActivityDef(), "activity");
        assertEquals(result.getName(), "My first ride");

        verify(csvFileParser).parseFile(filePath);
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/static/assignment_activity.txt"})
    void parseFile_Invalid_FileType(final String file) {
        Assertions.assertThrows(InvalidFileException.class, () -> activityService.parseFile(Paths.get(file)));
    }

    static Stream<Arguments> happyPathTestInputProvider() {

        final Activity st1 = Activity.builder()
                .activityDef("activity1")
                .name("My first ride")
                .type("cycling")
                .startTime(LocalDateTime.now())
                .build();

        final Activity st2 = Activity.builder()
                .activityDef("activity2")
                .name("My second ride")
                .type("cycling")
                .startTime(LocalDateTime.now())
                .build();

        final Activity st3 = Activity.builder()
                .activityDef("activity3")
                .name("My third ride")
                .type("trade mill")
                .startTime(LocalDateTime.now())
                .build();

        final List<Activity> inputActivities = ImmutableList.of(st1, st2, st3);
        final List<Activity> expected = ImmutableList.of(st1, st2);

        return Stream.of(Arguments.of(inputActivities, expected));
    }
}
