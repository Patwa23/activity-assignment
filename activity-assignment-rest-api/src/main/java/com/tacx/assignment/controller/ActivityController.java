package com.tacx.assignment.controller;

import com.tacx.assignment.auth.Endpoint;
import com.tacx.assignment.dto.ActivitySummaryDto;
import com.tacx.assignment.services.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(Endpoint.API_V1_ACTIVITY)
@Api(value="Activity and Records", description = "Activity endpoints ", tags=("Activity & Records"))
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping(Endpoint.UPLOAD_POST)
    @ApiOperation(
            value="Activity file upload",
            notes="Upload activity file - csv file",
            consumes = "multipart/form-data" )
    public ActivitySummaryDto upload(@RequestParam("file") final MultipartFile file) throws Exception {
        return activityService.upload(file);
    }

    @GetMapping(Endpoint.ACTIVITIES_GET)
    @ApiOperation(
            value="Get All Activities",
            notes="Find all the activities")
    public ResponseEntity<List<ActivitySummaryDto>> getAllActivities () {
        try {
            List<ActivitySummaryDto> activities = activityService.getAllActivities();

            if (activities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<ActivitySummaryDto>>(activities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(Endpoint.ACTIVITY_GET)
    @ApiOperation(
            value="Get Activity by Id",
            notes="Search activity by activityId")
    public ResponseEntity<ActivitySummaryDto> getActivityById (@PathVariable UUID activityId) {
        try {
            ActivitySummaryDto activity = activityService.getActivityById(activityId);

            if (activity==null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<ActivitySummaryDto>(activity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(Endpoint.ACTIVITY_DELETE)
    @ApiOperation(
            value="Delete Activity by Id",
            notes="Remove activity by activityId")
    public ResponseEntity<ActivitySummaryDto> deleteActivityById (@PathVariable UUID activityId) {
        try {
            ActivitySummaryDto activity = activityService.deleteActivityById(activityId);

            if (activity==null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<ActivitySummaryDto>(activity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
