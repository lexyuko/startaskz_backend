package com.michael.taskmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.michael.taskmanagementsystem.entity.OurUsers;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String role;
    private String email;
    private String password;
    private OurUsers ourUsers;
    private Integer userId;
    private List<OurUsers> ourUsersList;
//    private String taskName;
//    private String taskDescription;
//    private Date startDate, endDate;
//    private LocalDateTime startTime, endTime;
//    private String TaskStatus;
//    private String TaskCategory;
//    private Task task;

}
