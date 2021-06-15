package com.tacx.assignment.services.parser;

import com.tacx.assignment.exception.FileReadException;
import com.tacx.assignment.model.Activity;
import com.tacx.assignment.model.Record;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements {@link FileParser} interface. It parses
 * a customer statement CSV file.
 */
@Log4j2
@Component
public class ActivityCSVFileParser implements FileParser<Activity> {

    private static final char COMMA_DELIMITER_DEFAULT = ',';
    private static final String UTF_8 = "UTF-8";

    /**
     * Parses a csv file.
     *
     * @param filePath - csv file path
     * @return - list of records in the file.
     * @throws FileReadException
     */
    @Override
    public Activity parseFile(final Path filePath) throws FileReadException {

        final File file = new File(filePath.toUri());
        List<CSVRecord> csvRecords = getCsvRecords(file);

        log.info("{} records are parsed from file {}", csvRecords.size(), file.getPath());

        Optional<Activity> activity= csvRecords.stream()
                .filter(record -> record.getRecordNumber()== 1)
                .map(record -> {
                    return Activity.builder()
                            .activityDef(record.get(0))
                            .name(record.get(1))
                            .type(record.get(2))
                            .startTime(parseStringToDate(record.get(3)))
                            .build();
                }).findAny();

        List<Record> records= csvRecords.stream()
                .filter(record -> record.getRecordNumber()>= 3)
                .map(record -> {
                    return Record.builder()
                            .recordDef(record.get(0))
                            .time(parseStringToDate(record.get(1)))
                            .distance(Integer.parseInt(record.get(2)))
                            .power(Integer.parseInt(record.get(3)))
                            .cadence(Integer.parseInt(record.get(4)))
                            .activity(activity.get())
                            .build();
                }).collect(Collectors.toList());

        if(activity.isPresent()){
            activity.get().setListOfRecord(records);
        }
        return activity.get();
    }

    private LocalDateTime parseStringToDate(String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
        return dateTime;

    }




    /**
     * Parses a csv file.
     *
     * @param file - csv file
     * @return - list of csv records
     * @throws FileReadException
     */
    private List<CSVRecord> getCsvRecords(final File file) throws FileReadException {

        try {

            try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
                 CSVParser csvParser = new CSVParser(bufferedReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames())) {

                return csvParser.getRecords();
            }
        } catch (IOException e) {
            throw new FileReadException(MessageFormat.format("Error while parsing CSV file {}.", file.getPath()));
        }
    }
}
