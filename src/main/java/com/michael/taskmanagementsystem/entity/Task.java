package com.michael.taskmanagementsystem.entity;
//
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)

    private String taskName;
    private String taskDescription;
    private Date startDate, endDate;
    private LocalDateTime startTime, endTime;
    private String  taskStatus;
    private String  taskCategory;
    private Integer userId;
}
