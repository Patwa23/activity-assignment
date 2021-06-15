package com.tacx.assignment.services;

import com.tacx.assignment.dto.ActivitySummaryDto;
import com.tacx.assignment.enums.FileType;
import com.tacx.assignment.exception.FileReadException;
import com.tacx.assignment.exception.InvalidFileException;
import com.tacx.assignment.mapper.ActivityMapper;
import com.tacx.assignment.model.Activity;
import com.tacx.assignment.repository.ActivityRepository;
import com.tacx.assignment.services.parser.ActivityCSVFileParser;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ActivityServiceImpl implements ActivityService{

    private final ActivityCSVFileParser csvFileParser;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRepository activityRepository;
    @Value("${upload.path}")
    private String path;

    public ActivityServiceImpl(ActivityCSVFileParser csvFileParser
    ) {
        this.csvFileParser = csvFileParser;
    }


    public ActivitySummaryDto upload(final MultipartFile file) throws IOException, FileReadException {

        // number of files processed successfully
        int processedFileCount = 0;

        if (file.isEmpty()) {
            throw new InvalidFileException("Failed to store empty file.");
        }

        log.info("Started reading the {} file:", file.getOriginalFilename());
        var fileName = file.getOriginalFilename();
        var is = file.getInputStream();
        var filePath = Paths.get(path + fileName);

        Files.copy(is, filePath,
                StandardCopyOption.REPLACE_EXISTING);

        final Activity activity = parseFile(filePath);

        if (null != activity) {
           // activity.removeAll(invalidRecords);
            this.saveActivity(activity);
            processedFileCount++;

        }
        log.info("Total no. of file processed - {}.", processedFileCount);
        return activityMapper.activitytoActivitySummaryDto(activity);
    }

    @Transactional
    public Activity saveActivity(Activity activity) {
        Activity response = activityRepository.save(activity);
        return response;
    }

    /**
     * It checks the file type of the input file. If file type is not supported then throws exception.
     * Otherwise passes the file to corresponding file parser.
     *
     * @param filePath - input file
     * @return list of parsed records from the file.
     * @throws InvalidFileException - If the file type is not supported.
     * @throws FileReadException    - If there is any problem in reading/parsing the file.
     */

    public Activity parseFile(final Path filePath) throws InvalidFileException, FileReadException {

        final String fileName = filePath.getFileName().toString();
        final String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

        final FileType fileType = FileType.fromLabel(fileExtension);

        if (null == fileType) {
            log.warn("file type is not supported.");
            throw new InvalidFileException("file extension is not valid.It must be either xml/csv");
        }

        switch (fileType) {
            case CSV:
                return csvFileParser.parseFile(filePath);
            default:
                log.warn("{} file type is not supported.", fileType.name());
        }

        return null;
    }

    public List<ActivitySummaryDto> getAllActivities(){
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                .map(activity -> activityMapper.activitytoActivitySummaryDto(activity))
                .collect(Collectors.toList());
    }

    public ActivitySummaryDto getActivityById(UUID Id){
        Optional<Activity> activity = activityRepository.findById(Id);
        return activityMapper.activitytoActivitySummaryDto(activity.get());

    }

    public ActivitySummaryDto deleteActivityById(UUID Id){
        Optional<Activity> activity = activityRepository.findById(Id);
        activityRepository.deleteById(Id);
        return activityMapper.activitytoActivitySummaryDto(activity.get());
    }

}
