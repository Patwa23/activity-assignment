package com.tacx.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {

    private Integer totalDistance;
    private String totalDuration;
    private Double averagePower;
    private Double averageCadence;
}
