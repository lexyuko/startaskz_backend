package com.michael.taskmanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.michael.taskmanagementsystem.entity.Task;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskReqRes {
    private Integer id;
    private String taskName;
    private String taskDescription;
    private Date startDate, endDate;
    private LocalDateTime startTime, endTime;
    private String taskStatus;
    private String taskCategory;
    private Integer userId;
    private Task task;
}

