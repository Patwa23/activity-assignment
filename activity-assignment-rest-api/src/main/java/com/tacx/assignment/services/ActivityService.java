package com.tacx.assignment.services;

import com.tacx.assignment.dto.ActivitySummaryDto;
import com.tacx.assignment.exception.FileReadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ActivityService {

    public ActivitySummaryDto upload(final MultipartFile file) throws IOException, FileReadException;

    public List<ActivitySummaryDto> getAllActivities();

    public ActivitySummaryDto getActivityById(UUID Id);

    public ActivitySummaryDto deleteActivityById(UUID Id);

}
