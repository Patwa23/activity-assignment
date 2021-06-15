package com.tacx.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySummaryDto {
    private ActivityDto activityDetail;
    private RecordDto activitySummary;
}
