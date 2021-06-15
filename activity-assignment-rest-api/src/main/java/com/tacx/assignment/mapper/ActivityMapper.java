package com.tacx.assignment.mapper;


import com.tacx.assignment.dto.ActivityDto;
import com.tacx.assignment.dto.ActivitySummaryDto;
import com.tacx.assignment.dto.RecordDto;
import com.tacx.assignment.model.Activity;
import com.tacx.assignment.model.Record;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Component
public class ActivityMapper {

    public ActivitySummaryDto activitytoActivitySummaryDto(Activity activity){
        ActivitySummaryDto activitySummaryDto = new ActivitySummaryDto();
        if(activity!=null){
            activitySummaryDto.setActivityDetail(activitytoActivityDto(activity));
            activitySummaryDto.setActivitySummary(activitytoRecordDto(activity));
        }
        return activitySummaryDto;
    }

    private ActivityDto activitytoActivityDto(Activity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getActivityId());
        activityDto.setActivity(activity.getActivityDef());
        activityDto.setName(activity.getName());
        activityDto.setType(activity.getType());
        activityDto.setStartTime(activity.getStartTime());
        return activityDto;
    }

    private RecordDto activitytoRecordDto(Activity activity){

        Collection<Record> records = activity.getListOfRecord();
        if(records.isEmpty()){
            return null;
        }
        Record firstRecord = records.stream()
                                    .findFirst()
                                    .orElse(null);

        Record lastRecord = records.stream().skip(records.size() - 1)
                                            .reduce((first,second)-> second)
                                            .orElse(null);

        RecordDto recordDto = new RecordDto();
        recordDto.setTotalDistance(lastRecord.getDistance());
        recordDto.setTotalDuration(getTotalDuration(firstRecord.getTime(),lastRecord.getTime()));
        recordDto.setAveragePower(getAveragePower(records));
        recordDto.setAverageCadence(getAverageCadence(records));
        return recordDto;
    }

    private Double getAveragePower(Collection<Record> list){
        return list.stream()
                .mapToInt(record -> record.getPower())
                .summaryStatistics().getAverage();
    }

    private Double getAverageCadence(Collection<Record> list){
        return list.stream()
                .mapToInt(record -> record.getCadence())
                .summaryStatistics().getAverage();
    }

    private String getTotalDuration(LocalDateTime fromDate, LocalDateTime toDate){
        long minutes = ChronoUnit.MINUTES.between(fromDate,toDate);
        long hours = ChronoUnit.HOURS.between(fromDate,toDate);
        String totalDuration = hours + " hrs " + minutes + " min";
        return totalDuration;
    }
}
