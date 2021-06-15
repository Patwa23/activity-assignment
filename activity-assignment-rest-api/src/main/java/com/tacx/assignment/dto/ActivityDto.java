package com.tacx.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {

    private UUID id;
    private String activity;
    private String name;
    private String type;
    private LocalDateTime startTime;
}
